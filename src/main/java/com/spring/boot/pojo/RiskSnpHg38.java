package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_risk_snp
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_risk_snp_atlas_hg38")
@Data
@Schema(name = "RiskSnpHg38", description = "Table for risk SNPs from ATLAS (hg38)")
public class RiskSnpHg38 extends RiskSnp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}