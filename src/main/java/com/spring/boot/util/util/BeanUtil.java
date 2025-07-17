package com.spring.boot.util.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * 获取 Bean 的操作
 *
 * @author Zhengmin Yu
 */
@Component
@Lazy(value = false)
public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        if (BeanUtil.applicationContext == null) {
            BeanUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取 applicationContext
     *
     * @return ApplicationContext
     */
    private static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过 name 获取 Bean.
     *
     * @param name name
     * @return Object
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过 class 获取 Bean.
     *
     * @param clazz class
     * @param <T>   T
     * @return T
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过 name, 以及 Class 返回指定的 Bean
     *
     * @param name  name
     * @param clazz Class
     * @param <T>   T
     * @return T
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
