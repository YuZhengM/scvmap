package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_homer
 */
@TableName("t_homer")
@Data
public class Homer implements Serializable {
    @TableField("f_trait_id")
    private String traitId;

    @TableField("f_trait_code")
    private String traitCode;

    @TableField("f_motif_name")
    private String motifName;

    @TableField("f_tf")
    private String tf;

    @TableField("f_consensus")
    private String consensus;

    @TableField("f_p_value")
    private String pValue;

    @TableField("f_q_value")
    private String qValue;

    @Serial
    private static final long serialVersionUID = 1L;
}
