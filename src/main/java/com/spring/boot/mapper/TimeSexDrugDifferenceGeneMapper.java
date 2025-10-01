package com.spring.boot.mapper;

import com.spring.boot.pojo.DifferenceGene;
import com.spring.boot.pojo.DifferenceGeneChunk;
import com.spring.boot.pojo.TimeSexDrugDifferenceGene;
import com.spring.boot.util.util.result.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSexDrugDifferenceGeneMapper {
    List<String> selectGeneBySampleIdWithTop(@Param("sampleId") String sampleId,
                                             @Param("metadata") String metadata,
                                             @Param("log2FoldChange") Double log2FoldChange,
                                             @Param("count") Integer count);

    List<TimeSexDrugDifferenceGene> selectBySampleIdAndCellTypeAndGeneListWithTop(@Param("sampleId") String sampleId,
                                                                                  @Param("metadata") String metadata,
                                                                                  @Param("typeValue") String typeValue,
                                                                                  @Param("geneList") List<String> geneList,
                                                                                  @Param("log2FoldChange") Double log2FoldChange,
                                                                                  @Param("adjustedPValue") Double adjustedPValue,
                                                                                  @Param("pValue") Double pValue,
                                                                                  @Param("count") Integer count);

    List<DifferenceGene> selectBySampleIdAndCellType(@Param("sampleId") String sampleId,
                                                     @Param("metadata") String metadata,
                                                     @Param("typeValue") String typeValue,
                                                     @Param("page") Page page);
}
