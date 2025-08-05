package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * t_magma
 */
@TableName("t_magma")
@Data
@Schema(name = "Magma", description = "MAGMA result table entity")
public class Magma implements Serializable {

    @Schema(description = "Unique identifier of the trait")
    @TableField("f_trait_id")
    private String traitId;

    @Schema(description = "Gene name")
    @TableField("f_gene")
    private String gene;

    @Schema(description = "Chromosome code")
    @TableField("f_chr")
    private String chr;

    @Schema(description = "Starting boundary of gene annotation on chromosomes")
    @TableField("f_start")
    private Integer start;

    @Schema(description = "Ending boundary of gene annotation on chromosomes")
    @TableField("f_end")
    private Integer end;

    @Schema(description = "The number of SNPs not annotated to this gene based on previous SNP QC exclusion")
    @TableField("f_n_snps")
    private Integer nSnps;

    @Schema(description = "Z-value")
    @TableField("f_z_value")
    private Double zValue;

    @Schema(description = "P-value")
    @TableField("f_p_value")
    private String pValue;

    @Serial
    private static final long serialVersionUID = 1L;
}
