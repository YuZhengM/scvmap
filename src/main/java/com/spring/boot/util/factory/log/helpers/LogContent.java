package com.spring.boot.util.factory.log.helpers;

import com.spring.boot.util.factory.log.Log;
import org.slf4j.Logger;
import org.springframework.lang.Nullable;

/**
 * 实现 Log 的方法
 *
 * @author Zhengmin Yu
 * @see LogContent
 * @see com.spring.boot.util.factory.LogFactory
 */
public class LogContent implements Log {

    /**
     * 由 <code>slf4j</code> 中 <code>getLogger(Class<?> clazz)</code> 中产生的
     */
    Logger logger;

    public LogContent(@Nullable Logger logger) {
        this.logger = logger;
    }

    @Override
    public void trace(String message, Object... content) {
        if (logger.isTraceEnabled()) {
            logger.trace(message, content);
        } else {
            debug("不支持打印 TRACE 级别日志, message = {}", message);
        }
    }

    @Override
    public void debug(String message, Object... content) {
        if (logger.isDebugEnabled()) {
            logger.debug(message, content);
        } else {
            info("不支持打印 DEBUG 级别日志, message = [{}]", message);
        }
    }

    @Override
    public void info(String message, Object... content) {
        if (logger.isInfoEnabled()) {
            logger.info(message, content);
        } else {
            warn("不支持打印 INFO 级别日志, message = [{}]", message);
        }
    }

    @Override
    public void warn(String message, Object... content) {
        if (logger.isWarnEnabled()) {
            logger.warn(message, content);
        } else {
            error("不支持打印 WARN 级别日志, message = [{}]", message);
        }
    }

    @Override
    public void error(String message, Object... content) {
        if (logger.isErrorEnabled()) {
            logger.error(message, content);
        }
    }

    @Override
    public void error(String message, Exception e, Object... content) {
        if (logger.isErrorEnabled()) {
            logger.error(message + " Exception: {}", content, e);
        }
    }

}
