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

    private Double af;

    private Double maf;

    private Double beta;

    private Double se;

    private Double pValue;

    private Double chisq;

    private Double zScore;

    private Double pp;

    private Double betaPosterior;

    private Double sdPosterior;

    @Serial
    private static final long serialVersionUID = 1L;
}