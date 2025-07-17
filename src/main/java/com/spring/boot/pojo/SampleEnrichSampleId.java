package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * t_sample_enrich_gchromvar_sample_id_1
 */
@ToString
@Data
public class SampleEnrichSampleId implements Serializable {
    private String traitId;

    private String traitCode;

    private String traitAbbr;

    private String trait;

    private String sourceName;

    private Integer traitIndex;

    @Serial
    private static final long serialVersionUID = 1L;
}