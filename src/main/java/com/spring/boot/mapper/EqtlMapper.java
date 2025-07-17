package com.spring.boot.mapper;

import com.spring.boot.pojo.Eqtl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EqtlMapper {
    List<Eqtl> selectByRegionAndGenome(@Param("geneName") String geneName,
                                       @Param("genome") String genome,
                                       @Param("symbol") int symbol);
}
