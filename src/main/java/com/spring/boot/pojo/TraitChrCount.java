package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_trait_chr_count
 */
@TableName("t_trait_chr_count")
@Data
public class TraitChrCount implements Serializable {

    @TableId("f_trait_id")
    private String fTraitId;

    @TableField("f_chr")
    private String fChr;

    @TableField("f_count")
    private Integer fCount;

    @TableField("f_genome")
    private String fGenome;

    @Serial
    private static final long serialVersionUID = 1L;
}