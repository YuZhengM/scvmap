package com.spring.boot.util.model.vo.echarts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Echarts graph relationship data
 *
 * @author Zhengmin Yu
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Schema(description = "Echarts graph relationship data model")
public class EchartsGraphData implements Serializable {

    /**
     * Node information
     */
    @Schema(description = "List of node information")
    private List<EchartsNode> nodes;

    /**
     * Edge information
     */
    @Schema(description = "List of edge information")
    private List<EchartsLink> links;

    /**
     * Category information
     */
    @Schema(description = "List of category information")
    private List<EchartsCategories> categories;

}
