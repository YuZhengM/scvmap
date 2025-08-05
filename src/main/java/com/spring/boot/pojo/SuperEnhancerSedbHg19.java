package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_super_enhancer_sedb
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_super_enhancer_sedb_hg19")
@Data
@Schema(name = "SuperEnhancerSedbHg19", description = "Table for storing super enhancer information from SEdb (hg19)")
public class SuperEnhancerSedbHg19 extends SuperEnhancerSedb implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}