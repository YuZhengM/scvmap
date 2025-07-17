package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class Sample extends BaseSample implements Serializable {

    @TableId("f_sample_id")
    private String sampleId;

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

    @TableField("f_tissue_type")
    private String tissueType;

    @TableField("f_sequencing_type")
    private String sequencingType;

    @TableField("f_health_type")
    private String healthType;

    @TableField("f_health_type_description")
    private String healthTypeDescription;

    @TableField("f_description")
    private String description;

    @TableField("f_cell_type_count")
    private Integer cellTypeCount;

    @TableField("f_cell_count")
    private Integer cellCount;

    @TableField("f_source")
    private String source;

    @TableField("f_source_url")
    private String sourceUrl;

    @TableField("f_sample_exist")
    private Integer sampleExist;

    @TableField("f_index")
    private Integer FIndex;

    @TableField(value = "count(0)", select = false)
    private Long count;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}
