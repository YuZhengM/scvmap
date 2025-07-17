package com.spring.boot.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SampleTfChunkMapper {

    List<String> selectSampleIdByTf(@Param("tf") String tf, @Param("signalId") int signalId);

    List<String> selectSampleIdByTfList(@Param("signalIdSampleTfListMap") Map<Integer, List<String>> signalIdSampleTfListMap,
                                        @Param("log2FoldChange") Double log2FoldChange,
                                        @Param("adjustedPValue") Double adjustedPValue,
                                        @Param("pValue") Double pValue);

}