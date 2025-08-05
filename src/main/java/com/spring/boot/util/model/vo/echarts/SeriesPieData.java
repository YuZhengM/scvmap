package com.spring.boot.util.model.vo.echarts;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Echarts Pie Series Data")
public class SeriesPieData implements Serializable {

    /**
     * Label content
     */
    @Schema(description = "Label content")
    private String name;

    /**
     * Label quantity
     */
    @Schema(description = "Label quantity")
    private Integer value;

}
