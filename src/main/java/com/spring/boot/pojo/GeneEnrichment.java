package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * t_gene_enrichment
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GeneEnrichment extends TraitGeneEnrichment implements Serializable {

    private String sampleId;

    private String cellType;

    @Serial
    private static final long serialVersionUID = 1L;
}