package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * t_gene_trait_count
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_gene_trait_count")
@Data
@Schema(name = "GeneTraitCount", description = "Table for counting traits per gene")
public class GeneTraitCount extends BaseTraitCount implements Serializable {

    @Schema(description = "Gene name")
    @TableField("f_gene")
    private String gene;

    @Serial
    private static final long serialVersionUID = 1L;
}
