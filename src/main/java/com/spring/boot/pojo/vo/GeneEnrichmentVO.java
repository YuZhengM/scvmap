package com.spring.boot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

import static com.spring.boot.util.constant.ApplicationConstant.*;

@Data
@Schema(name = "GeneEnrichmentVO", description = "Parameters for gene enrichment request")
public class GeneEnrichmentVO implements Serializable {

    @Schema(description = "Sample ID", example = SAMPLE_EXAMPLE)
    private String sampleId;

    @Schema(description = "Trait ID", example = TRAIT_EXAMPLE)
    private String traitId;

    @Schema(description = "Gene set", example = "GO_Biological_Process_2023")
    private String geneSet;

    @Schema(description = "Cell type. This parameter is only valid for cell type differential gene enrichment in single-cell samples.", example = CELL_TYPE_EXAMPLE)
    private String cellType;

    @Schema(description = "Threshold for enrichment p-value", example = "0.05")
    private Double value;

    @Schema(description = "Genome information. This parameter is only valid for trait-relevant gene enrichment.", example = GENOME_EXAMPLE)
    private String genome;

}
