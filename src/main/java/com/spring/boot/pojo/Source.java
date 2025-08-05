package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * t_source
 *
 * @author Admin
 */
@TableName("t_source")
@Data
@Schema(name = "Source", description = "Trait source information table")
public class Source implements Serializable {

    @TableId("f_id")
    @Schema(description = "ID of the source")
    private String id;

    @TableField("f_name")
    @Schema(description = "Name of the source")
    private String name;

    @TableField("f_source")
    @Schema(description = "Source information")
    private String source;

    @TableField("f_description")
    @Schema(description = "Description of the source")
    private String description;

    @TableField("f_pmid")
    @Schema(description = "PMID of the source")
    private String pmid;

    @TableField("f_genome")
    @Schema(description = "Genome information of the source")
    private String genome;

    @TableField("f_trait_count")
    @Schema(description = "Trait count of the source")
    private Integer traitCount;

    @TableField("f_filter_count")
    @Schema(description = "Filter count of the source")
    private Integer filterCount;

    @TableField("f_link")
    @Schema(description = "Link of the source")
    private String link;

    @TableField("f_version")
    @Schema(description = "Version of the source")
    private String version;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}
