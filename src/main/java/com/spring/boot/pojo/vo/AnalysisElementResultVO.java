package com.spring.boot.pojo.vo;

import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.Trait;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnalysisElementResultVO implements Serializable {

    List<Sample> sampleList;

    List<Trait> traitList;

}
