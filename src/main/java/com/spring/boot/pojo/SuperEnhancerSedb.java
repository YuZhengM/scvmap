package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_super_enhancer_sedb
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "SuperEnhancerSedb", description = "Table for storing super enhancer information from SEdb")
public class SuperEnhancerSedb extends BaseRegion implements Serializable {

    @Schema(description = "Sample ID")
    @TableField("f_sample_id")
    private String sampleId;

    @Schema(description = "SE ID")
    @TableField("f_se_id")
    private String seId;

    @Schema(description = "Cell source")
    @TableField("f_cell_source")
    private String cellSource;

    @Schema(description = "Cell type")
    @TableField("f_cell_type")
    private String cellType;

    @Schema(description = "Tissue type")
    @TableField("f_tissue_type")
    private String tissueType;

    @Schema(description = "Cell state")
    @TableField("f_cell_state")
    private String cellState;

    @Serial
    private static final long serialVersionUID = 1L;
}
