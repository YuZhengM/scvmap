package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * t_trait_tf
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_trait_tf")
@Data
public class TraitTf extends BaseElement implements Serializable {

    @TableField("f_tf")
    private String tf;

    @TableField("f_p_value")
    private String pValue;

    @TableField("f_q_value")
    private String qValue;

    @Serial
    private static final long serialVersionUID = 1L;
}
