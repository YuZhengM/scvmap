package com.spring.boot.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CiceroSampleGeneMapper {
    List<String> selectSampleIdByGeneList(@Param("signalIdSampleGeneListMap") Map<Integer, List<String>> map,
                                          @Param("coScore") Double coScore);
}
