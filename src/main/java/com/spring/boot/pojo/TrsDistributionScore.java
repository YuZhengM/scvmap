package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@TableName("t_trs_distribution_score")
@Data
@Schema(name = "TrsDistributionScore", description = "KL divergence scores comparing TRS results from different fine-mapping methods.")
public class TrsDistributionScore implements Serializable {

    @Schema(description = "Trait ID")
    @TableField("f_trait_id")
    private String traitId;

    @Schema(description = "Trait Code")
    @TableField("f_trait_code")
    private String traitCode;

    @Schema(description = "Trait Name")
    @TableField("f_trait_name")
    private String traitName;

    @Schema(description = "Sample ID")
    @TableField("f_sample_id")
    private String sampleId;

    @Schema(description = "Label")
    @TableField("f_label")
    private String label;

    @Schema(description = "Sample Name")
    @TableField("f_sample_name")
    private String sampleName;

    @Schema(description = "KL Score")
    @TableField("f_kl_score")
    private Double klScore;

}
