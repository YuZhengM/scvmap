package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * t_difference_gene
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_difference_gene")
@Data
@Schema(name = "DifferenceGene", description = "Represents the difference gene information.")
public class DifferenceGene extends BaseDifferenceGene implements Serializable {

    @Schema(description = "Unique identifier of scATAC-seq sample")
    @TableField("f_sample_id")
    private String sampleId;

    @Schema(description = "Cell type")
    @TableField("f_cell_type")
    private String cellType;

    @Schema(description = "Age/sex/drug")
    @TableField("f_type_value")
    private String typeValue;

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

    @Serial
    private static final long serialVersionUID = 1L;
}
