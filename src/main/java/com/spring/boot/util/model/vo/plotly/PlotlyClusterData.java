package com.spring.boot.util.model.vo.plotly;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Builder
@ToString
@Data
@Schema(description = "Result data information for drawing cell type clustering diagrams")
public class PlotlyClusterData<X, Y> implements Serializable {

    @Schema(description = "Multiple cluster data information")
    private List<PlotlyData<X, Y>> plotlyDataList;

}
