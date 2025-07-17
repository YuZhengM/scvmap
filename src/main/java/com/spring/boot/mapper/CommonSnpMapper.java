package com.spring.boot.mapper;

import com.spring.boot.pojo.CommonSnp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonSnpMapper {
    List<CommonSnp> selectByRegionAndGenome(@Param("chr") String chr,
                                            @Param("start") Integer start,
                                            @Param("end") Integer end,
                                            @Param("genome") String genome);
}
