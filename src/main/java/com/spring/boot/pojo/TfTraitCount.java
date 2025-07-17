package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_tf_trait_count
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_tf_trait_count")
@Data
public class TfTraitCount extends BaseTraitCount implements Serializable {

    @TableField("f_tf")
    private String tf;

    @Serial
    private static final long serialVersionUID = 1L;
}