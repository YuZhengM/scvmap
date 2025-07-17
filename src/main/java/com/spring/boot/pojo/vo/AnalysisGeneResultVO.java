package com.spring.boot.pojo.vo;

import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.Trait;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class AnalysisGeneResultVO extends AnalysisElementResultVO implements Serializable {

    public AnalysisGeneResultVO(List<Sample> sampleList, List<Trait> traitList) {
        super(sampleList, traitList);
    }

}
