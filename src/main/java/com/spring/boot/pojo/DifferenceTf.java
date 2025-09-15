package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * t_difference_tf
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_difference_tf")
@ToString
@Data
@Schema(name = "DifferenceTf", description = "Represents the difference tf information.")
public class DifferenceTf extends BaseCellTypeTf implements Serializable {

    @Schema(description = "Unique identifier of scATAC-seq sample")
    @TableField("f_sample_id")
    private String sampleId;

    @Schema(description = "Unique identifier of transcription factor")
    @TableField("f_tf_id")
    private String tfId;

    @Schema(description = "P-value")
    @TableField("f_p_value")
    private Double pValue;

    @Schema(description = "Adjusted p value")
    @TableField("f_adjusted_p_value")
    private Double adjustedPValue;

    @Schema(description = "Log2(Fold change)")
    @TableField("f_log2_fold_change")
    private Double log2FoldChange;

    @Serial
    private static final long serialVersionUID = 1L;
}
