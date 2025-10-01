package com.spring.boot.pojo.vo;

import com.spring.boot.util.model.vo.FieldNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class SampleDataBrowseResultVO<T> implements Serializable {

    @Schema(description = "List of health types")
    private List<FieldNumber> healthTypeList;

    @Schema(description = "List of tissue types")
    private List<FieldNumber> tissueTypeList;

    @Schema(description = "List of cell types")
    private List<FieldNumber> cellTypeList;

    @Schema(description = "List of metadata")
    private List<FieldNumber> metadataList;

    @Schema(description = "List of returned result data")
    private List<T> dataBrowseDataList;

}
