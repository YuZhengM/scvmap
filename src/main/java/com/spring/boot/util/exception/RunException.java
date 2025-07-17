package com.spring.boot.util.exception;

import com.spring.boot.util.constant.SystemException;

/**
 * 异常处理
 *
 * @author Zhengmin Yu
 */
public class RunException extends RuntimeException {

    /**
     * 异常信息
     *
     * @param systemException 自定义异常
     */
    public RunException(SystemException systemException) {
        super(systemException.getException());
    }

}
