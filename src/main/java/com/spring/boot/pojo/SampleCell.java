package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_sample_cell
 *
 * @author Admin
 */
@TableName("t_sample_cell")
@Data
public class SampleCell implements Serializable {

    @TableField("f_sample_id")
    private String sampleId;

    @TableField("f_barcodes")
    private String barcodes;

    @TableField("f_cell_type")
    private String cellType;

    @TableField("f_umap_x")
    private Double umapX;

    @TableField("f_umap_y")
    private Double umapY;

    @TableField("f_tsse")
    private Double tsse;

    @TableField("f_index")
    private Integer indexValue;

    @TableField("f_cell_type_index")
    private Integer cellTypeIndex;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}