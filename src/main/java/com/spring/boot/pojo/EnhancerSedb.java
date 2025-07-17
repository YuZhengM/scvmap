package com.spring.boot.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * t_enhancer
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EnhancerSedb extends BaseRegion implements Serializable {

    private String sampleId;

    private String seId;

    private String cellSource;

    private String cellType;

    private String tissueType;

    private String cellState;

    @Serial
    private static final long serialVersionUID = 1L;
}