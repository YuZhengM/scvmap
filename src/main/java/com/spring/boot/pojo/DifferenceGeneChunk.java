package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * t_difference_gene_sample_id_1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DifferenceGeneChunk extends DifferenceGene implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}