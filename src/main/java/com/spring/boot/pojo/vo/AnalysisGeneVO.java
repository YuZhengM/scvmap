package com.spring.boot.pojo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class AnalysisGeneVO extends AnalysisElementVO implements Serializable {

    private Double log2FoldChange;
    private Double adjustedPValue;
    private Double pvalue;
    private Integer min;
    private Double pvalueTrait;

}
