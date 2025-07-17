package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_risk_snp
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RiskSnp extends BasePosition implements Serializable {

    @TableField("f_rs_id")
    private String rsId;

    @TableField("f_ref")
    private String ref;

    @TableField("f_alt")
    private String alt;

    @TableField("f_p_value")
    private String pValue;

    @TableField("f_trait")
    private String trait;

    @TableField("f_population")
    private String population;

    @TableField("f_pmid")
    private String pmid;

    @Serial
    private static final long serialVersionUID = 1L;
}