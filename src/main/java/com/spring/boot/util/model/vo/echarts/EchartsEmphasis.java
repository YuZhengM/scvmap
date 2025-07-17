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
public class EchartsEmphasis implements Serializable {

    /**
     * 是否关闭高亮状态
     */
    @Builder.Default
    private Boolean disabled = false;

    /**
     * 该节点的样式
     */
    @Builder.Default
    private EchartsItemStyle itemStyle = EchartsItemStyle.builder().build();

    /**
     * 该节点标签的样式
     */
    @Builder.Default
    private EchartsLabel label = EchartsLabel.builder().build();

}
