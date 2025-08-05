package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_super_enhancer_sea
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_super_enhancer_sea_hg38")
@Data
@Schema(name = "SuperEnhancerSeaHg38", description = "Table for storing super enhancer information from SEA (hg38)")
public class SuperEnhancerSeaHg38 extends SuperEnhancerSea implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}