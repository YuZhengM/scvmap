package com.spring.boot.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CiceroSampleGene implements Serializable {

    private String sampleId;
    private String gene;
    private String score;

}
