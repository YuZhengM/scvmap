package com.spring.boot.util.model.vo.echarts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * ItemStyle: The style of this node
 *
 * @author Zhengmin Yu
 */
@Builder
@ToString
@Data
@Schema(description = "The style of this node")
public class EchartsItemStyle implements Serializable {

    /**
     * The color of this node
     */
    @Schema(description = "The color of this node")
    private String color;

    /**
     * The stroke color of the graphic
     */
    @Schema(description = "The stroke color of the graphic", defaultValue = "#FFF")
    @Builder.Default
    private String borderColor = "#FFF";

    /**
     * The width of the stroke
     */
    @Schema(description = "The width of the stroke", defaultValue = "1")
    @Builder.Default
    private Integer borderWidth = 1;

    /**
     * The type of the stroke, optional values: "solid", "dashed", "dotted"
     */
    @Schema(description = "The type of the stroke, optional values: \"solid\", \"dashed\", \"dotted\"", defaultValue = "solid")
    @Builder.Default
    private String borderType = "solid";

    /**
     * Used to set the offset of the dashed line
     */
    @Schema(description = "Used to set the offset of the dashed line", defaultValue = "0")
    @Builder.Default
    private Integer borderDashOffset = 0;

    /**
     * Used to specify the drawing method for the end of the line segment
     * 'butt': The line segment ends with a square.
     * 'round': The line segment ends with a circle.
     * 'square': The line segment ends with a square.
     */
    @Schema(description = "Used to specify the drawing method for the end of the line segment. 'butt': The line segment ends with a square. 'round': The line segment ends with a circle. 'square': The line segment ends with a square.", defaultValue = "butt")
    @Builder.Default
    private String borderCap = "butt";

    /**
     * Used to set the connection of two non-zero length segments
     * 'bevel': Fill an additional triangular area at the end of the connected part, and each part has its own independent rectangular corner.
     * 'round': Draw the shape of the corner by filling an additional sector with the center at the end of the connected part. The radius of the rounded corner is the width of the line segment.
     * 'miter': Extend the outer edges of the connected parts to make them intersect at a point, forming an additional diamond-shaped area. This setting can be seen through the borderMiterLimit property.
     */
    @Schema(description = "Used to set the connection of two non-zero length segments. 'bevel': Fill an additional triangular area at the end of the connected part, and each part has its own independent rectangular corner. 'round': Draw the shape of the corner by filling an additional sector with the center at the end of the connected part. The radius of the rounded corner is the width of the line segment. 'miter': Extend the outer edges of the connected parts to make them intersect at a point, forming an additional diamond-shaped area. This setting can be seen through the borderMiterLimit property.", defaultValue = "bevel")
    @Builder.Default
    private String borderJoin = "bevel";

    /**
     * Used to set the miter limit ratio
     */
    @Schema(description = "Used to set the miter limit ratio", defaultValue = "10")
    @Builder.Default
    private Integer borderMiterLimit = 10;

    /**
     * The blur size of the graphic shadow
     */
    @Schema(description = "The blur size of the graphic shadow", defaultValue = "10")
    @Builder.Default
    private Integer shadowBlur = 10;

    /**
     * The color of the graphic shadow
     */
    @Schema(description = "The color of the graphic shadow", defaultValue = "#FFFFFF")
    @Builder.Default
    private String shadowColor = "#FFFFFF";

    /**
     * The horizontal offset distance of the shadow
     */
    @Schema(description = "The horizontal offset distance of the shadow", defaultValue = "0")
    @Builder.Default
    private Double shadowOffsetX = 0D;

    /**
     * The vertical offset distance of the shadow
     */
    @Schema(description = "The vertical offset distance of the shadow", defaultValue = "0")
    @Builder.Default
    private Double shadowOffsetY = 0D;

    /**
     * The opacity of the graphic
     */
    @Schema(description = "The opacity of the graphic", defaultValue = "1")
    @Builder.Default
    private Double opacity = 1D;
}
