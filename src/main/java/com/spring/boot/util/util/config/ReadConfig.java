package com.spring.boot.util.util.config;

import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import com.spring.boot.util.util.ClassUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Read Config file information
 *
 * @author Zhengmin Yu
 */
public class ReadConfig {

    private static final Log log = LogFactory.getLog(ReadConfig.class);

    /**
     * Root path symbol of resources
     */
    private static final String FILE_PATH_PREFIX = "/";

    private final Class<?> clazz;
    private final String filename;

    /**
     * Initialize the class and file name
     *
     * @param clazz    Class
     * @param filename Configuration file name
     */
    public ReadConfig(Class<?> clazz, String filename) {
        this.clazz = clazz;
        this.filename = filename;
    }

    /**
     * Read parameters from the configuration file
     * <p>
     * The contextConfig file stream is closed after properties.load(contextConfig); is executed.
     * This means that every time a parameter is read from the configuration file,
     * the file needs to be loaded again. contextConfig cannot be used as a macro.
     * </p>
     *
     * @param props Parameter
     * @return Parameter value
     */
    public String getConfiguration(String props) {
        // Load the file
        InputStream contextConfig = clazz.getResourceAsStream(FILE_PATH_PREFIX + filename);
        // Initialize the properties to read from the configuration file
        Properties properties = new Properties();

        // Read the parameter from the configuration file
        try {
            properties.load(contextConfig);
        } catch (IOException e) {
            log.error("[before]: Failed to form file or folder. {}, {}", e.getMessage(), e.getStackTrace());
        } finally {
            try {
                if (ClassUtil.isNotEmpty(contextConfig)) {
                    assert contextConfig != null;
                    contextConfig.close();
                }
            } catch (IOException e) {
                log.error("[before]: Failed to form file or folder. {}, {}", e.getMessage(), e.getStackTrace());
            }
        }
        return properties.getProperty(props);
    }
}
