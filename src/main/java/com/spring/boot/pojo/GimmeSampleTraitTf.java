package com.spring.boot.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class GimmeSampleTraitTf extends BaseSnpTf implements Serializable {

    private String traitId;

    private String rsId;

    private String tf;

    private Double score;

    private Double pp;

    private String scoreMean;

    private String motifSet;

    private String positionSet;

    private String strandSet;

    private Integer count;

}
