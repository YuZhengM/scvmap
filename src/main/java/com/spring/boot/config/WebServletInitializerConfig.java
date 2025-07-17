package com.spring.boot.config;

import com.spring.boot.ScvdbApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Servlet initializer configuration for the Spring Boot application.
 * This class extends SpringBootServletInitializer to configure the application when deployed in a servlet container.
 *
 * @author Zhengmin Yu
 */
public class WebServletInitializerConfig extends SpringBootServletInitializer {

    /**
     * Configure the application.
     * This method is overridden to specify the source class (ScvdbApplication) that will be used to launch the Spring Boot application.
     *
     * @param application the SpringApplicationBuilder to configure
     * @return the configured SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // Specify the source class for the Spring Boot application
        return application.sources(ScvdbApplication.class);
    }

}
