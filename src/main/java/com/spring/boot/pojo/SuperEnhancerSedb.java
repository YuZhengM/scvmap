package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_super_enhancer_sedb
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SuperEnhancerSedb extends BaseRegion implements Serializable {

    @TableField("f_sample_id")
    private String sampleId;

    @TableField("f_se_id")
    private String seId;

    @TableField("f_cell_source")
    private String cellSource;

    @TableField("f_cell_type")
    private String cellType;

    @TableField("f_tissue_type")
    private String tissueType;

    @TableField("f_cell_state")
    private String cellState;

    @Serial
    private static final long serialVersionUID = 1L;
}