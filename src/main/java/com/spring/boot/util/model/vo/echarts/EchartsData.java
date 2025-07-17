package com.spring.boot.util.model.vo.echarts;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * echarts 的 x, y 数据
 *
 * @author Zhengmin Yu
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class EchartsData<X, Y> implements Serializable {

    /**
     * x 轴数据 (String 类型)
     */
    private List<X> xData;

    /**
     * x 轴数据 (Integer 类型)
     */
    private List<Y> yData;

}
