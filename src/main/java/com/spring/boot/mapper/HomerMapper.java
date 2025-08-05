package com.spring.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.pojo.Homer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomerMapper extends BaseMapper<Homer> {

    List<Homer> selectByTraitIdAndTfList(@Param("signalId") String signalId,
                                         @Param("traitId") String traitId,
                                         @Param("genome") String genome,
                                         @Param("tfList") List<String> tfList,
                                         @Param("pValue") Double pValue,
                                         @Param("qValue") Double qValue);

    List<Homer> selectByTraitId(@Param("signalId") String signalId,
                                @Param("traitId") String traitId,
                                @Param("genome") String genome);

    List<Homer> selectByTraitIdAndGene(@Param("signalId") String signalId,
                                       @Param("traitId") String traitId,
                                       @Param("tf") String tf,
                                       @Param("genome") String genome);
}
