package com.spring.boot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@Schema(description = "Parameters for the \"Analyzing variant-to-function mapping with TFs\" analysis function")
public class AnalysisTfVO extends AnalysisElementVO implements Serializable {

    @Schema(description = "Log2 fold change value.", example = "1.0")
    private Double log2FoldChange;

    @Schema(description = "P-value of the analysis.", example = "1.0E-2")
    private Double pvalue;
    
    @Schema(description = "Adjusted P-value of the analysis.", example = "1.0E-2")
    private Double adjustedPValue;
    
    @Schema(description = "Q-value related to the trait.", example = "0.05")
    private Double qvalueTrait;
    
    @Schema(description = "P-value related to the trait.", example = "0.05")
    private Double pvalueTrait;

}
