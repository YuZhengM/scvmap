package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * t_variant_0_hg19
 *
 * @author Admin
 */
@Data
@Schema(name = "VariantInfo", description = "Fine-mapping result data information")
public class VariantInfo implements Serializable {

    @Schema(description = "Unique identifier of the trait, used as the file name for the file processing procedure")
    private String traitId;

    @Schema(description = "Source ID")
    private String sourceId;

    @Schema(description = "Chromosome in the reference genome coordinate of the source cohort")
    private String chr;

    @Schema(description = "Position of variant in the reference genome coordinate of the source cohort")
    private String position;

    @Schema(description = "Unique index identifiers based on trait or disease variants, used to identify the uniqueness of variants")
    private String fIndex;

    @Schema(description = "rsID identifier")
    private String rsId;

    @Schema(description = "Unique variant identifier")
    private String variant;

    @Schema(description = "Reference allele in the reference genome coordinate of the source cohort")
    private String allele1;

    @Schema(description = "Alternative allele in the reference genome coordinate of the source cohort. (This allele is the effect allele.)")
    private String allele2;

    @Schema(description = "Allele frequency of allele2 (alt)")
    private String af;

    @Schema(description = "Allele frequency of the minor allele in cohort")
    private String maf;

    @Schema(description = "Marginal association effect size from linear mixed model/effect size GWAS")
    private String beta;

    @Schema(description = "Standard error on marginal association effect size from linear mixed model/standard error GWAS")
    private String se;

    @Schema(description = "p-value GWAS")
    private String pValue;

    @Schema(description = "Test statistic for marginal association")
    private String chisq;

    @Schema(description = "Original z-score")
    private String zScore;

    @Schema(description = "Posterior probability of association from fine-mapping (FINEMAP)")
    private Double pp;

    @Schema(description = "Posterior expectation of true effect size")
    private String betaPosterior;

    @Schema(description = "Posterior standard deviation of true effect size")
    private String sdPosterior;

    @Serial
    private static final long serialVersionUID = 1L;
}
