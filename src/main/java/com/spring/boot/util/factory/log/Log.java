package com.spring.boot.util.factory.log;

/**
 * This interface is used to define log-related methods.
 *
 * @author Zhengmin Yu
 */
public interface Log {

    /**
     * Print log at trace level.
     *
     * @param message Content description, supporting format patterns.
     * @param content Parameters in the format pattern.
     */
    void trace(String message, Object... content);

    /**
     * Print log at debug level.
     *
     * @param message Content description, supporting format patterns.
     * @param content Parameters in the format pattern.
     */
    void debug(String message, Object... content);

    /**
     * Print log at info level.
     *
     * @param message Content description, supporting format patterns.
     * @param content Parameters in the format pattern.
     */
    void info(String message, Object... content);

    /**
     * Print log at warn level.
     *
     * @param message Content description, supporting format patterns.
     * @param content Parameters in the format pattern.
     */
    void warn(String message, Object... content);

    /**
     * Print log at error level.
     *
     * @param message Content description, supporting format patterns.
     * @param content Parameters in the format pattern.
     */
    void error(String message, Object... content);

    /**
     * Print log at error level.
     *
     * @param message Content description, supporting format patterns.
     * @param e       Exception information.
     * @param content Parameters in the format pattern.
     */
    void error(String message, Exception e, Object... content);

}
