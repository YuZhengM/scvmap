package com.spring.boot.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TraitTfChunkMapper {

    List<String> selectListByTfAndGenome(@Param("tf") String tf,
                                         @Param("genome") String genome,
                                         @Param("signalId") int signalId);

    List<String> selectTraitIdByTfList(@Param("signalIdTraitTfListMap") Map<Integer, List<String>> signalIdTraitTfListMap,
                                       @Param("genome") String genome,
                                       @Param("pValue") Double pValue,
                                       @Param("qValue") Double qValue);
}
