package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * t_trait_gene
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TraitGene extends BaseElement implements Serializable {

    @TableField("f_gene")
    private String gene;

    @Serial
    private static final long serialVersionUID = 1L;
}