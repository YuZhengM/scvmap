package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_difference_gene
 */
@TableName("t_difference_gene")
@Data
public class DifferenceGene implements Serializable {

    @TableField("f_sample_id")
    private String sampleId;

    @TableField("f_cell_type")
    private String cellType;

    @TableField("f_gene")
    private String gene;

    @TableField("f_score")
    private Double score;

    @TableField("f_adjusted_p_value")
    private String adjustedPValue;

    @TableField("f_log2_fold_change")
    private Double log2FoldChange;

    @TableField("f_p_value")
    private String pValue;

    @Serial
    private static final long serialVersionUID = 1L;
}