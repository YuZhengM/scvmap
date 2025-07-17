package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * t_trait_enrich_gchromvar_0
 */
@ToString
@Data
public class TraitEnrich implements Serializable {
    private String traitId;

    private String sampleId;

    private String tissueType;

    private String label;

    private Integer sampleIndex;

    @Serial
    private static final long serialVersionUID = 1L;
}