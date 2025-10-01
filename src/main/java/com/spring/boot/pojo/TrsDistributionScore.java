package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("t_trs_distribution_score")
@Data
public class TrsDistributionScore implements Serializable {

    @TableField("f_trait_id")
    private String traitId;

    @TableField("f_trait_code")
    private String traitCode;

    @TableField("f_trait_name")
    private String traitName;

    @TableField("f_sample_id")
    private String sampleId;

    @TableField("f_label")
    private String label;

    @TableField("f_sample_name")
    private String sampleName;

    @TableField("f_kl_score")
    private Double klScore;

}
