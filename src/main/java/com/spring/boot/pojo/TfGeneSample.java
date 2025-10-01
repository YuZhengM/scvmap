package com.spring.boot.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TfGeneSample implements Serializable {

    private String tf;

    private String scoreMean;

    private Integer count;

    private String gene;

    private String tfPeak;

    private String genePeak;

    private String score;

}
