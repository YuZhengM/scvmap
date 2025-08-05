package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * t_homer
 */
@TableName("t_homer")
@Data
@Schema(name = "Homer", description = "HOMER result table entity")
public class Homer implements Serializable {

    @Schema(description = "Unique identifier of trait or disease")
    @TableField("f_trait_id")
    private String traitId;

    @TableField("f_trait_code")
    private String traitCode;

    @Schema(description = "Unique identifier of gene")
    @TableField("f_motif_name")
    private String motifName;

    @Schema(description = "TF name")
    @TableField("f_tf")
    private String tf;

    @Schema(description = "Consensus")
    @TableField("f_consensus")
    private String consensus;

    @Schema(description = "p-value")
    @TableField("f_p_value")
    private String pValue;

    @Schema(description = "q-value")
    @TableField("f_q_value")
    private String qValue;

    @Serial
    private static final long serialVersionUID = 1L;
}
