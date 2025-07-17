package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

/**
 * t_sample_tf_0
 */
@Data
public class SampleTfChunk implements Serializable {
    private String sampleId;

    private String tf;

    private Double pValue;

    private Double adjustedPValue;

    private Double log2FoldChange;

    @Serial
    private static final long serialVersionUID = 1L;
}