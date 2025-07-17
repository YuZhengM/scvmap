package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_trait_tf
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_trait_tf_hg19")
@Data
public class TraitTfHg19 extends TraitTf implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
