package com.spring.boot.pojo.vo;

import com.spring.boot.pojo.Trait;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.vo.FieldNumber;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class TraitDataBrowseResultVO implements Serializable {

    private List<FieldNumber> typeList;
    private List<FieldNumber> categoryList;
    private List<FieldNumber> subcategoryList;
    private List<FieldNumber> cohortList;
    private PageResult<Trait> dataBrowseDataList;

}
