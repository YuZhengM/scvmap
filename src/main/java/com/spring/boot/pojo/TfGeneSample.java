package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "TfGeneSample", description = "Mapping information for each TF, gene, and sample.")
public class TfGeneSample implements Serializable {

    @Schema(description = "TF")
    private String tf;

    @Schema(description = "Score Mean")
    private Double scoreMean;

    @Schema(description = "Count")
    private Integer count;

    @Schema(description = "Gene")
    private String gene;

    @Schema(description = "TF Peak")
    private String tfPeak;

    @Schema(description = "Gene Peak")
    private String genePeak;

    @Schema(description = "Score")
    private Double score;

}
