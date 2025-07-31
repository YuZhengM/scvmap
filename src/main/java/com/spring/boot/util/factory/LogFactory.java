package com.spring.boot.util.factory;

import com.spring.boot.util.factory.log.Log;
import com.spring.boot.util.factory.log.helpers.LogContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A common class related to log functionality.
 * <pre>
 *     1. Log levels: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL
 *        For example, if the log level is set to INFO, only levels higher than INFO (WARN, ERROR) will be printed,
 *        while lower levels (DEBUG, TRACE) will not be printed.
 *     2. Development environment - dev: DEBUG level
 *        Test environment - test: INFO level
 *        Production environment - prod: INFO level
 *     3. In the primary_common class, all log levels are set to DEBUG. Common classes are only visible during development (this class is an exception).
 *     4. If the current log level does not support printing, it will automatically print at the next higher level in a loop.
 *        Note: When printing at a higher level, only the format of the message will be printed, and the actual content in the 'content' will not be printed.
 * </pre>
 *
 * @author Zhengmin Yu
 */
public class LogFactory {

    /**
     * Custom LOG, an extension of the logger.
     */
    private static Log log;
    private static Logger logger;

    public LogFactory() {
    }

    /**
     * Return a logger named corresponding to the class passed as parameter
     *
     * @param clazz The class
     */
    public static Log getLog(Class<?> clazz) {
        // Embed the slf4j Logger
        logger = LoggerFactory.getLogger(clazz);
        return getLogContent();
    }

    /**
     * Core operations
     * <pre>
     *     0. Embed the slf4j Logger
     *     1. Initialize the log factory
     *     2. Initialize the custom Log class (load the embedded slf4j Logger)
     *     3. Add the logs loaded by the custom Log to the factory
     *     4. Return the custom Log that implements the corresponding interface operations
     * </pre>
     *
     * @return Log instance
     */
    private static Log getLogContent() {
        // Initialize the custom Log class
        initLog();
        // Return the custom Log that implements the corresponding interface operations
        return log;
    }

    /**
     * Initialize the log.
     */
    private static void initLog() {
        log = new LogContent(logger);
    }

}
