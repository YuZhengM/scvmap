package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "GimmeTraitTf", description = "Mapping information for each trait and TF.")
public class GimmeTraitTf implements Serializable {

    @Schema(description = "Trait ID")
    private String traitId;

    @Schema(description = "TF")
    private String tf;

    @Schema(description = "Score")
    private Double score;

    @Schema(description = "Score Mean")
    private Double scoreMean;

}
