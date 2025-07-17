package com.spring.boot.pojo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class TraitVariantMap implements Serializable {

    private String traitId;

    private String rsId;

    private Double pp;

    @Serial
    private static final long serialVersionUID = 1L;
}
