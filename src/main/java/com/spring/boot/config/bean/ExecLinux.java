package com.spring.boot.config.bean;

import com.spring.boot.ScvdbApplication;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import com.spring.boot.util.model.LinuxResult;
import com.spring.boot.util.util.ApplicationUtil;
import com.spring.boot.util.util.NumberUtil;
import com.spring.boot.util.util.StringUtil;
import com.spring.boot.util.util.config.ReadConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.IOException;

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
     * Public Key
     */
    private String keyFile;

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
        if (StringUtil.isEmpty(keyFile)) {
            keyFile = readConfig.getConfiguration("com.linux.keyFile");
        }
    }

    /**
     * Executes a command and returns the result
     *
     * @param command The command to execute
     * @return List<String> containing the command output
     * @throws IOException if an I/O error occurs
     */
    public LinuxResult execCommand(String command) throws IOException {
        return ApplicationUtil.execLinuxCommand(host, port, username, password, keyFile, command);
    }

}
