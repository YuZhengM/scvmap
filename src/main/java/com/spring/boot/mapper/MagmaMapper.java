package com.spring.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.pojo.Magma;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MagmaMapper extends BaseMapper<Magma> {

    List<Magma> selectByTraitId(@Param("signalId") String signalId, @Param("traitId") String traitId, @Param("genome") String genome);

    List<Magma> selectByTraitIdAndGeneList(@Param("signalId") String signalId,
                                           @Param("traitId") String traitId,
                                           @Param("genome") String genome,
                                           @Param("geneList") List<String> geneList,
                                           @Param("pValue") Double pValue,
                                           @Param("min") Integer min,
                                           @Param("count") Integer count);

    List<Magma> selectByTraitIdAndGene(@Param("signalId") String signalId,
                                       @Param("traitId") String traitId,
                                       @Param("gene") String gene,
                                       @Param("genome") String genome);

    List<Magma> selectByTraitIdWithTop(@Param("signalId") String signalId,
                                       @Param("traitId") String traitId,
                                       @Param("genome") String genome,
                                       @Param("count") Integer count);
}
