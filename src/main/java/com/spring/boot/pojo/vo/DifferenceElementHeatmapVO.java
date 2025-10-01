package com.spring.boot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

import static com.spring.boot.util.constant.ApplicationConstant.SAMPLE_EXAMPLE;

@ToString
@Data
@Schema(name = "DifferenceElementHeatmapVO", description = "Value object for difference element heatmap.")
public class DifferenceElementHeatmapVO implements Serializable {

    @Schema(description = "Sample ID", example = SAMPLE_EXAMPLE)
    private String sampleId;

    @Schema(description = "Metadata", example = "cell_type")
    private String metadata;

    @Schema(description = "Top count", example = "20")
    private Integer topCount;

    @Schema(description = "Log2 fold change", example = "1.0")
    private Double log2FoldChange;

    @Schema(description = "Names of genes or TFs", example = "GLI1,RCC2")
    private String names;

    @Schema(description = "Value type", example = "Score")
    private String valueType;

}
