package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_super_enhancer_sea
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SuperEnhancerSea extends BaseRegion implements Serializable {

    @TableField("f_associated_gene")
    private String associatedGene;

    @TableField("f_cell_tissue_type")
    private String cellTissueType;

    @TableField("f_recognition_factor")
    private String recognitionFactor;

    @TableField("f_sequence_region")
    private String sequenceRegion;

    @TableField("f_se_id")
    private String seId;

    @Serial
    private static final long serialVersionUID = 1L;
}