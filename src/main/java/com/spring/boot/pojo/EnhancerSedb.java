package com.spring.boot.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_enhancer
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "EnhancerSedb", description = "Table for enhancer information")
public class EnhancerSedb extends BaseRegion implements Serializable {

    @Schema(description = "Sample ID")
    private String sampleId;

    @Schema(description = "SE ID")
    private String seId;

    @Schema(description = "Cell source")
    private String cellSource;

    @Schema(description = "Cell type")
    private String cellType;

    @Schema(description = "Tissue type")
    private String tissueType;

    @Schema(description = "Cell state")
    private String cellState;

    @Serial
    private static final long serialVersionUID = 1L;
}
