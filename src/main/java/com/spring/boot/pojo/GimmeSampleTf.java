package com.spring.boot.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GimmeSampleTf implements Serializable {

    private String sampleId;
    private String tf;
    private String score;
    private String scoreMean;

}
