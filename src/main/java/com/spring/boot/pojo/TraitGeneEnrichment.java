package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_gene_enrichment
 */
@Data
@Schema(name = "TraitGeneEnrichment", description = "Gene enrichment result information")
public class TraitGeneEnrichment implements Serializable {

    @Schema(description = "Trait ID")
    private String traitId;

    @Schema(description = "Gene set (GO_Biological_Process_2023, GO_Cellular_Component_2023, GO_Molecular_Function_2023 and GWAS_Catalog_2023)")
    private String geneSet;

    @Schema(description = "Gene enrichment term")
    private String term;

    @Schema(description = "Percentage of gene set overlap")
    private Double overlap;

    @Schema(description = "P-value")
    private String pValue;

    @Schema(description = "Adjusted p-value")
    private String adjustedPValue;

    @Schema(description = "Combined score")
    private String combinedScore;

    @Schema(description = "Odds ratio")
    private Double oddsRatio;

    @Schema(description = "Overlap genes")
    private String gene;

    @Schema(description = "Count of overlapping genes")
    private Integer fCount;

    @Serial
    private static final long serialVersionUID = 1L;
}
