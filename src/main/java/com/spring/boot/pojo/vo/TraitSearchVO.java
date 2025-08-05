package com.spring.boot.pojo.vo;

import com.spring.boot.util.util.result.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "TraitSearchVO", description = "VO for trait search.")
public class TraitSearchVO implements Serializable {

    @Schema(description = "Category of the trait.")
    private String category;

    @Schema(description = "Subcategory of the trait.")
    private String subcategory;

    @Schema(description = "Source ID of the trait.")
    private String sourceId;

    @Schema(description = "Pagination information.")
    private Page page;
}
