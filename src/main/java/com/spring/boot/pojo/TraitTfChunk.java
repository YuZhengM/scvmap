package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_trait_gene_hg19_0
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@Schema(name = "TraitTfChunk", description = "Mapping table between traits and TFs")
public class TraitTfChunk extends BaseElement implements Serializable {

    @Schema(description = "Transcription factor identifier")
    private String tf;

    @Schema(description = "P-value")
    private String pValue;

    @Schema(description = "Q-value")
    private String qValue;

    @Serial
    private static final long serialVersionUID = 1L;
}
