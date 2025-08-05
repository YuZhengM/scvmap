package com.spring.boot.util.model.vo.echarts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Zhengmin Yu
 * @description VO for Echarts cluster data
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Schema(description = "VO for Echarts cluster data")
public class EchartsClusterData<T> implements Serializable {

    /**
     * Number of clusters
     */
    @Builder.Default
    @Schema(description = "Number of clusters, default value is 0", defaultValue = "0")
    private Integer clusterCount = 0;

    @Builder.Default
    @Schema(description = "Output cluster index dimension, default value is 2", defaultValue = "2")
    private Integer outputClusterIndexDimension = 2;

    /**
     * Coordinates
     */
    @Schema(description = "Coordinates data")
    private List<List<T>> data;
    
    @Schema(description = "Cluster pieces data")
    private List<EchartsClusterPieces> pieces;

    @Schema(description = "Minimum value")
    private Double min;

    @Schema(description = "Maximum value")
    private Double max;

}
