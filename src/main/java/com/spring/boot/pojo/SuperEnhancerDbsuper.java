package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_super_enhancer_dbsuper
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "SuperEnhancerDbsuper", description = "Table for storing super enhancer information from dbSUPER")
public class SuperEnhancerDbsuper extends BaseRegion implements Serializable {

    @Schema(description = "Cell type")
    @TableField("f_cell_type")
    private String cellType;

    @Schema(description = "SE ID")
    @TableField("f_se_id")
    private String seId;

    @Serial
    private static final long serialVersionUID = 1L;
}