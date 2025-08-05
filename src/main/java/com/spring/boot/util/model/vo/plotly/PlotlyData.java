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
@Schema(description = "Data model for Plotly visualization, used to define data points and their display properties.")
public class PlotlyData<X, Y> implements Serializable {

    @Schema(description = "List of X-axis data points.")
    private List<X> x;

    @Schema(description = "List of Y-axis data points.")
    private List<Y> y;

    @Schema(description = "List of color values for data points.")
    private List<Double> color;

    @Builder.Default
    @Schema(description = "Display mode of data points, default is 'markers'.", defaultValue = "markers")
    private String mode = "markers";

    @Builder.Default
    @Schema(description = "Type of plot, default is 'scatter'.", defaultValue = "scatter")
    private String type = "scatter";

    @Schema(description = "Name of the data series.")
    private String name;

    @Schema(description = "List of text labels for data points.")
    private List<String> text;

    @Schema(description = "List of extra text information for data points.")
    private List<String> extraText;

}
