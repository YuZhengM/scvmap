package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * t_difference_gene_sample_id_1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "DifferenceGeneChunk", description = "Table for difference gene information")
public class DifferenceGeneChunk extends DifferenceGene implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
