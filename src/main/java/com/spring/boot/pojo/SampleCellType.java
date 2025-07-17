package com.spring.boot.pojo;

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
public class SampleCellType extends BaseSample implements Serializable {

    @TableField("f_sample_id")
    private String sampleId;

    @TableField("f_cell_type")
    private String cellType;

    @TableField("f_gse_id")
    private String gseId;

    @TableField("f_genome")
    private String genome;

    @TableField("f_geo_id")
    private String geoId;

    @TableField("f_label")
    private String label;

    @TableField("f_pmid")
    private String pmid;

    @TableField("f_species")
    private String species;

    @TableField("f_sequencing_type")
    private String sequencingType;

    @TableField("f_health_type")
    private String healthType;

    @TableField("f_health_type_description")
    private String healthTypeDescription;

    @TableField("f_description")
    private String description;

    @TableField("f_cell_count")
    private Integer cellCount;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;

}