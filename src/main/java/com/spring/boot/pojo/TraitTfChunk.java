package com.spring.boot.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_trait_gene_hg19_0
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class TraitTfChunk extends BaseElement implements Serializable {

    private String tf;

    private String pValue;

    private String qValue;

    @Serial
    private static final long serialVersionUID = 1L;
}
