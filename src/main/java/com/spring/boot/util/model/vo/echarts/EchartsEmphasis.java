package com.spring.boot.util.model.vo.echarts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Highlight state
 * @author Zhengmin Yu
 */
@Builder
@ToString
@Data
@Schema(description = "Highlight state information")
public class EchartsEmphasis implements Serializable {

    /**
     * Whether to disable the highlight state
     */
    @Builder.Default
    @Schema(description = "Whether to disable the highlight state", defaultValue = "false")
    private Boolean disabled = false;

    /**
     * The style of this node
     */
    @Builder.Default
    @Schema(description = "The style of this node")
    private EchartsItemStyle itemStyle = EchartsItemStyle.builder().build();

    /**
     * The style of this node's label
     */
    @Builder.Default
    @Schema(description = "The style of this node's label")
    private EchartsLabel label = EchartsLabel.builder().build();

}
