package com.spring.boot.util.model.vo.echarts;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Zhengmin Yu
 */
@Builder
@ToString
@Data
public class EchartsLabel implements Serializable {

    /**
     * 是否显示标签
     */
    @Builder.Default
    private Boolean show = true;

    /**
     * 标签的位置
     */
    @Builder.Default
    private String position = "top";

    /**
     * 距离图形元素的距离
     */
    @Builder.Default
    private Double distance = 5D;

    /**
     * 标签旋转
     */
    @Builder.Default
    private Double rotate = 0D;

    /**
     * 文字的颜色
     */
    @Builder.Default
    private String color = "#000000";

    /**
     * 文字字体的粗细
     */
    @Builder.Default
    private String fontWeight = "normal";

    /**
     * 文字的字体大小
     */
    @Builder.Default
    private Double fontSize = 17D;

}
