package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@TableName("t_chromvar_difference_tf")
@Data
public class ChromVarDifferenceTf extends BaseCellTypeTf implements Serializable {

    @Schema(description = "Sample ID")
    @TableField("f_sample_id")
    private String sampleId;

    @Schema(description = "Mean1")
    @TableField("f_mean1")
    private String mean1;

    @Schema(description = "Mean2")
    @TableField("f_mean2")
    private String mean2;

    @Schema(description = "P-value")
    @TableField("f_p_value")
    private String pValue;

    @Schema(description = "Adjusted P-value")
    @TableField("f_p_value_adjust")
    private String pValueAdjust;

}
