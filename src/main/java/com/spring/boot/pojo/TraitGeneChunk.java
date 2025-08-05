package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * t_trait_gene_hg19_0
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@Schema(name = "TraitGeneChunk", description = "Table for mapping traits and genes")
public class TraitGeneChunk extends BaseElement implements Serializable {

    @Schema(description = "Gene name")
    private String gene;

    @Schema(description = "Number of SNPs")
    private Integer nSnps;

    @Schema(description = "P-value")
    private String pValue;

    @Serial
    private static final long serialVersionUID = 1L;
}