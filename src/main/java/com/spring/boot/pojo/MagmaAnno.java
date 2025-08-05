package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * t_magma_anno_hg19_0
 */
@Data
@Schema(name = "MagmaAnno", description = "Information of the result table obtained from the MAGMA annotation step, i.e., the mapping relationship between genes and variants")
public class MagmaAnno implements Serializable {

    @Schema(description = "Unique identifier of trait or disease")
    private String traitId;

    @Schema(description = "Unique identifier of gene")
    private String geneId;

    @Schema(description = "Gene name")
    private String gene;

    @Schema(description = "rsID identifier")
    private String rsId;

    @Serial
    private static final long serialVersionUID = 1L;
}
