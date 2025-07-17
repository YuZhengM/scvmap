package com.spring.boot.util.model.vo.echarts;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Zhengmin Yu
 */
@ToString
@Data
public class EchartsLink implements Serializable {

    /**
     * 边的源节点名称的字符串
     */
    private String source;

    /**
     * 边的目标节点名称的字符串
     */
    private String target;

    /**
     * 边的数值，可以在力引导布局中用于映射到边的长度
     */
    private Double value;

    /**
     * 边的样式
     */
    private EchartsLineStyle lineStyle;

}
