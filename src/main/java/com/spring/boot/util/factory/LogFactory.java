package com.spring.boot.util.factory;

import com.spring.boot.util.factory.log.Log;
import com.spring.boot.util.factory.log.helpers.LogContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log 日志相关的公共类
 * <pre>
 *     1. log 日志级别: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL
 *        例如: 日志级别定义为 INFO 其中比 INFO 级别大 (WARN, ERROR) 的方可打印出来, 级别小 (DEBUG, TRACE) 的不支持打印.
 *     2. 开发环境-dev: DEBUG 级别
 *        测试环境-test: INFO 级别
 *        生产黄健-prod: INFO 级别
 *     3. 其中 primary_common 类中日志级别全用 DEBUG, 公共类只支持开发查看 (只有本类除外)
 *     4. 该类中当本级别类型不支持时候, 自动打印高一个级别, 进行循环.
 *        其中注意: 打印高一个级别的时候只支持打印 message 中 format 格式的本身, 不会将 content 中的实质内容打印出来.
 * </pre>
 *
 * @author Zhengmin Yu
 */
public class LogFactory {

    /**
     * 自定义的 LOG, 再 logger 上的扩展
     */
    private static Log log;
    private static Logger logger;

    public LogFactory() {
    }

    /**
     * Return a logger named corresponding to the class passed as parameter
     *
     * @param clazz 类
     */
    public static Log getLog(Class<?> clazz) {
        // 嵌入 slf4j 的 Logger
        logger = LoggerFactory.getLogger(clazz);
        return getLogContent();
    }

    /**
     * 核心操作
     * <pre>
     *     0. 嵌入 slf4j 的 Logger
     *     1. 初始化日志工厂
     *     2. 初始化自定义的 Log 类 (对嵌入 slf4j 的 Logger 进行加载)
     *     3. 自定义的 Log 所加载到的日志添加到工厂中
     *     4. 返回自定义的 Log 实现响应的接口操作
     * </pre>
     *
     * @return Log
     */
    private static Log getLogContent() {
        // 初始化自定义的 Log 类
        initLog();
        // 返回自定义的 Log 实现响应的接口操作
        return log;
    }

    /**
     * 初始化日志 Log
     */
    private static void initLog() {
        log = new LogContent(logger);
    }


}
