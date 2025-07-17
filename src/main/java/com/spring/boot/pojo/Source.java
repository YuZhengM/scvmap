package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_source
 *
 * @author Admin
 */
@TableName("t_source")
@Data
public class Source implements Serializable {

    @TableId("f_id")
    private String id;

    @TableField("f_name")
    private String name;

    @TableField("f_source")
    private String source;

    @TableField("f_description")
    private String description;

    @TableField("f_pmid")
    private String pmid;

    @TableField("f_genome")
    private String genome;

    @TableField("f_trait_count")
    private Integer traitCount;

    @TableField("f_filter_count")
    private Integer filterCount;

    @TableField("f_link")
    private String link;

    @TableField("f_version")
    private String version;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}
