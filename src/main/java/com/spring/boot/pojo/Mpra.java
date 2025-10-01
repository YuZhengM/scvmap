package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("t_mpra")
@Data
public class Mpra implements Serializable {

    @TableField("f_chr")
    private String chr;

    @TableField("f_position")
    private String position;

    @TableField("f_ref")
    private String ref;

    @TableField("f_alt")
    private String alt;

    @TableField("f_genome")
    private String genome;

    @TableField("f_rs_id")
    private String rsId;

    @TableField("f_disease")
    private String disease;

    @TableField("f_cell_line")
    private String cellLine;

    @TableField("f_description")
    private String description;

    @TableField("f_log2_fold_change")
    private String log2FoldChange;

    @TableField("f_p_value")
    private String pValue;

    @TableField("f_fdr")
    private String fdr;

    @TableField("f_mpra_study")
    private String mpraStudy;

}
