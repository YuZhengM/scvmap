package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_magma_anno_hg19_0
 */
@Data
public class MagmaAnno implements Serializable {
    private String traitId;

    private String geneId;

    private String gene;

    private String rsId;

    @Serial
    private static final long serialVersionUID = 1L;
}