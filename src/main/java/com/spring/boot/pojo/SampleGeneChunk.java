package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

/**
 * t_sample_gene_0
 */
@Data
public class SampleGeneChunk implements Serializable {

    private String sampleId;

    private String gene;

    private String adjustedPValue;

    private Double log2FoldChange;

    private String pValue;

    @Serial
    private static final long serialVersionUID = 1L;
}
