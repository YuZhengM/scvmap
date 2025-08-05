package com.spring.boot.util.model.vo.canvasXpress;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Builder
@ToString
@Data
@Schema(description = "Data model for CanvasXpress heat map.")
public class CanvasXpressHeatMapData<T> implements Serializable {

    @Schema(description = "Two-dimensional list containing the heat map data.")
    private List<List<T>> data;

    @Schema(description = "List of labels for the X-axis.")
    private List<String> xLabelList;

    @Schema(description = "List of labels for the Y-axis.")
    private List<String> yLabelList;

    @Schema(description = "List of labels for the Z-axis.")
    private List<String> zLabelList;

}
