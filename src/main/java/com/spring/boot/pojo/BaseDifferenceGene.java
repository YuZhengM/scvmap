package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDifferenceGene implements Serializable {

    @Schema(description = "Gene name")
    @TableField("f_gene")
    private String gene;

    @Schema(description = "Score")
    @TableField("f_score")
    private Double score;

    @Schema(description = "Adjusted p value")
    @TableField("f_adjusted_p_value")
    private String adjustedPValue;

    @Schema(description = "Log2(Fold change)")
    @TableField("f_log2_fold_change")
    private Double log2FoldChange;

    @Schema(description = "P-value")
    @TableField("f_p_value")
    private String pValue;

}
