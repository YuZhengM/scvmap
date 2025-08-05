package com.spring.boot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "Used to store trait information for requests and trait-cell type heatmaps.")
public class SampleTraitHeatmap implements Serializable {

    @Schema(description = "List of trait ID.", example = "[\"trait_id_1\", \"trait_id_2\", \"trait_id_3\", \"trait_id_894\"]")
    private List<String> traitIdList;

}
