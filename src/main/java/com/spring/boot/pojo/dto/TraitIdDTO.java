package com.spring.boot.pojo.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class TraitIdDTO implements Serializable {

    private String id;

    private List<String> traitIdList;

}
