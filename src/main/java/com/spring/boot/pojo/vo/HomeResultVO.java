package com.spring.boot.pojo.vo;

import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.Trait;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class HomeResultVO implements Serializable {

    private String name;

    private String content;

    private List<Trait> traitList;

    private List<Sample> sampleList;

}
