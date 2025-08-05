package com.spring.boot.util.model.vo.echarts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Echarts Categories
 *
 * @author Zhengmin Yu
 */
@Builder
@ToString
@Data
@Schema(name = "EchartsCategories", description = "Represents the categories in Echarts.")
public class EchartsCategories implements Serializable {

    /**
     * Category name
     */
    @Schema(description = "Category name", example = "Sample Category")
    private String name;

    /**
     * The symbol of this category node.
     * 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow', 'none'
     */
    @Builder.Default
    @Schema(description = "The symbol of this category node. Options: 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow', 'none'", defaultValue = "circle")
    private String symbol = "circle";

    /**
     * The size of this category node's symbol
     */
    @Builder.Default
    @Schema(description = "The size of this category node's symbol", defaultValue = "10.0")
    private Double symbolSize = 10D;

    /**
     * The style of this node
     */
    @Builder.Default
    @Schema(description = "The style of this node", implementation = EchartsItemStyle.class)
    private EchartsItemStyle itemStyle = EchartsItemStyle.builder().build();

}
