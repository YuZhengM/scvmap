package com.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Zheng-Min Yu
 */
@MapperScan("com.spring.boot.mapper")
@EnableCaching
@SpringBootApplication
public class ScvdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScvdbApplication.class, args);
    }

}
