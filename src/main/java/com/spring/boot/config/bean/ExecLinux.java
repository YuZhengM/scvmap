package com.spring.boot.config.bean;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.spring.boot.ScvdbApplication;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import com.spring.boot.util.util.NumberUtil;
import com.spring.boot.util.util.StringUtil;
import com.spring.boot.util.util.config.ReadConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Executes Linux commands and returns the results (can operate remotely)
 *
 * @author Zhengmin Yu
 */
@Data
@ConfigurationProperties(prefix = "com.linux")
public class ExecLinux {

    private static final Log log = LogFactory.getLog(ExecLinux.class);

    /**
     * IP address
     */
    private String host;
    /**
     * Port number
     */
    private int port;
    /**
     * Username
     */
    private String username;
    /**
     * Password
     */
    private String password;

    /**
     * Constructor to initialize the Linux configuration
     */
    public ExecLinux() {
        ReadConfig readConfig = new ReadConfig(ScvdbApplication.class, "application.properties");
        log.debug("Initializing Linux configuration...");
        if (StringUtil.isEmpty(host)) {
            host = readConfig.getConfiguration("com.linux.ip");
        }
        if (NumberUtil.isEqualZero(port)) {
            port = Integer.parseInt(readConfig.getConfiguration("com.linux.port"));
        }
        if (StringUtil.isEmpty(username)) {
            username = readConfig.getConfiguration("com.linux.username");
        }
        if (StringUtil.isEmpty(password)) {
            password = readConfig.getConfiguration("com.linux.password");
        }
    }

    /**
     * Executes a command and returns the result
     *
     * @param command The command to execute
     * @return List<String> containing the command output
     * @throws IOException if an I/O error occurs
     */
    public List<String> execCommand(String command) throws IOException {
        // Create a new SSH connection
        Connection connection = new Connection(host, port);
        // Inner class for authentication
        class Authenticate {
            private Boolean login() {
                boolean success = false;
                try {
                    Connection conn = new Connection(host);
                    // Connect to the host
                    conn.connect();
                    // Check if authentication is complete
                    if (!conn.isAuthenticationComplete()) {
                        // Synchronize to prevent multiple threads from authenticating simultaneously
                        synchronized (this) {
                            // Authenticate if not already done
                            if (!conn.isAuthenticationComplete()) {
                                success = conn.authenticateWithPassword(username, password);
                            }
                        }
                    }
                } catch (IOException e) {
                    log.error("Error executing Linux command {} => {}", command, e.getMessage());
                }
                return success;
            }
        }

        // Create a list to hold the command execution results
        List<String> result = new ArrayList<>();
        // Connect to the host
        connection.connect();
        // Authenticate with password
        boolean authenticated = connection.authenticateWithPassword(username, password);
        Authenticate auth = new Authenticate();
        if (!authenticated || !auth.login()) {
            throw new IOException("Authentication failed.");
        } else {
            // Open a session for command execution
            Session session = connection.openSession();
            // Execute the command with UTF-8 encoding
            session.execCommand(command, "utf-8");
            // StreamGobbler for stdout
            StreamGobbler stdout = new StreamGobbler(session.getStdout());
            // StreamGobbler for stderr
            StreamGobbler stderr = new StreamGobbler(session.getStderr());
            // BufferedReader for stdout
            BufferedReader stdoutBuffer = new BufferedReader(new InputStreamReader(stdout));
            // BufferedReader for stderr
            BufferedReader stderrBuffer = new BufferedReader(new InputStreamReader(stderr));
            log.debug("Executing Linux command >>>> {}", command);
            // Read stdout line by line
            for (String line = stdoutBuffer.readLine(); line != null; line = stdoutBuffer.readLine()) {
                result.add(line + "\n");
            }
            // Read stderr line by line
            for (String line = stderrBuffer.readLine(); line != null; line = stderrBuffer.readLine()) {
                log.error("Linux error line >>>> {}", line);
                result.add(line);
            }
            // Close the BufferedReader instances
            stdoutBuffer.close();
            stderrBuffer.close();
            // Close the session
            session.close();
        }
        // Close the connection
        connection.close();
        return result;
    }

}
