package com.spring.boot.util.factory.log;

/**
 * description
 *
 * @author Zhengmin Yu
 */
public interface Log {


    /**
     * log trace 级别打印
     *
     * @param message 内容说明, 支持带有 format 格式
     * @param content format 格式中的参数
     */
    void trace(String message, Object... content);

    /**
     * log debug 级别打印
     *
     * @param message 内容说明, 支持带有 format 格式
     * @param content format 格式中的参数
     */
    void debug(String message, Object... content);

    /**
     * log info 级别打印
     *
     * @param message 内容说明, 支持带有 format 格式
     * @param content format 格式中的参数
     */
    void info(String message, Object... content);

    /**
     * log warn 级别打印
     *
     * @param message 内容说明, 支持带有 format 格式
     * @param content format 格式中的参数
     */
    void warn(String message, Object... content);

    /**
     * log error 级别打印
     *
     * @param message 内容说明, 支持带有 format 格式
     * @param content format 格式中的参数
     */
    void error(String message, Object... content);

    /**
     * log error 级别打印
     *
     * @param message 内容说明, 支持带有 format 格式
     * @param e       异常信息
     * @param content format 格式中的参数
     */
    void error(String message, Exception e, Object... content);


}
