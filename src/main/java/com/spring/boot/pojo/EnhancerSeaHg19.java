package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_enhancer
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_enhancer_sea_hg19")
@Data
@Schema(name = "EnhancerSeaHg19", description = "Table for enhancer information in hg19")
public class EnhancerSeaHg19 extends EnhancerSea implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
