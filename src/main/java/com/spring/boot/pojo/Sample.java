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
 * t_sample
 *
 * @author Admin
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_sample")
@Data
@Schema(name = "Sample", description = "scATAC-seq sample information")
public class Sample extends BaseSample implements Serializable {

    @TableId("f_sample_id")
    @Schema(description = "The unique identifier of the single-cell sample, used for database operations.")
    private String sampleId;

    @TableField("f_sample_name")
    @Schema(description = "The unique identifier of the single-cell sample, used for database operations.")
    private String sampleName;

    @TableField("f_gse_id")
    @Schema(description = "GSE ID")
    private String gseId;

    @TableField("f_genome")
    @Schema(description = "The reference genome of the single-cell sample.")
    private String genome;

    @TableField("f_geo_id")
    @Schema(description = "GEO ID")
    private String geoId;

    @TableField("f_label")
    @Schema(description = "The unique identifier for the single-cell sample, used as the file name during data processing.")
    private String label;

    @TableField("f_pmid")
    @Schema(description = "PMID")
    private String pmid;

    @TableField("f_species")
    @Schema(description = "The species information of the single-cell sample. All data belongs to humans.")
    private String species;

    @TableField("f_tissue_type")
    @Schema(description = "The tissue type of the single-cell sample.")
    private String tissueType;

    @TableField("f_sequencing_type")
    @Schema(description = "The sequencing type of the single-cell sample.")
    private String sequencingType;

    @TableField("f_health_type")
    @Schema(description = "The health type of the single-cell sample.")
    private String healthType;

    @TableField("f_health_type_description")
    @Schema(description = "Detailed information on the health type of the single-cell sample.")
    private String healthTypeDescription;

    @TableField("f_description")
    @Schema(description = "Detailed information on the content of the single-cell sample.")
    private String description;

    @TableField("f_cell_type_count")
    @Schema(description = "The number of cell types in the single-cell sample.")
    private Integer cellTypeCount;

    @TableField("f_cell_count")
    @Schema(description = "The number of cells in the single-cell sample.")
    private Integer cellCount;

    @TableField("f_source")
    @Schema(description = "The source name of the single-cell sample.")
    private String source;

    @TableField("f_source_url")
    @Schema(description = "The link to the source of the single-cell sample.")
    private String sourceUrl;

    @TableField("f_sample_exist")
    @Schema(description = "The single-cell sample contains multiple sample information.")
    private Integer sampleExist;

    @TableField("f_time")
    @Schema(description = "The single-cell sample contains age/day information.")
    private Integer timeExist;

    @TableField("f_sex")
    @Schema(description = "The single-cell sample contains sex information.")
    private Integer sexExist;

    @TableField("f_drug")
    @Schema(description = "The single-cell sample contains drug resistance information.")
    private Integer drugExist;

    @TableField("f_index")
    @Schema(description = "The unique index identifier of the single-cell sample has no meaning and is only used for sorting.")
    private Integer FIndex;

    @TableField(value = "count(0)", select = false)
    @Schema(description = "Count value, not selected by default.")
    private Long count;

    @TableField(exist = false)
    @Serial
    @Schema(hidden = true)
    private static final long serialVersionUID = 1L;
}
