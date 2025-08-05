package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * t_gene
 */
@TableName("t_gene")
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "Gene", description = "Table for gene information")
public class Gene extends GeneKey implements Serializable {

    @Schema(description = "Unique identifier of gene")
    @TableId("f_gene_id")
    private String geneId;

    @Schema(description = "Gene name")
    @TableField("f_gene_name")
    private String geneName;

    @Schema(description = "Chromosome")
    @TableField("f_chr")
    private String chr;

    @Schema(description = "Start position")
    @TableField("f_start")
    private Integer start;

    @Schema(description = "End position")
    @TableField("f_end")
    private Integer end;

    @Schema(description = "Strand")
    @TableField("f_strand")
    private String strand;

    @Schema(description = "Gene type")
    @TableField("f_gene_type")
    private String geneType;

    @Schema(description = "Source")
    @TableField("f_source")
    private String source;

    @Schema(description = "Genome version")
    @TableField("f_genome")
    private String genome;

    @Serial
    private static final long serialVersionUID = 1L;
}
