package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@TableName("t_chromvar_difference_tf")
@Data
public class ChromVarDifferenceTf extends BaseCellTypeTf implements Serializable {

    @TableField("f_sample_id")
    private String sampleId;

    @TableField("f_mean1")
    private String mean1;

    @TableField("f_mean2")
    private String mean2;

    @TableField("f_p_value")
    private String pValue;

    @TableField("f_p_value_adjust")
    private String pValueAdjust;

}
