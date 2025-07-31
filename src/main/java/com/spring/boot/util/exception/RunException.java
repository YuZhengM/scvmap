package com.spring.boot.util.exception;

import com.spring.boot.util.constant.SystemException;

/**
 * Exception handling
 *
 * @author Zhengmin Yu
 */
public class RunException extends RuntimeException {

    /**
     * Exception information
     *
     * @param systemException Custom exception
     */
    public RunException(SystemException systemException) {
        super(systemException.getException());
    }

}
