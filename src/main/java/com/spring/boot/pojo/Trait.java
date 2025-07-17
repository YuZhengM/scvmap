package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_trait
 *
 * @author Admin
 */
@TableName("t_trait")
@Data
public class Trait implements Serializable {

    @TableId("f_trait_id")
    private String traitId;

    @TableField("f_trait_index")
    private String traitIndex;

    @TableField("f_trait_code")
    private String traitCode;

    @TableField("f_trait_abbr")
    private String traitAbbr;

    @TableField("f_trait")
    private String trait;

    @TableField("f_type")
    private String type;

    @TableField("f_icd10")
    private String icd10;

    @TableField("f_category")
    private String category;

    @TableField("f_subcategory")
    private String subcategory;

    @TableField("f_three_category")
    private String threeCategory;

    @TableField("f_source_id")
    private String sourceId;

    @TableField("f_source_name")
    private String sourceName;

    @TableField("f_source_genome")
    private String sourceGenome;

    @TableField("f_variant_count")
    private Integer variantCount;

    @TableField("f_variant_pp_sum")
    private Double variantPpSum;

    @TableField("f_hg19_count")
    private Integer hg19Count;

    @TableField("f_hg38_count")
    private Integer hg38Count;

    @TableField("f_hg19_pp_sum")
    private Double hg19PpSum;

    @TableField("f_hg38_pp_sum")
    private Double hg38PpSum;

    @TableField("f_cohort")
    private String cohort;

    @TableField("f_author")
    private String author;

    @TableField("f_mesh_id")
    private String meshId;

    @TableField("f_mesh_term")
    private String meshTerm;

    @TableField("f_meta_id")
    private String metaId;

    @TableField("f_n_case")
    private Integer nCase;

    @TableField("f_n_control")
    private Integer nControl;

    @TableField("f_sample_size")
    private Integer sampleSize;

    @TableField("f_filter")
    private Integer filter;

    @TableField("f_index")
    private Integer fIndex;

    @TableField("f_url")
    private String url;

    @TableField(exist = false)
    private Source source;

    @TableField(value = "count(0)", select = false)
    private Long count;

    @TableField(select = false)
    private List<Magma> magmaList;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}
