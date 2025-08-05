package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * t_trait_chr_count
 */
@TableName("t_trait_chr_count")
@Data
@Schema(name = "TraitChrCount", description = "Table for mapping traits and chromosomes")
public class TraitChrCount implements Serializable {

    @Schema(description = "Trait ID")
    @TableId("f_trait_id")
    private String fTraitId;

    @Schema(description = "Chromosome")
    @TableField("f_chr")
    private String fChr;

    @Schema(description = "Count")
    @TableField("f_count")
    private Integer fCount;

    @Schema(description = "Genome")
    @TableField("f_genome")
    private String fGenome;

    @Serial
    private static final long serialVersionUID = 1L;
}