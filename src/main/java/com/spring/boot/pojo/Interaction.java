package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "Interaction", description = "Mapping information for each interaction.")
public class Interaction implements Serializable {

    @Schema(description = "Trait ID")
    private String traitId;

    @Schema(description = "RS ID")
    private String rsId;

    @Schema(description = "PP")
    private Double pp;

    @Schema(description = "Gene")
    private String gene;

    @Schema(description = "Interaction 1")
    private String interaction1;

    @Schema(description = "Interaction 2")
    private String interaction2;

    @Schema(description = "Source Interaction ID")
    private String sourceInteractionId;

    @Schema(description = "Method")
    private String method;

    @Schema(description = "Tissue Cell Type")
    private String tissueCellType;

    @Schema(description = "Cell Line")
    private String cellLine;

}
