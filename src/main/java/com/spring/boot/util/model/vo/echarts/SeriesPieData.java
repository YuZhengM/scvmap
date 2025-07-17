package com.spring.boot.util.model.vo.echarts;

import lombok.*;

import java.io.Serializable;

/**
 * Echarts Pie Series Data
 *
 * @author Zhengmin Yu
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class SeriesPieData implements Serializable {

    /**
     * 标签内容
     */
    private String name;

    /**
     * 标签数量
     */
    private Integer value;

}
