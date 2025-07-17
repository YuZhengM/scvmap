package com.spring.boot.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TraitGeneChunkMapper {

    List<String> selectListByGeneAndGenome(@Param("gene") String gene,
                                           @Param("genome") String genome,
                                           @Param("signalId") int signalId);

    List<String> selectTraitIdByGeneList(@Param("signalIdTraitGeneListMap") Map<Integer, List<String>> signalIdTraitGeneListMap,
                                         @Param("genome") String genome,
                                         @Param("pValue") Double pValue,
                                         @Param("min") Integer min);
}