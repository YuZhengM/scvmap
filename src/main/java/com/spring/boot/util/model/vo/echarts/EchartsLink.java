package com.spring.boot.util.model.vo.echarts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Echarts Link
 * 
 * @author Zhengmin Yu
 */
@ToString
@Data
@Schema(name = "EchartsLink", description = "Represents a link in Echarts, used to define the connection between nodes.")
public class EchartsLink implements Serializable {
 
    /**
     * String of the source node name of the edge
     */
    @Schema(description = "Name of the source node of the edge.")
    private String source;

    /**
     * String of the target node name of the edge
     */
    @Schema(description = "Name of the target node of the edge.")
    private String target;

    /**
     * The value of the edge, which can be used to map to the edge length in the force-directed layout
     */
    @Schema(description = "Value of the edge, which can be used to map to the edge length in the force-directed layout.")
    private Double value;

    /**
     * The style of the edge
     */
    @Schema(description = "Style of the edge.")
    private EchartsLineStyle lineStyle;

}
