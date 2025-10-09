package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "CiceroSampleTraitGene", description = "Mapping information for each single-cell sample, trait, and gene.")
public class CiceroSampleTraitGene extends BaseSnpGene implements Serializable {

    @Schema(description = "Trait ID")
    private String traitId;

    @Schema(description = "Trait Peak")
    private String traitPeak;

    @Schema(description = "Gene Peak")
    private String genePeak;

    @Schema(description = "Score")
    private Double score;

    @Schema(description = "Position")
    private String position;

    @Schema(description = "PP")
    private Double pp;

    @Schema(description = "Trait Abbreviation")
    private String traitAbbr;

    @Schema(description = "Weight")
    private Double weight;

}
