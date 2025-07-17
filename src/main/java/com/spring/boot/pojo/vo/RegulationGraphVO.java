package com.spring.boot.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegulationGraphVO implements Serializable {

    private String sampleId;

    private String traitId;

    private String cellType;

    private AnalysisGeneVO analysisGene;

    private AnalysisTfVO analysisTf;

    private Integer topCount;

    private Boolean isCore;

}
