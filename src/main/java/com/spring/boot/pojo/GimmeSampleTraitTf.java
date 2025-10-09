package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "GimmeSampleTraitTf", description = "Mapping information for each sample, trait, and TF.")
public class GimmeSampleTraitTf extends BaseSnpTf implements Serializable {

    @Schema(description = "Trait ID")
    private String traitId;

    @Schema(description = "RS ID")
    private String rsId;

    @Schema(description = "TF")
    private String tf;

    @Schema(description = "Score")
    private Double score;

    @Schema(description = "PP")
    private Double pp;

    @Schema(description = "Score Mean")
    private String scoreMean;

    @Schema(description = "Motif Set")
    private String motifSet;

    @Schema(description = "Position Set")
    private String positionSet;

    @Schema(description = "Strand Set")
    private String strandSet;

    @Schema(description = "Count")
    private Integer count;

}
