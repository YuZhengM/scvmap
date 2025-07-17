package com.spring.boot.pojo.vo;

import com.spring.boot.util.model.vo.echarts.EchartsPieData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SampleTraitInfo<T> implements Serializable {

    private List<T> traitSampleList;

    private EchartsPieData<String, Integer> echartsPieData;

}
