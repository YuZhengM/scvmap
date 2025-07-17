package com.spring.boot.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GeneEnrichmentVO implements Serializable {

    private String sampleId;
    private String traitId;
    private String geneSet;
    private String cellType;
    private Double value;
    private String genome;

}
