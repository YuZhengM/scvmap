package com.spring.boot.pojo.vo;

import com.spring.boot.pojo.Sample;
import com.spring.boot.util.model.vo.FieldNumber;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class SampleDataBrowseResultVO<T> implements Serializable {

    private List<FieldNumber> healthTypeList;
    private List<FieldNumber> tissueTypeList;
    private List<FieldNumber> cellTypeList;
    private List<T> dataBrowseDataList;

}
