package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@ToString
@Data
@Schema(name = "BaseTraitCount", description = "Base class for trait count information")
public class BaseTraitCount implements Serializable {

    @Schema(description = "Count of trait")
    @TableField("f_count")
    private Integer count;

    @Schema(description = "Genome")
    @TableField("f_genome")
    private String genome;

}
