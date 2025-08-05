package com.spring.boot.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_eqtl
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "Eqtl", description = "Table for eQTL information")
public class Eqtl extends BasePosition implements Serializable {

    @Schema(description = "Reference allele")
    private String ref;

    @Schema(description = "Alternate allele")
    private String alt;

    @Schema(description = "Gene name")
    private String geneName;

    @Schema(description = "Distance to TSS")
    private String tssDistance;

    @Schema(description = "Allele frequency")
    private String af;

    @Schema(description = "Nominal p-value")
    private String pValueNominal;

    @Schema(description = "Tissue type")
    private String tissueType;

    @Serial
    private static final long serialVersionUID = 1L;
}
