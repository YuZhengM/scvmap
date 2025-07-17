package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_risk_snp
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_risk_snp_atlas_hg19")
@Data
public class RiskSnpHg19 extends RiskSnp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}