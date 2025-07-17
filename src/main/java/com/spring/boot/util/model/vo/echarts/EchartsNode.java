package com.spring.boot.util.model.vo.echarts;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Zhengmin Yu
 */
@ToString
@Data
public class EchartsNode implements Serializable {

    private String id;

    /**
     * 数据项名称
     */
    private String name;

    /**
     * 节点的初始 x 值
     */
    private Double x;

    /**
     * 节点的初始 y 值
     */
    private Double y;

    /**
     * 数据项值
     */
    private String value;

    /**
     * 球的大小
     */
    private Double symbolSize;

    /**
     * 种类
     */
    private String category;

    /**
     * 该节点的样式
     */
    private EchartsItemStyle itemStyle;

    /**
     * 该节点标签的样式
     */
    private EchartsLabel label;

    /**
     * 该节点的高亮状态
     */
    private EchartsEmphasis emphasis;

}
