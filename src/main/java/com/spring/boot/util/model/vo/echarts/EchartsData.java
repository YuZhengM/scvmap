package com.spring.boot.util.model.vo.echarts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * echarts'x and y data
 *
 * @author Zhengmin Yu
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Schema(description = "Data model for ECharts, containing x-axis and y-axis data.")
public class EchartsData<X, Y> implements Serializable {

    /**
     * Data on the x-axis
     */
    @Schema(description = "Data on the x-axis")
    private List<X> xData;

    /**
     * Data on the y-axis
     */
    @Schema(description = "Data on the y-axis")
    private List<Y> yData;

}
