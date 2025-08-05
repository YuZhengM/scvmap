package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * t_trait_enrich_gchromvar_0
 */
@ToString
@Data
@Schema(name = "TraitEnrich", description = "Mapping table between traits and single-cell samples")
public class TraitEnrich implements Serializable {

    @Schema(description = "Trait ID")
    private String traitId;
    
    @Schema(description = "Sample ID")
    private String sampleId;

    @Schema(description = "Tissue type")
    private String tissueType;

    @Schema(description = "Label")
    private String label;

    @Schema(description = "Sample index")
    private Integer sampleIndex;

    @Serial
    private static final long serialVersionUID = 1L;
}