package com.spring.boot.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_eqtl
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Eqtl extends BasePosition implements Serializable {

    private String ref;

    private String alt;

    private String geneName;

    private String tssDistance;

    private String af;

    private String pValueNominal;

    private String tissueType;

    @Serial
    private static final long serialVersionUID = 1L;
}