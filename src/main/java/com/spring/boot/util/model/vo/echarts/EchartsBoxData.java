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
public class EchartsBoxData<T> implements Serializable {

    /**
     * 聚类数量
     */
    @Builder.Default
    private Integer count = 0;

    /**
     * 坐标
     */
    private List<List<T>> data;

    private List<String> colorType;

    private List<String> description;

}
