package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.spring.boot.util.constant.ApplicationConstant.TRAIT_EXAMPLE;
import static com.spring.boot.util.constant.ApplicationConstant.VARIANT_EXAMPLE;

@Data
@Schema(name = "TraitVariantMap", description = "Represents the mapping between trait ID, rsID and pp value.")
public class TraitVariantMap implements Serializable {

    @Schema(description = "Unique identifier for the trait.", example = TRAIT_EXAMPLE)
    private String traitId;

    @Schema(description = "rs ID associated with the genetic variant.", example = VARIANT_EXAMPLE)
    private String rsId;

    @Schema(description = "Probability value related to the trait and variant.", example = "0.9")
    private Double pp;

    @Serial
    private static final long serialVersionUID = 1L;
}
