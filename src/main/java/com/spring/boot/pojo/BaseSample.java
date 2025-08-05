package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
@Schema(description = "Base sample information")
public class BaseSample implements Serializable {

    @TableField("f_tissue_type")
    @Schema(description = "Tissue type")
    private String tissueType;

    @TableField(value = "count(0)", select = false)
    @Schema(description = "Count value", hidden = true)
    private Long count;

}
