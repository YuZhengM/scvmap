package com.spring.boot.service;

import com.spring.boot.pojo.Trait;
import com.spring.boot.util.model.vo.echarts.EchartsGraphData;

import java.util.List;

public interface VariantDetailService {

    List<Trait> listTraitByRsId(String rsId, String genome);

    EchartsGraphData getVariantRelevanceGraph(String rsId, String genome);
}
