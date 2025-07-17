package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * t_difference_tf
 */
@TableName("t_difference_tf")
@ToString
@Data
public class DifferenceTf implements Serializable {

    @TableField("f_sample_id")
    private String sampleId;

    @TableField("f_cell_type")
    private String cellType;

    @TableField("f_tf")
    private String tf;

    @TableField("f_tf_id")
    private String tfId;

    @TableField("f_p_value")
    private Double pValue;

    @TableField("f_adjusted_p_value")
    private Double adjustedPValue;

    @TableField("f_log2_fold_change")
    private Double log2FoldChange;

    @Serial
    private static final long serialVersionUID = 1L;
}