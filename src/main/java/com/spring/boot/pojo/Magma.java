package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_magma
 */
@TableName("t_magma")
@Data
public class Magma implements Serializable {
    @TableField("f_trait_id")
    private String traitId;

    @TableField("f_gene")
    private String gene;

    @TableField("f_chr")
    private String chr;

    @TableField("f_start")
    private Integer start;

    @TableField("f_end")
    private Integer end;

    @TableField("f_n_snps")
    private Integer nSnps;

    @TableField("f_z_value")
    private Double zValue;

    @TableField("f_p_value")
    private String pValue;

    @Serial
    private static final long serialVersionUID = 1L;
}