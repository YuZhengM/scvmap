package com.spring.boot.util.model.vo.echarts;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Zhengmin Yu
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class EchartsClusterData<T> implements Serializable {

    /**
     * 聚类数量
     */
    @Builder.Default
    private Integer clusterCount = 0;

    @Builder.Default
    private Integer outputClusterIndexDimension = 2;

    /**
     * 坐标
     */
    private List<List<T>> data;

    private List<EchartsClusterPieces> pieces;

    private Double min;

    private Double max;

}
