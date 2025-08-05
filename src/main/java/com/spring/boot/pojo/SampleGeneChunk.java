package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * t_sample_gene_0
 */
@Data
@Schema(name = "SampleGeneChunk", description = "Table for mapping single-cell samples to genes")
public class SampleGeneChunk implements Serializable {

    @Schema(description = "Sample ID")
    private String sampleId;

    @Schema(description = "Gene name")
    private String gene;

    @Schema(description = "Adjusted P-value")
    private String adjustedPValue;

    @Schema(description = "Log2(Fold change)")
    private Double log2FoldChange;

    @Schema(description = "P-value")
    private String pValue;

    @Serial
    private static final long serialVersionUID = 1L;
}
