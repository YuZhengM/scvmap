package com.spring.boot.util.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Bean retrieval utility class
 * Provides static methods to access Spring managed beans
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
     * Get application context
     *
     * @return ApplicationContext instance
     */
    private static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * Get Bean by name
     *
     * @param name Bean name
     * @return Bean instance
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * Get Bean by class type
     *
     * @param clazz Bean class type
     * @param <T> Generic type
     * @return Typed Bean instance
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * Get specified Bean by name and class type
     *
     * @param name Bean name
     * @param clazz Bean class type
     * @param <T> Generic type
     * @return Typed Bean instance
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
