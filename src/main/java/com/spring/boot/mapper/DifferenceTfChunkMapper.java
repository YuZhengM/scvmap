package com.spring.boot.mapper;

import com.spring.boot.pojo.DifferenceTfChunk;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DifferenceTfChunkMapper {

    List<DifferenceTfChunk> selectBySampleIdList(@Param("sampleIdList") List<String> sampleIdList,
                                                 @Param("tfList") List<String> tfList,
                                                 @Param("log2FoldChange") Double log2FoldChange,
                                                 @Param("adjustedPValue") Double adjustedPValue,
                                                 @Param("pValue") Double pValue);

    List<DifferenceTfChunk> selectBySampleIdAndCellTypeAndTfList(@Param("sampleId") String sampleId,
                                                                 @Param("cellType") String cellType,
                                                                 @Param("tfList") List<String> tfList,
                                                                 @Param("log2FoldChange") Double log2FoldChange,
                                                                 @Param("adjustedPValue") Double adjustedPValue,
                                                                 @Param("pValue") Double pValue);

    List<DifferenceTfChunk> selectBySampleIdAndTfList(@Param("sampleId") String sampleId,
                                                      @Param("cellType") String cellType,
                                                      @Param("tfList") List<String> tfList,
                                                      @Param("count") Integer count);

    List<DifferenceTfChunk> selectBySampleIdAndCellType(@Param("sampleId") String sampleId, @Param("cellType") String cellType);

    List<DifferenceTfChunk> selectBySampleIdAndCellTypeWithTop(@Param("sampleId") String sampleId,
                                                               @Param("cellType") String cellType,
                                                               @Param("log2FoldChange") Double log2FoldChange,
                                                               @Param("pValue") Double pValue,
                                                               @Param("count") Integer count);

    List<String> selectGeneBySampleIdWithTop(@Param("sampleId") String sampleId,
                                             @Param("log2FoldChange") Double log2FoldChange,
                                             @Param("pValue") Double pValue,
                                             @Param("count") Integer count);

}
