package com.spring.boot.mapper;

import com.spring.boot.pojo.GimmeSampleTraitTf;
import com.spring.boot.util.util.result.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GimmeSampleTraitTfMapper {
    List<GimmeSampleTraitTf> selectBySampleIdAndTraitId(@Param("sampleId") String sampleId,
                                                        @Param("signalId") String signalId,
                                                        @Param("traitId") String traitId,
                                                        @Param("page") Page page);

    List<GimmeSampleTraitTf> selectByScoreAndMeanScore(@Param("sampleId") String sampleId,
                                                       @Param("signalId") String signalId,
                                                       @Param("traitId") String traitId,
                                                       @Param("coScore") Double coScore,
                                                       @Param("meanScore") Double meanScore,
                                                       @Param("count") Integer count,
                                                       @Param("tfList") List<String> tfList);
}
