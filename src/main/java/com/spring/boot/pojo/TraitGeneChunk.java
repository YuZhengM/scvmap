package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * t_trait_gene_hg19_0
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class TraitGeneChunk extends BaseElement implements Serializable {

    private String gene;

    private Integer nSnps;

    private String pValue;

    @Serial
    private static final long serialVersionUID = 1L;
}