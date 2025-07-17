package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * t_gene
 */
@Data
public class GeneKey implements Serializable {

    @TableId("f_gene_id")
    private String geneId;

    @TableField("f_genome")
    private String genome;

    @Serial
    private static final long serialVersionUID = 1L;
}