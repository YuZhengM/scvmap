package com.spring.boot.pojo.vo;

import com.spring.boot.util.util.result.Page;
import lombok.Data;

import java.io.Serializable;

@Data
public class TraitDataBrowseVO implements Serializable {

    private String type;
    private String category;
    private String subcategory;
    private String cohort;

    private Page page;

}
