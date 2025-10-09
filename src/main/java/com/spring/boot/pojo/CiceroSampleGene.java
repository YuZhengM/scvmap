package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "CiceroSampleGene", description = "Mapping information for each single-cell sample and gene.")
public class CiceroSampleGene implements Serializable {

    @Schema(description = "Sample ID")
    private String sampleId;
    
    @Schema(description = "Gene")
    private String gene;
    
    @Schema(description = "Score")
    private String score;

}
