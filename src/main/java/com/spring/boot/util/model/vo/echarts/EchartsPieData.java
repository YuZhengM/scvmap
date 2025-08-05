package com.spring.boot.util.model.vo.echarts;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Data model for Echarts pie chart")
public class EchartsPieData<K, V> implements Serializable {

    /**
     * Chart legend
     */
    @Schema(description = "Chart legend list")
    private List<String> legend;

    /**
     * Chart data
     */
    @Schema(description = "Chart data list")
    private List<SeriesPieData> data;

    /**
     * Chart data
     */
    @Schema(description = "Chart data list of type Integer")
    private List<Integer> aData;

    /**
     * Chart data
     */
    @Schema(description = "Chart data list of type Integer")
    private List<Integer> bData;

    /**
     * Additional descriptive information carried
     */
    @Schema(description = "Additional descriptive information carried")
    private Map<K, V> description;

}
