package com.spring.boot.util.model.vo.echarts;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Echarts 关系图数据
 *
 * @author Zhengmin Yu
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class EchartsGraphData implements Serializable {

    /**
     * 结点信息
     */
    private List<EchartsNode> nodes;

    /**
     * 边信息
     */
    private List<EchartsLink> links;

    /**
     * 类别信息
     */
    private List<EchartsCategories> categories;

}
