package com.spring.boot.pojo.vo;

import com.spring.boot.util.util.result.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "TraitDataBrowseVO", description = "Filter condition information for trait data on the `Data-browse` page")
public class TraitDataBrowseVO implements Serializable {

    @Schema(description = "Type of the trait data", example = "indicator")
    private String type;

    @Schema(description = "Category of the trait data", example = "Non disease")
    private String category;

    @Schema(description = "Subcategory of the trait data", example = "Non disease")
    private String subcategory;

    @Schema(description = "Cohort of the trait data", example = "CAUSALdb")
    private String cohort;

    @Schema(description = "Fine-mapping method", example = "FINEMAP")
    private String method;

    @Schema(description = "Pagination information")
    private Page page;

}
