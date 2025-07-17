package com.spring.boot.util.model.vo.echarts;

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
public class EchartsCategories implements Serializable {

    /**
     * 类目名称
     */
    private String name;

    /**
     * 该类目节点标记的图形
     * 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow', 'none'
     */
    @Builder.Default
    private String symbol = "circle";

    /**
     * 该类目节点标记的大小
     */
    @Builder.Default
    private Double symbolSize = 10D;

    @Builder.Default
    private EchartsItemStyle itemStyle;

}
