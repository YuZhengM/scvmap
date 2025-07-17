package com.spring.boot.util.util.bean;

import org.springframework.context.ApplicationContext;

/**
 * description
 *
 * @author Zhengmin Yu
 */
public enum AppContext {

    /**
     * 实例
     */
    INSTANCE;

    public static AppContext getInstance() {
        return INSTANCE;
    }

    private ApplicationContext applicationContext;

    /**
     * Default constructor
     */
    AppContext() {
    }

    /**
     *
     */
    public void setContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * @return applicationContext
     */
    public ApplicationContext getContext() {
        return applicationContext;
    }
}
