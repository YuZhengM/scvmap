package com.spring.boot.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SampleDataBrowseVO implements Serializable {

    private String healthType;
    private String tissueType;
    private String cellType;

}
