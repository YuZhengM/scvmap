package com.spring.boot.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SampleGeneChunkMapper {

    List<String> selectSampleIdByGene(@Param("gene") String gene, @Param("signalId") int signalId);

    List<String> selectSampleIdByGeneList(@Param("signalIdSampleGeneListMap") Map<Integer, List<String>> signalIdSampleGeneListMap,
                                          @Param("log2FoldChange") Double log2FoldChange,
                                          @Param("adjustedPValue") Double adjustedPValue,
                                          @Param("pValue") Double pValue);

}