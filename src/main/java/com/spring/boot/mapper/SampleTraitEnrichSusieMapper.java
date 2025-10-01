package com.spring.boot.mapper;

import com.spring.boot.pojo.SampleTraitEnrichSusie;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleTraitEnrichSusieMapper {
    List<SampleTraitEnrichSusie> selectByTraitIdAndMethod(@Param("traitId") String traitId,
                                                          @Param("method") String method);

    List<SampleTraitEnrichSusie> selectBySampleIdAndMethod(@Param("sampleId") String sampleId,
                                                           @Param("method") String method);
}
