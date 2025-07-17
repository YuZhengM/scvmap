package com.spring.boot.util.model.vo.plotly;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Builder
@ToString
@Data
public class PlotlyData<X, Y> implements Serializable {

    private List<X> x;

    private List<Y> y;

    private List<Double> color;

    @Builder.Default
    private String mode = "markers";

    @Builder.Default
    private String type = "scatter";

    private String name;

    private List<String> text;

    private List<String> extraText;

}
