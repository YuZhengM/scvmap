package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

/**
 * t_variant_0_hg19
 *
 * @author Admin
 */
@Data
public class VariantInfo implements Serializable {

    private String traitId;

    private String sourceId;

    private String chr;

    private String position;

    private String fIndex;

    private String rsId;

    private String variant;

    private String allele1;

    private String allele2;

    private String af;

    private String maf;

    private String beta;

    private String se;

    private String pValue;

    private String chisq;

    private String zScore;

    private Double pp;

    private String betaPosterior;

    private String sdPosterior;

    @Serial
    private static final long serialVersionUID = 1L;
}