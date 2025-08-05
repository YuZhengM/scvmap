package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_risk_snp
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "RiskSnp", description = "Table for risk SNPs from ATLAS")
public class RiskSnp extends BasePosition implements Serializable {

    @Schema(description = "SNP ID")
    @TableField("f_rs_id")
    private String rsId;

    @Schema(description = "Reference allele")
    @TableField("f_ref")
    private String ref;

    @Schema(description = "Alternate allele")
    @TableField("f_alt")
    private String alt;

    @Schema(description = "P-value")
    @TableField("f_p_value")
    private String pValue;

    @Schema(description = "Trait")
    @TableField("f_trait")
    private String trait;

    @Schema(description = "Population")
    @TableField("f_population")
    private String population;

    @Schema(description = "PMID")
    @TableField("f_pmid")
    private String pmid;

    @Serial
    private static final long serialVersionUID = 1L;
}
