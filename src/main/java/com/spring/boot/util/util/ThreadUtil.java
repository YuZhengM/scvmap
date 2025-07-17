package com.spring.boot.util.util;

import com.spring.boot.util.constant.SystemException;
import com.spring.boot.util.exception.RunException;

import java.util.concurrent.*;

/**
 * 线程相关的公共类
 *
 * @author Zhengmin Yu
 */
public class ThreadUtil {


    /**
     * 默认等到排列时间
     */
    private static final int DEFAULT_SLEEP_TIME = 500;

    /**
     * 创建线程池
     *
     * @param corePoolSize    核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime   超过 corePoolSize 线程数量的线程最大空闲时间
     * @param capacity        等待执行任务的个数
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor create(int corePoolSize, int maximumPoolSize, long keepAliveTime, int capacity) {
        // 以秒为时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        // 创建工作队列, 用于存放提交的等待执行任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(capacity);
        // 创建线程池
        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    /**
     * 创建线程池
     *
     * @param corePoolSize    核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime   超过 corePoolSize 线程数量的线程最大空闲时间
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor create(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        // 以秒为时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        // 创建工作队列, 用于存放提交的等待执行任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        // 创建线程池
        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    /**
     * 创建线程池
     *
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor create() {
        // 以秒为时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        // 创建工作队列, 用于存放提交的等待执行任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(4);
        // 创建线程池
        return new ThreadPoolExecutor(
                5,
                7,
                3,
                unit,
                workQueue,
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    /**
     * 每个任务提交后休眠 millis ms 再提交下一个任务, 用于保证提交顺序
     *
     * @param millis 毫秒
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 每个任务提交后休眠 500ms 再提交下一个任务, 用于保证提交顺序
     */
    public static void sleep() {
        sleep(DEFAULT_SLEEP_TIME);
    }

    /**
     * 关闭线程池
     *
     * @param threadPoolExecutor 线程池
     */
    public static void close(ThreadPoolExecutor threadPoolExecutor) {
        if (ClassUtil.isEmpty(threadPoolExecutor)) {
            threadPoolExecutor.shutdown();
        }
    }

    /**
     * 获取结果
     *
     * @param future  future
     * @param timeout 超时时间(单位为 s)
     * @return T
     */
    public static <T> T getResult(Future<T> future, int timeout) throws RunException, ExecutionException, InterruptedException, TimeoutException {
        // 循环获取结果
        while (true) {
            // 判断是否完成
            if (future.isDone()) {
                return future.get(timeout, TimeUnit.SECONDS);
            }
            // 判断是否取消
            if (future.isCancelled()) {
                throw new RunException(SystemException.THREAD_TIMEOUT_ERROR);
            }
            sleep(2 * DEFAULT_SLEEP_TIME);
        }

    }


    /**
     * 获取结果
     * <p>默认超时时间为 10 秒</p>
     *
     * @param future future
     * @return T
     */
    public static <T> T getResult(Future<T> future) throws RunException, ExecutionException, InterruptedException, TimeoutException {
        return getResult(future, 10);
    }

}
