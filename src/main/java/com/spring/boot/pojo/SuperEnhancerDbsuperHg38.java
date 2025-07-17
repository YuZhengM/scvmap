package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_super_enhancer_dbsuper
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_super_enhancer_dbsuper_hg38")
@Data
public class SuperEnhancerDbsuperHg38 extends SuperEnhancerDbsuper implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}