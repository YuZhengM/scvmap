package com.spring.boot.mapper;

import com.spring.boot.pojo.TraitGeneEnrichment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraitGeneEnrichmentMapper {
    List<TraitGeneEnrichment> selectByTraitId(@Param("signalId") String signalId,
                                              @Param("traitId") String traitId,
                                              @Param("geneSet") String geneSet,
                                              @Param("pValue") Double pValue,
                                              @Param("genome") String genome);

}
