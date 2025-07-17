package com.spring.boot.mapper;

import com.spring.boot.pojo.TraitSample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraitSampleMapper {

    List<TraitSample> selectBySampleIdAndMethod(String sampleId, String method);

    List<TraitSample> selectByTraitIdAndMethod(String traitId, String method);

    List<TraitSample> selectByTraitIdListAndMethod(String sampleId, String method, List<String> traitIdList);

}