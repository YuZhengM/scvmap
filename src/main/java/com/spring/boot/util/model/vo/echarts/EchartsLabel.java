package com.spring.boot.util.model.vo.echarts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * EchartsLabel: The style of this node's label
 *
 * @author Zhengmin Yu
 */
@Builder
@ToString
@Data
@Schema(description = "The style of this node's label")
public class EchartsLabel implements Serializable {

    /**
     * Whether to show the label
     */
    @Builder.Default
    @Schema(description = "Whether to show the label", defaultValue = "true")
    private Boolean show = true;

    /**
     * The position of the label
     */
    @Builder.Default
    @Schema(description = "The position of the label", defaultValue = "top")
    private String position = "top";

    /**
     * Distance from the graphic element
     */
    @Builder.Default
    @Schema(description = "Distance from the graphic element", defaultValue = "5.0")
    private Double distance = 5D;

    /**
     * Label rotation
     */
    @Builder.Default
    @Schema(description = "Label rotation", defaultValue = "0.0")
    private Double rotate = 0D;

    /**
     * Text color
     */
    @Builder.Default
    @Schema(description = "Text color", defaultValue = "#000000")
    private String color = "#000000";

    /**
     * Text font weight
     */
    @Builder.Default
    @Schema(description = "Text font weight", defaultValue = "normal")
    private String fontWeight = "normal";

    /**
     * Text font size
     */
    @Builder.Default
    @Schema(description = "Text font size", defaultValue = "17.0")
    private Double fontSize = 17D;

}
