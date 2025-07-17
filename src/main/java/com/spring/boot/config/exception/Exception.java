package com.spring.boot.config.exception;

import com.spring.boot.util.constant.ResultCode;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import com.spring.boot.util.model.Result;
import com.spring.boot.util.util.result.ResultUtil;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application.
 * This class handles all exceptions thrown by the controllers and returns a standardized result.
 *
 * @author Zhengmin Yu
 * @version V1.0.0
 * @date 2021/11/20 0020 10:46
 * @since 1.0
 */
@RestControllerAdvice
public class Exception {

    // Initialize the logger for this class
    private static final Log log = LogFactory.getLog(Exception.class);

    /**
     * Handle exceptions of type java.lang.Exception.
     * This method logs the exception and returns a Result object with an appropriate error code and message.
     *
     * @param e The exception that was thrown
     * @return A Result object containing the error details
     */
    @ExceptionHandler(java.lang.Exception.class)
    public Result<String> exception(java.lang.Exception e) {
        // Check if the exception is a ClientAbortException
        if (e instanceof ClientAbortException) {
            // Log a warning that the client disconnected
            log.warn("Client disconnected: {}", e.getMessage());
            // Return a failure result with a specific code for client disconnection
            return ResultUtil.fail(e.getMessage(), ResultCode.CLIENT_DISCONNECTED);
        }
        // Log the exception as an error
        log.error("Unified exception handling: {}", e, e.getMessage());
        // Return a failure result with a general invalid operation code
        return ResultUtil.fail(e.getMessage(), ResultCode.INVALID_OPERATION);
    }

}
