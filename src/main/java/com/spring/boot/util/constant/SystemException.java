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
    INVALID_RESOURCE(90001, "无效资源"),
    INVALID_OPERATION(90002, "无效操作"),
    ILLEGAL_PARAMETER(90003, "非法参数"),
    PARSING_ERROR(90004, "解析错误"),
    UPLOAD_ERROR(90005, "上传错误"),
    TIMEOUT_ERROR(90006, "超时错误"),
    NULL_POINTER_ERROR(90007, "空指针错误"),
    OBVIOUS_BUG_ERROR(90008, "明显 BUG 错误"),
    SERVICE_DISCONNECT(90009, "服务断开"),
    AUTHENTICATION_ERROR(90010, "认证错误"),
    THREAD_TIMEOUT_ERROR(90011, "线程超时"),
    BEAN_MISSING_ERROR(90011, "Bean 缺失"),
    /**
     * 代表疑似被攻击: 内容填写疑似被攻击会返回给前端页面
     */
    SUSPECTED_ATTACKED(90099, "不可能出现");

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
