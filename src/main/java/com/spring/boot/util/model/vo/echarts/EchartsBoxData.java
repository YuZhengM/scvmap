package com.spring.boot.util.model.vo.echarts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Zhengmin Yu
 * @description Class for storing data related to ECharts boxplot
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Schema(description = "Class for storing data related to ECharts boxplot")
public class EchartsBoxData<T> implements Serializable {

    /**
     * Cluster count
     */
    @Builder.Default
    @Schema(description = "Cluster count", defaultValue = "0")
    private Integer count = 0;

    /**
     * Coordinates
     */
    @Schema(description = "Coordinates")
    private List<List<T>> data;

    /**
     * List of color types
     */
    @Schema(description = "List of color types")
    private List<String> colorType;

    /**
     * List of descriptions
     */
    @Schema(description = "List of descriptions")
    private List<String> description;

}
