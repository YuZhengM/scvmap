package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_super_enhancer_sea
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_super_enhancer_sea_hg19")
@Data
public class SuperEnhancerSeaHg19 extends SuperEnhancerSea implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}