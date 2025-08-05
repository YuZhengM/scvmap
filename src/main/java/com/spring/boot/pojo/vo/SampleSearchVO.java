package com.spring.boot.pojo.vo;

import com.spring.boot.util.util.result.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YuZhengMin
 * @version V1.0.0
 * @date 2024-10-12 21:21
 */
@Data
@Schema(name = "SampleSearchVO", description = "Sample search value object")
public class SampleSearchVO implements Serializable {

    @Schema(description = "Tissue type")
    private String tissueType;

    @Schema(description = "Cell type")
    private String cellType;

    @Schema(description = "Pagination information")
    private Page page;

}
