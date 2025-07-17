package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * t_gene
 */
@TableName("t_gene")
@EqualsAndHashCode(callSuper = true)
@Data
public class Gene extends GeneKey implements Serializable {

    @TableId("f_gene_id")
    private String geneId;

    @TableField("f_gene_name")
    private String geneName;

    @TableField("f_chr")
    private String chr;

    @TableField("f_start")
    private Integer start;

    @TableField("f_end")
    private Integer end;

    @TableField("f_strand")
    private String strand;

    @TableField("f_gene_type")
    private String geneType;

    @TableField("f_source")
    private String source;

    @TableField("f_genome")
    private String genome;

    @Serial
    private static final long serialVersionUID = 1L;
}