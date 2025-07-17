package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_trait_gene
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_trait_gene_hg19")
@Data
public class TraitGeneHg19 extends TraitGene implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}