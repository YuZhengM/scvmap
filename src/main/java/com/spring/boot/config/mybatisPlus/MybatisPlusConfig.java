package com.spring.boot.config.mybatisPlus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Configuration class for MybatisPlus and PageHelper plugins.
 * This class defines beans for pagination interceptors to be used with MybatisPlus and PageHelper.
 *
 * @author Zhengmin Yu
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * Bean for MybatisPlus pagination interceptor.
     * This interceptor adds support for pagination in queries using MybatisPlus.
     *
     * @return an instance of MybatisPlusInterceptor configured for MySQL
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // Create a new instance of MybatisPlusInterceptor
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // Add a pagination inner interceptor for MySQL database type
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // Return the configured interceptor
        return mybatisPlusInterceptor;
    }

    /**
     * Bean for PageHelper pagination interceptor.
     * This interceptor adds support for pagination in queries using PageHelper.
     *
     * @return an instance of PageInterceptor configured for MySQL
     */
    @Bean
    public PageInterceptor pageInterceptor() {
        // Create a new instance of PageInterceptor
        PageInterceptor pageInterceptor = new PageInterceptor();
        // Create a new Properties object to hold configuration settings
        Properties properties = new Properties();
        // Set the dialect property to MySQL
        properties.setProperty("helperDialect", "mysql");
        // Set the properties on the page interceptor
        pageInterceptor.setProperties(properties);
        // Return the configured interceptor
        return pageInterceptor;
    }
}
