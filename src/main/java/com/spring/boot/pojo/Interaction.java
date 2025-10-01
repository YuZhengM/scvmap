package com.spring.boot.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Interaction implements Serializable {

    private String traitId;
    private String rsId;
    private Double pp;
    private String gene;
    private String interaction1;
    private String interaction2;
    private String sourceInteractionId;
    private String method;
    private String tissueCellType;
    private String cellLine;

}
