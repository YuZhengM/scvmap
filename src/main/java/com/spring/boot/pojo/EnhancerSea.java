package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * t_enhancer
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "EnhancerSea", description = "Table for enhancer information in sea")
public class EnhancerSea extends BaseRegion implements Serializable {

    @Schema(description = "Associated gene")
    @TableField("f_associated_gene")
    private String associatedGene;

    @Schema(description = "Cell tissue type")
    @TableField("f_cell_tissue_type")
    private String cellTissueType;

    @Schema(description = "Recognition factor")
    @TableField("f_recognition_factor")
    private String recognitionFactor;

    @Schema(description = "Sequence region")
    @TableField("f_sequence_region")
    private String sequenceRegion;

    @Schema(description = "SE ID")
    @TableField("f_se_id")
    private String seId;

    @Serial
    private static final long serialVersionUID = 1L;
}