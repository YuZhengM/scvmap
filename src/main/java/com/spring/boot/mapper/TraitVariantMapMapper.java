package com.spring.boot.mapper;

import com.spring.boot.pojo.TraitVariantMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraitVariantMapMapper {

    List<String> selectTraitIdByRsIdAndGenome(@Param("rsId") String rsId, @Param("signalId") int signalId, @Param("genome") String genome);

    List<TraitVariantMap> selectByRsIdAndGenome(@Param("rsId") String rsId, @Param("signalId") int signalId, @Param("genome") String genome);

}
