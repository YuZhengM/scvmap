package com.spring.boot.util.constant;

import lombok.Getter;

/**
 * description
 *
 * @author Zhengmin Yu
 */
@Getter
public enum ServiceEnum {

    /**
     * 父模块服务
     */
    ROOT(0, CommonCode.PROJECT_EN_L_NAME + "_root"),
    API(1, CommonCode.PROJECT_EN_L_NAME + "_api"),
    COMMON(2, CommonCode.PROJECT_EN_L_NAME + "_common"),
    EUREKA(3, CommonCode.PROJECT_EN_L_NAME + "_eureka"),
    GATEWAY(4, CommonCode.PROJECT_EN_L_NAME + "_gateway"),
    SERVICE(5, CommonCode.PROJECT_EN_L_NAME + "_service"),
    USER(6, CommonCode.PROJECT_EN_L_NAME + "_user"),
    USER_API(7, CommonCode.PROJECT_EN_L_NAME + "_user_api"),
    LOG(8, CommonCode.PROJECT_EN_L_NAME + "_log"),
    LOG_API(9, CommonCode.PROJECT_EN_L_NAME + "_log_api");

    /**
     * 代码
     */
    private final int code;
    /**
     * 服务名
     */
    private final String serviceName;

    ServiceEnum(int code, String serviceName) {
        this.code = code;
        this.serviceName = serviceName;
    }

}
