package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "GimmeSampleTf", description = "Mapping information for each sample and TF.")
public class GimmeSampleTf implements Serializable {

    @Schema(description = "Sample ID")
    private String sampleId;

    @Schema(description = "TF")
    private String tf;

    @Schema(description = "Score")
    private Double score;

    @Schema(description = "Score Mean")
    private Double scoreMean;

}
