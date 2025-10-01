package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * t_sample_enrich_gchromvar_sample_id_1
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@Schema(name = "SampleEnrichSampleId", description = "Table for mapping single-cell sample to traits")
public class SampleEnrichSampleId extends TraitEnrich implements Serializable {

    @Schema(description = "Trait ID")
    private String traitId;

    @Schema(description = "Trait code")
    private String traitCode;

    @Schema(description = "Trait abbreviation")
    private String traitAbbr;

    @Schema(description = "Trait name")
    private String trait;

    @Schema(description = "Source name")
    private String sourceName;

    @Schema(description = "Trait index")
    private Integer traitIndex;

    @Serial
    private static final long serialVersionUID = 1L;
}