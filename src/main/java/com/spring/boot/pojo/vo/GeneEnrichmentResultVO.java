package com.spring.boot.pojo.vo;

import com.spring.boot.pojo.GeneEnrichment;
import com.spring.boot.pojo.TraitGeneEnrichment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@Schema(description = "Response object for gene enrichment results.")
public class GeneEnrichmentResultVO implements Serializable {

    @Schema(description = "List of gene enrichment records.")
    private List<GeneEnrichment> geneEnrichmentList;

    @Schema(description = "List of trait gene enrichment records.")
    private List<TraitGeneEnrichment> traitGeneEnrichmentList;

}
