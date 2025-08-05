package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * t_trait_sample_scavenge
 */
@Data
@Schema(name = "TraitSample", description = "Trait- or disease-sample pair information")
public class TraitSample implements Serializable {

    @Schema(description = "The unique identifier of the trait used for searching in the database.")
    private String traitId;

    @Schema(description = "The unique identifier of the single-cell sample, used for database operations.")
    private String sampleId;

    @Schema(description = "The unique identifier of the trait, used as the file name for the file processing procedure.")
    private String traitCode;

    @Schema(description = "The abbreviation form of the trait.")
    private String traitAbbr;

    @Schema(description = "Detailed information for the trait.")
    private String trait;

    @Schema(description = "Name of the trait source cohort.")
    private String sourceName;

    @Schema(description = "The tissue type of the single-cell sample.")
    private String tissueType;

    @Schema(description = "The unique identifier for the single-cell sample, used as the file name during data processing.")
    private String label;

    @Schema(description = "The unique identifier of the trait, used for sorting in the database, corresponds one-to-one with 'f_trait_id'.")
    private Integer traitIndex;

    @Schema(description = "The unique index identifier of the single-cell sample has no meaning and is only used for sorting.")
    private Integer sampleIndex;

    @Serial
    private static final long serialVersionUID = 1L;
}
