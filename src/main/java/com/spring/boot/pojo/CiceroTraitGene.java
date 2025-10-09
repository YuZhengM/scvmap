package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "CiceroTraitGene", description = "Mapping information for each trait and gene.")
public class CiceroTraitGene implements Serializable {

    @Schema(description = "Trait ID")
    private String traitId;
    @Schema(description = "Gene")
    private String gene;
    @Schema(description = "Score")
    private Double score;

}
