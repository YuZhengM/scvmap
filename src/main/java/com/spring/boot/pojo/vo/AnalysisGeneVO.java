package com.spring.boot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@Schema(description = "Parameters for the \"Analyzing variant-to-function mapping with genes\" analysis function")
public class AnalysisGeneVO extends AnalysisElementVO implements Serializable {

    @Schema(description = "Log2 fold change value.", example = "1.0")
    private Double log2FoldChange;

    @Schema(description = "Adjusted p-value.", example = "1.0E-2")
    private Double adjustedPValue;

    @Schema(description = "P-value.", example = "1.0E-2")
    private Double pvalue;

    @Schema(description = "Minimum value.", example = "1")
    private Integer min;

    @Schema(description = "Method.", example = "cicero")
    private String strategy;

    @Schema(description = "Co-score.", example = "0.1")
    private Double coScore;

    @Schema(description = "P-value for trait.", example = "0.05")
    private Double pvalueTrait;

}
