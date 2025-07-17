package com.spring.boot.pojo.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class DifferenceElementHeatmapVO implements Serializable {

    private String sampleId;
    private Integer topCount;
    private Double log2FoldChange;
    private String names;

}
