package com.spring.boot.util.constant;

import lombok.Getter;

/**
 * Collection of exceptions
 *
 * @author Zhengmin Yu
 */
@Getter
public enum SystemException {

    /**
     * Exception information
     */
    INVALID_RESOURCE(90001, "Invalid resource"),
    INVALID_OPERATION(90002, "Invalid operation"),
    ILLEGAL_PARAMETER(90003, "Illegal parameter"),
    PARSING_ERROR(90004, "Parse error"),
    UPLOAD_ERROR(90005, "Upload error"),
    TIMEOUT_ERROR(90006, "Timeout error"),
    NULL_POINTER_ERROR(90007, "Null pointer"),
    OBVIOUS_BUG_ERROR(90008, "Obvious BUG error"),
    SERVICE_DISCONNECT(90009, "Service disconnection"),
    AUTHENTICATION_ERROR(90010, "Authentication error"),
    THREAD_TIMEOUT_ERROR(90011, "Thread timeout"),
    BEAN_MISSING_ERROR(90011, "Bean missing"),
    /**
     * Indicates a suspected attack: The content indicating a suspected attack will be returned to the front-end page
     */
    SUSPECTED_ATTACKED(90099, "It's impossible to occur");

    /**
     * Code
     */
    private final int code;
    /**
     * Exception message
     */
    private final String exception;

    SystemException(int code, String exception) {
        this.code = code;
        this.exception = exception;
    }

}
