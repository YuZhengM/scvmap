package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@ToString
@Data
@Schema(name = "BaseRegion", description = "Base class for region information")
public class BaseRegion implements Serializable {

    @Schema(description = "Chromosome")
    @TableField("f_chr")
    private String chr;

    @Schema(description = "Start position")
    @TableField("f_start")
    private Integer start;

    @Schema(description = "End position")
    @TableField("f_end")
    private Integer end;

}
