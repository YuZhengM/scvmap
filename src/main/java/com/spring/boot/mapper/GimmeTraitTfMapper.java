package com.spring.boot.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GimmeTraitTfMapper {
    List<String> selectByCoScoreAndMeanScore(@Param("signalIdTraitTfListMap") Map<Integer, List<String>> signalIdTraitTfListMap,
                                             @Param("coScore") Double coScore,
                                             @Param("meanScore") Double meanScore);
}
