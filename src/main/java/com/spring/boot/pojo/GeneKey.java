package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * t_gene
 */
@Data
@Schema(name = "GeneKey", description = "Table for gene keys")
public class GeneKey implements Serializable {

    @Schema(description = "Unique identifier of gene")
    @TableId("f_gene_id")
    private String geneId;

    @Schema(description = "Genome")
    @TableField("f_genome")
    private String genome;

    @Serial
    private static final long serialVersionUID = 1L;
}