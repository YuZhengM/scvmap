package com.spring.boot.mapper;

import com.spring.boot.pojo.TraitEnrich;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraitEnrichMapper {

    List<TraitEnrich> selectByTraitIdAndMethod(@Param("traitId") String traitId,
                                               @Param("method") String method,
                                               @Param("signalId") String signalId);
}