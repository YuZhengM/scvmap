package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_common_snp
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "CommonSnp", description = "Table for common SNP information")
public class CommonSnp extends BasePosition implements Serializable {

    @Schema(description = "SNP ID")
    private String rsId;

    @Schema(description = "Reference allele")
    private String ref;

    @Schema(description = "Alternate allele")
    private String alt;

    @Serial
    private static final long serialVersionUID = 1L;
}
