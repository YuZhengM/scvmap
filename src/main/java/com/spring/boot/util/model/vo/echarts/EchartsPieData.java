package com.spring.boot.util.model.vo.echarts;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Echarts Pie Data
 *
 * @author Zhengmin Yu
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class EchartsPieData<K, V> implements Serializable {

    /**
     * 图标信息
     */
    private List<String> legend;

    private List<SeriesPieData> data;

    private List<Integer> aData;

    private List<Integer> bData;

    private Map<K, V> description;

}
