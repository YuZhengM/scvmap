package com.spring.boot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.spring.boot.util.constant.ApplicationConstant.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "RegulationGraphVO", description = "Returns Gene/TF-hub regulatory network data based on different thresholds.")
public class RegulationGraphVO implements Serializable {

    @Schema(description = "ID of the sample.", example = SAMPLE_EXAMPLE)
    private String sampleId;

    @Schema(description = "ID of the trait.", example = TRAIT_EXAMPLE)
    private String traitId;

    @Schema(description = "Cell type.", example = CELL_TYPE_EXAMPLE)
    private String cellType;

    @Schema(description = "Thresholds (Gene).")
    private AnalysisGeneVO analysisGene;

    @Schema(description = "Thresholds (TF).")
    private AnalysisTfVO analysisTf;

    @Schema(description = "Top count value.", example = "200")
    private Integer topCount;

    @Schema(description = "Indicates whether to return a network containing only key genes or TFs.", example = "true")
    private Boolean isCore;

}
