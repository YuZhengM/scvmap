package com.spring.boot.pojo.vo;

import com.spring.boot.pojo.GeneEnrichment;
import com.spring.boot.pojo.TraitGeneEnrichment;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class GeneEnrichmentResultVO implements Serializable {

    private List<GeneEnrichment> geneEnrichmentList;
    private List<TraitGeneEnrichment> traitGeneEnrichmentList;

}
