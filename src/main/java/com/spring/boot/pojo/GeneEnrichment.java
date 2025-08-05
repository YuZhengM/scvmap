package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * t_gene_enrichment
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "GeneEnrichment", description = "Represents the gene enrichment information.")
public class GeneEnrichment extends TraitGeneEnrichment implements Serializable {

    @Schema(description = "ID of the sample.")
    private String sampleId;

    @Schema(description = "Cell type.")
    private String cellType;

    @Serial
    private static final long serialVersionUID = 1L;
}