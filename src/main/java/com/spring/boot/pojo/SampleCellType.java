package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * t_sample_cell_type
 *
 * @author Admin
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_sample_cell_type")
@Data
@Schema(name = "SampleCellType", description = "Information of samples at the cell type granularity")
public class SampleCellType extends BaseSample implements Serializable {

    @Schema(description = "Unique identifier of the single-cell sample, used for database operations.")
    @TableField("f_sample_id")
    private String sampleId;

    @Schema(description = "Cell type of the single-cell sample.")
    @TableField("f_cell_type")
    private String cellType;

    @Schema(description = "GSE ID of the single-cell sample.")
    @TableField("f_gse_id")
    private String gseId;

    @Schema(description = "Reference genome of the single-cell sample.")
    @TableField("f_genome")
    private String genome;

    @Schema(description = "GEO ID of the single-cell sample.")
    @TableField("f_geo_id")
    private String geoId;

    @Schema(description = "Unique identifier for the single-cell sample, used as the file name during data processing.")
    @TableField("f_label")
    private String label;

    @Schema(description = "PMID of the single-cell sample.")
    @TableField("f_pmid")
    private String pmid;

    @Schema(description = "Species information of the single-cell sample. All data belongs to humans.")
    @TableField("f_species")
    private String species;

    @Schema(description = "Sequencing type of the single-cell sample.")
    @TableField("f_sequencing_type")
    private String sequencingType;

    @Schema(description = "Health type of the single-cell sample.")
    @TableField("f_health_type")
    private String healthType;

    @Schema(description = "Detailed information on the health type of the single-cell sample.")
    @TableField("f_health_type_description")
    private String healthTypeDescription;

    @Schema(description = "Detailed information on the content of the single-cell sample.")
    @TableField("f_description")
    private String description;

    @Schema(description = "Number of cells in the single-cell sample.")
    @TableField("f_cell_count")
    private Integer cellCount;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;

}
