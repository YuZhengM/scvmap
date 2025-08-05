package com.spring.boot.util.model.vo.echarts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * @author Zhengmin Yu
 */
@Schema(description = "Data model for ECharts cluster pieces")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class EchartsClusterPieces implements Serializable {

    /**
     * The value of the cluster piece
     */
    @Schema(description = "The value of the cluster piece")
    private Integer value;

    /**
     * The label of the cluster piece
     */
    @Schema(description = "The label of the cluster piece")
    private String label;

    /**
     * The color of the cluster piece
     */
    @Schema(description = "The color of the cluster piece")
    private String color;

}
