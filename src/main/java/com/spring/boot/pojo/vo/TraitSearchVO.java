package com.spring.boot.pojo.vo;

import com.spring.boot.util.util.result.Page;
import lombok.Data;

import java.io.Serializable;

@Data
public class TraitSearchVO implements Serializable {

    private String category;
    private String subcategory;
    private String sourceId;

    private Page page;
}
