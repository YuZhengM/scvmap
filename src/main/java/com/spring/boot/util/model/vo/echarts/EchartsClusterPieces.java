package com.spring.boot.util.model.vo.echarts;

import lombok.*;

import java.io.Serializable;

/**
 * @author Zhengmin Yu
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class EchartsClusterPieces implements Serializable {

    /**
     * 值
     */
    private Integer value;

    /**
     * 标签
     */
    private String label;

    /**
     * 标签
     */
    private String color;


}
