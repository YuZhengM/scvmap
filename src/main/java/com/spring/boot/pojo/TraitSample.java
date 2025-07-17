package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * t_trait_sample_scavenge
 */
@Data
public class TraitSample implements Serializable {
    private String traitId;

    private String sampleId;

    private String traitCode;

    private String traitAbbr;

    private String trait;

    private String sourceName;

    private String tissueType;

    private String label;

    private Integer traitIndex;

    private Integer sampleIndex;

    @Serial
    private static final long serialVersionUID = 1L;
}