package com.spring.boot.util.model.vo.echarts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Echarts Node
 * 
 * @author Zhengmin Yu
 */
@ToString
@Data
@Schema(name = "EchartsNode", description = "Represents a node in Echarts visualization.")
public class EchartsNode implements Serializable {

    /**
     * String of the id of the node
     */
    @Schema(description = "ID string of the node")
    private String id;
 
    /**
     * Name of the data item
     */
    @Schema(description = "Name of the data item")
    private String name;

    /**
     * Initial x value of the node
     */
    @Schema(description = "Initial x-coordinate value of the node")
    private Double x;

    /**
     * Initial y value of the node
     */
    @Schema(description = "Initial y-coordinate value of the node")
    private Double y;

    /**
     * Value of the data item
     */
    @Schema(description = "Value of the data item")
    private String value;

    /**
     * Size of the ball
     */
    @Schema(description = "Size of the ball representing the node")
    private Double symbolSize;

    /**
     * Category
     */
    @Schema(description = "Category of the node")
    private String category;

    /**
     * Style of this node
     */
    @Schema(description = "Style configuration of this node")
    private EchartsItemStyle itemStyle;

    /**
     * Style of this node's label
     */
    @Schema(description = "Style configuration of this node's label")
    private EchartsLabel label;

    /**
     * Highlight state of this node
     */
    @Schema(description = "Highlight state configuration of this node")
    private EchartsEmphasis emphasis;

}
