package com.spring.boot.pojo.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class OnLineVO implements Serializable {

    private String jobId;

    private String userIp;

    private String genome;

    private String layer;

    private String email;

}
