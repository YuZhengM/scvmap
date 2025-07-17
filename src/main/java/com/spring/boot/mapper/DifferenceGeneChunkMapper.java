package com.spring.boot.mapper;

import com.spring.boot.pojo.DifferenceGeneChunk;
import com.spring.boot.pojo.DifferenceTfChunk;
import com.spring.boot.util.util.result.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DifferenceGeneChunkMapper {

    List<DifferenceGeneChunk> selectBySampleIdList(@Param("sampleIdList") List<String> sampleIdList,
                                                   @Param("geneList") List<String> geneList,
                                                   @Param("log2FoldChange") Double log2FoldChange,
                                                   @Param("adjustedPValue") Double adjustedPValue,
                                                   @Param("pValue") Double pValue);

    List<DifferenceGeneChunk> selectBySampleIdAndCellTypeAndGeneListWithTop(@Param("sampleId") String sampleId,
                                                                            @Param("cellType") String cellType,
                                                                            @Param("geneList") List<String> geneList,
                                                                            @Param("log2FoldChange") Double log2FoldChange,
                                                                            @Param("adjustedPValue") Double adjustedPValue,
                                                                            @Param("pValue") Double pValue,
                                                                            @Param("count") Integer count);

    List<DifferenceGeneChunk> selectBySampleIdAndGeneList(@Param("sampleId") String sampleId,
                                                          @Param("cellType") String cellType,
                                                          @Param("geneList") List<String> geneList,
                                                          @Param("count") Integer count);

    List<DifferenceGeneChunk> selectBySampleIdAndCellType(@Param("sampleId") String sampleId,
                                                          @Param("cellType") String cellType,
                                                          @Param("page") Page page);

    List<DifferenceGeneChunk> selectBySampleIdAndCellTypeWithTop(@Param("sampleId") String sampleId,
                                                                 @Param("cellType") String cellType,
                                                                 @Param("count") Integer count);

    List<String> selectGeneBySampleIdWithTop(@Param("sampleId") String sampleId,
                                             @Param("log2FoldChange") Double log2FoldChange,
                                             @Param("count") Integer count);

    List<String> selectGeneBySampleIdWithTopNoExpression(@Param("sampleId") String sampleId,
                                                         @Param("cellType") String cellType,
                                                         @Param("log2FoldChange") Double log2FoldChange);
}
