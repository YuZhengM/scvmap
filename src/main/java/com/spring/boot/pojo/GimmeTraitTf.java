package com.spring.boot.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GimmeTraitTf implements Serializable {

    private String traitId;
    private String tf;
    private String score;
    private String scoreMean;

}
