package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_common_snp
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonSnp extends BasePosition implements Serializable {

    private String rsId;

    private String ref;

    private String alt;

    @Serial
    private static final long serialVersionUID = 1L;
}