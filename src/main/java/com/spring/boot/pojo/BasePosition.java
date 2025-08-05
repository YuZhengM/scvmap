package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@ToString
@Data
@Schema(name = "BasePosition", description = "Base class for position information")
public class BasePosition implements Serializable {

    @Schema(description = "Chromosome")
    @TableField("f_chr")
    private String chr;

    @Schema(description = "Position")
    @TableField("f_position")
    private Integer position;

}
