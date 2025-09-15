package com.spring.boot.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class CiceroSampleTraitGene extends BaseSnpGene implements Serializable {

    private String traitId;

    private String traitPeak;

    private String genePeak;

    private Double score;

    private String position;

    private Double pp;

    private String traitAbbr;

    private Double weight;

}
