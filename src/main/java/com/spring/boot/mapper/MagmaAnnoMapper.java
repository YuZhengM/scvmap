package com.spring.boot.mapper;

import com.spring.boot.pojo.MagmaAnno;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MagmaAnnoMapper {
    List<MagmaAnno> selectByTraitIdAndGene(@Param("signalId") String signalId,
                                           @Param("traitId") String traitId,
                                           @Param("genome") String genome,
                                           @Param("gene") String gene);

    List<MagmaAnno> selectByTraitIdAndGeneList(@Param("signalId") String signalId,
                                               @Param("traitId") String traitId,
                                               @Param("genome") String genome,
                                               @Param("geneList") List<String> geneList);

    List<MagmaAnno> selectByTraitIdListAndRsId(@Param("rsId") String rsId,
                                               @Param("genome") String genome,
                                               @Param("signalIdTraitListMap") Map<String, List<String>> signalIdTraitListMap);
}