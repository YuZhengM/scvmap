package com.spring.boot.mapper;

import com.spring.boot.pojo.CiceroSampleTraitGene;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiceroSampleTraitGeneMapper {

    List<CiceroSampleTraitGene> selectBySampleIdAndTraitId(@Param("sampleId") String sampleId,
                                                           @Param("signalId") String signalId,
                                                           @Param("traitId") String traitId);

    List<CiceroSampleTraitGene> selectBySampleIdAndTraitIdAndScore(@Param("sampleId") String sampleId,
                                                                   @Param("signalId") String signalId,
                                                                   @Param("traitId") String traitId,
                                                                   @Param("score") Double score);

}
