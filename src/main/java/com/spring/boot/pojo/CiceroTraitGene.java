package com.spring.boot.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CiceroTraitGene implements Serializable {

    private String traitId;
    private String gene;
    private String score;

}
