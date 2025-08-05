package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * t_sample_tf_0
 */
@Data
@Schema(name = "SampleTfChunk", description = "Table for mapping single-cell samples to TFs")
public class SampleTfChunk implements Serializable {

    @Schema(description = "Sample ID")
    private String sampleId;

    @Schema(description = "TF name")
    private String tf;

    @Schema(description = "P-value")
    private Double pValue;

    @Schema(description = "Adjusted P-value")
    private Double adjustedPValue;

    @Schema(description = "Log2(Fold change)")
    private Double log2FoldChange;

    @Serial
    private static final long serialVersionUID = 1L;
}
