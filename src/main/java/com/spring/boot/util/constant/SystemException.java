package com.spring.boot.util.constant;

import lombok.Getter;

/**
 * 异常集合
 *
 * @author Zhengmin Yu
 */
@Getter
public enum SystemException {

    /**
     * 异常信息
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
     * 代表疑似被攻击: 内容填写疑似被攻击会返回给前端页面
     */
    SUSPECTED_ATTACKED(90099, "It's impossible to occur");

    /**
     * 代码
     */
    private final int code;
    /**
     * 服务名
     */
    private final String exception;

    SystemException(int code, String exception) {
        this.code = code;
        this.exception = exception;
    }

}
