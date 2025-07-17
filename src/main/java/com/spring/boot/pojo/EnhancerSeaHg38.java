package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_enhancer
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_enhancer_sea_hg38")
@Data
public class EnhancerSeaHg38 extends EnhancerSea implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
