package com.spring.boot.pojo.vo;

import com.spring.boot.pojo.Trait;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.vo.FieldNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@Schema(name = "TraitDataBrowseResultVO", description = "Trait data browse result value object")
public class TraitDataBrowseResultVO<T> implements Serializable {

    @Schema(description = "List of type field numbers")
    private List<FieldNumber> typeList;

    @Schema(description = "List of category field numbers")
    private List<FieldNumber> categoryList;

    @Schema(description = "List of subcategory field numbers")
    private List<FieldNumber> subcategoryList;

    @Schema(description = "List of cohort field numbers")
    private List<FieldNumber> cohortList;

    @Schema(description = "Paged list of trait data browse results")
    private PageResult<T> dataBrowseDataList;

}
