package com.spring.boot.pojo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_gene_enrichment
 */
@Data
public class TraitGeneEnrichment implements Serializable {
    private String traitId;

    private String geneSet;

    private String term;

    private Double overlap;

    private String pValue;

    private String adjustedPValue;

    private String combinedScore;

    private Double oddsRatio;

    private String gene;

    private Integer fCount;

    @Serial
    private static final long serialVersionUID = 1L;
}