package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * t_sample_cell
 *
 * @author Admin
 */
@TableName("t_sample_cell")
@Data
@Schema(name = "SampleCell", description = "Detailed information of cell annotations in scATAC-seq data")
public class SampleCell implements Serializable {

    @Schema(description = "Sample ID")
    @TableField("f_sample_id")
    private String sampleId;

    @Schema(description = "Cell barcodes")
    @TableField("f_barcodes")
    private String barcodes;

    @Schema(description = "Cell type")
    @TableField("f_cell_type")
    private String cellType;

    @Schema(description = "UMAP X coordinate")
    @TableField("f_umap_x")
    private Double umapX;

    @Schema(description = "UMAP Y coordinate")
    @TableField("f_umap_y")
    private Double umapY;

    @Schema(description = "TSS enrichment score")
    @TableField("f_tsse")
    private Double tsse;

    @Schema(description = "Cell index")
    @TableField("f_index")
    private Integer indexValue;

    @Schema(description = "Cell type index")
    @TableField("f_cell_type_index")
    private Integer cellTypeIndex;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}
