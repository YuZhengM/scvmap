package com.spring.boot.util.model.vo.echarts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Line style
 * 
 * @author Zhengmin Yu
 */
@Builder
@ToString
@Data
@Schema(description = "Line style configuration")
public class EchartsLineStyle implements Serializable {

    /**
     * Line color
     */
    @Builder.Default
    @Schema(description = "Line color, default is 'source'", defaultValue = "source")
    private String color = "source";

    /**
     * Line width
     */
    @Builder.Default
    @Schema(description = "Line width, default is 1.0", defaultValue = "1.0")
    private Double width = 1D;

    /**
     * Line type, optional: solid, dashed, dotted
     */
    @Builder.Default
    @Schema(description = "Line type, optional values: solid, dashed, dotted. Default is 'solid'", defaultValue = "solid")
    private String type = "solid";

    /**
     * Used to set the offset of the dashed line
     */
    @Builder.Default
    @Schema(description = "Used to set the offset of the dashed line, default is 0.1", defaultValue = "0.1")
    private Double dashOffset = 0.1D;

    /**
     * Used to specify the drawing method for the end of the line segment
     * 'butt': The line segment ends with a square.
     * 'round': The line segment ends with a circle.
     * 'square': The line segment ends with a square, but adds a rectangular area with the same width as the line segment and a height half of the line segment's thickness.
     */
    @Builder.Default
    @Schema(description = "Used to specify the drawing method for the end of the line segment. 'butt': The line segment ends with a square. 'round': The line segment ends with a circle. 'square': The line segment ends with a square, but adds a rectangular area with the same width as the line segment and a height half of the line segment's thickness. Default is 'butt'", defaultValue = "butt")
    private String cap = "butt";

    /**
     * Used to set the connection of two non-zero length segments
     * 'bevel': Fill an additional triangular-based area at the end of the connected part, and each part has its own independent rectangular corner.
     * 'round': Draw the shape of the corner by filling an additional sector centered at the end of the connected part. The radius of the rounded corner is the width of the line segment.
     * 'miter': Extend the outer edges of the connected parts until they intersect at a point, forming an additional diamond-shaped area. This setting can be observed through the miterLimit property.
     */
    @Builder.Default
    @Schema(description = "Used to set the connection of two non-zero length segments. 'bevel': Fill an additional triangular-based area at the end of the connected part, and each part has its own independent rectangular corner. 'round': Draw the shape of the corner by filling an additional sector centered at the end of the connected part. The radius of the rounded corner is the width of the line segment. 'miter': Extend the outer edges of the connected parts until they intersect at a point, forming an additional diamond-shaped area. This setting can be observed through the miterLimit property. Default is 'bevel'", defaultValue = "bevel")
    private String join = "bevel";

    /**
     * Used to set the miter limit ratio
     */
    @Builder.Default
    @Schema(description = "Used to set the miter limit ratio, default is 10.0", defaultValue = "10.0")
    private Double miterLimit = 10D;

    /**
     * Graph transparency. Supports numbers from 0 to 1. When set to 0, the graph is not drawn.
     */
    @Builder.Default
    @Schema(description = "Graph transparency. Supports values from 0 to 1. When set to 0, the graph is not drawn. Default is 0.9", defaultValue = "0.9")
    private Double opacity = 0.9D;

    /**
     * Curvature of the edge. Supports values from 0 to 1. The larger the value, the greater the curvature.
     */
    @Builder.Default
    @Schema(description = "Curvature of the edge. Supports values from 0 to 1. The larger the value, the greater the curvature. Default is 0.0", defaultValue = "0.0")
    private Double curveness = 0D;

}
