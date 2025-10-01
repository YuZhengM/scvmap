package com.spring.boot.mapper;

import com.spring.boot.pojo.Eqtl;
import com.spring.boot.util.util.result.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EqtlMapper {
    List<Eqtl> selectByRegionAndGenome(@Param("geneName") String geneName,
                                       @Param("genome") String genome,
                                       @Param("symbol") int symbol);

    List<Eqtl> selectByTraitIdAndChrPosition(@Param("chrList") List<String> chrList,
                                             @Param("traitId") String traitId,
                                             @Param("signalId") String signalId,
                                             @Param("genome") String genome,
                                             @Param("page") Page page);
}
