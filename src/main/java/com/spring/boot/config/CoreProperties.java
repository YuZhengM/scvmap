package com.spring.boot.config;

import com.spring.boot.config.bean.ExecLinux;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for core properties.
 * This class is responsible for enabling configuration properties and defining beans.
 *
 * @author Zhengmin Yu
 */
@Configuration
@EnableConfigurationProperties(ExecLinux.class)
public class CoreProperties {

    /**
     * Bean definition method for ExecLinux.
     * This method creates and returns a new instance of ExecLinux.
     *
     * @return a new instance of ExecLinux
     */
    @Bean
    public ExecLinux execLinux() {
        // Create and return a new ExecLinux instance
        return new ExecLinux();
    }

}
