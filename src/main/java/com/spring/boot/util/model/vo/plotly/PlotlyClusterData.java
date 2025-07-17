package com.spring.boot.util.model.vo.plotly;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Builder
@ToString
@Data
public class PlotlyClusterData<X, Y> implements Serializable {

    private List<PlotlyData<X, Y>> plotlyDataList;

}
