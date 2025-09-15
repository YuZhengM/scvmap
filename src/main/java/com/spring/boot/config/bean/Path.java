package com.spring.boot.config.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration class for path properties.
 * This class is used to inject path configuration from application properties.
 *
 * @author Zhengmin Yu
 */
@Component
@ConfigurationProperties(prefix = "com.path")
@ToString
@Data
public class Path {

    /**
     * The working path of the project.
     * This field is mapped to the 'com.path.workPath' property in the application configuration.
     */
    private String workPath;

    private String execFilePath;

}
