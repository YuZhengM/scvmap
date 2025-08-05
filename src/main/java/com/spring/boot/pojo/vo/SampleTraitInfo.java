package com.spring.boot.pojo.vo;

import com.spring.boot.util.model.vo.echarts.EchartsPieData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(name = "SampleTraitInfo", description = "Sample trait information model, used to encapsulate sample trait data and pie chart data.")
public class SampleTraitInfo<T> implements Serializable {

    @Schema(description = "List of sample traits, supporting generic types.")
    private List<T> traitSampleList;

    @Schema(description = "Pie chart data, with key type String and value type Integer.")
    private EchartsPieData<String, Integer> echartsPieData;

}
