package com.spring.boot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "SampleDataBrowseVO", description = "Filter condition information for scATAC-seq samples on the Data-browse page")
public class SampleDataBrowseVO implements Serializable {

    @Schema(description = "Type of health status", example = "Disease")
    private String healthType;
    
    @Schema(description = "Tissue type", example = "Heart")
    private String tissueType;
    
    @Schema(description = "Cell type", example = "Endothelial cells")
    private String cellType;

}
