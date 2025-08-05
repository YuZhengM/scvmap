package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@ToString
@Data
@Schema(name = "BaseElement", description = "Base class for element information")
public class BaseElement implements Serializable {

    @Schema(description = "Trait ID")
    @TableField("f_trait_id")
    private String traitId;

    @Schema(description = "Count value", hidden = true)
    @TableField(value = "count(DISTINCT f_trait_id)", select = false)
    private Long count;

}
