package com.spring.boot.pojo.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class OnLineResultVO implements Serializable {

    private String jobId;

}
