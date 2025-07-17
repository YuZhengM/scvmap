package com.spring.boot.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class AnalysisTfVO extends AnalysisElementVO implements Serializable {

    private Double log2FoldChange;
    private Double pvalue;
    private Double adjustedPValue;
    private Double qvalueTrait;
    private Double pvalueTrait;

}
