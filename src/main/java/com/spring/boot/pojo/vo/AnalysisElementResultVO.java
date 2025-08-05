package com.spring.boot.pojo.vo;

import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.Trait;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "AnalysisElementResultVO", description = "Result returned by the analysis function")
public class AnalysisElementResultVO implements Serializable {

    @Schema(description = "List of samples")
    List<Sample> sampleList;

    @Schema(description = "List of traits")
    List<Trait> traitList;

}
