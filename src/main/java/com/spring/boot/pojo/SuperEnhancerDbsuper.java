package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_super_enhancer_dbsuper
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SuperEnhancerDbsuper extends BaseRegion implements Serializable {

    @TableField("f_cell_type")
    private String cellType;

    @TableField("f_se_id")
    private String seId;

    @Serial
    private static final long serialVersionUID = 1L;
}