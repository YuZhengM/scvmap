package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Trait", description = "Trait or disease information")
public class Trait implements Serializable {

    @TableId("f_trait_id")
    @Schema(description = "The unique identifier of the trait used for searching in the database.")
    private String traitId;

    @TableField("f_trait_index")
    @Schema(description = "The unique identifier of the trait, used for sorting in the database, corresponds one-to-one with 'f_trait_id'.")
    private String traitIndex;

    @TableField("f_trait_code")
    @Schema(description = "The unique identifier of the trait, used as the file name for the file processing procedure.")
    private String traitCode;

    @TableField("f_trait_abbr")
    @Schema(description = "The abbreviation form of the trait.")
    private String traitAbbr;

    @TableField("f_trait")
    @Schema(description = "Detailed information for the trait.")
    private String trait;

    @TableField("f_type")
    @Schema(description = "The trait is classified as one of the types of 'disease', 'drug', 'compound', 'health', 'subject', 'treatment', 'symptom', 'indicator' or 'other'.")
    private String type;

    @TableField("f_icd10")
    @Schema(description = "ICD-10")
    private String icd10;

    @TableField("f_category")
    @Schema(description = "Major categories in ICD-10")
    private String category;

    @TableField("f_subcategory")
    @Schema(description = "Subcategories in ICD-10")
    private String subcategory;

    @TableField("f_three_category")
    @Schema(description = "The third category in ICD-10")
    private String threeCategory;

    @TableField("f_source_id")
    @Schema(description = "Unique ID of the trait source cohort.")
    private String sourceId;

    @TableField("f_source_name")
    @Schema(description = "Name of the trait source cohort.")
    private String sourceName;

    @TableField("f_source_genome")
    @Schema(description = "Reference genome of trait source cohort. (Reference genome of the trait before LiftOver)")
    private String sourceGenome;

    @TableField("f_variant_count")
    @Schema(description = "The number of variant in the trait before LiftOver.")
    private Integer variantCount;

    @TableField("f_variant_pp_sum")
    @Schema(description = "The total PP value of variant in the trait before LiftOver.")
    private Double variantPpSum;

    @TableField("f_hg19_count")
    @Schema(description = "The number of variant in the trait based on hg19 as a background reference genome.")
    private Integer hg19Count;

    @TableField("f_hg38_count")
    @Schema(description = "The number of variant in the trait based on hg38 as a background reference genome.")
    private Integer hg38Count;

    @TableField("f_hg19_pp_sum")
    @Schema(description = "The total PP value of variant in the trait based on hg19 as a background reference genome.")
    private Double hg19PpSum;

    @TableField("f_hg38_pp_sum")
    @Schema(description = "The total PP value of variant in the trait based on hg38 as a background reference genome.")
    private Double hg38PpSum;

    @TableField("f_cohort")
    @Schema(description = "The cohort for collecting the trait.")
    private String cohort;

    @TableField("f_author")
    @Schema(description = "The author of the origin of the trait.")
    private String author;

    @TableField("f_mesh_id")
    @Schema(description = "MESH ID")
    private String meshId;

    @TableField("f_mesh_term")
    @Schema(description = "MESH TERM")
    private String meshTerm;

    @TableField("f_meta_id")
    @Schema(description = "META ID")
    private String metaId;

    @TableField("f_n_case")
    @Schema(description = "Case size")
    private Integer nCase;

    @TableField("f_n_control")
    @Schema(description = "Control size")
    private Integer nControl;

    @TableField("f_sample_size")
    @Schema(description = "Sample size")
    private Integer sampleSize;

    @TableField("f_filter")
    @Schema(description = "Each trait is retained, with a value of 1 for all.")
    private Integer filter;

    @TableField("f_index")
    @Schema(description = "The unique index identifier given in the same source cohort has no meaning and is only used to distinguish different traits in the same source cohort.")
    private Integer fIndex;

    @TableField("f_url")
    @Schema(description = "The link to download the source of each trait.")
    private String url;

    @TableField(exist = false)
    @Schema(description = "Source information", hidden = true)
    private Source source;

    @TableField(value = "count(0)", select = false)
    @Schema(description = "Count value", hidden = true)
    private Long count;

    @TableField(select = false)
    @Schema(description = "List of Magma objects", hidden = true)
    private List<Magma> magmaList;

    @TableField(exist = false)
    @Serial
    @Schema(hidden = true)
    private static final long serialVersionUID = 1L;
}
