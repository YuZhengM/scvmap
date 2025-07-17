package com.spring.boot.mapper;

import com.spring.boot.pojo.SampleEnrichSampleId;
import com.spring.boot.pojo.TraitEnrich;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleEnrichSampleIdMapper {

    List<SampleEnrichSampleId> selectBySampleIdAndMethod(@Param("sampleId") String sampleId, @Param("method") String method);

}