package com.spring.boot.mapper;

import com.spring.boot.pojo.TfGeneSample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TfGeneSampleMapper {

    List<TfGeneSample> selectByTfListGeneListByScore(@Param("sampleId") String sampleId,
                                                     @Param("tfList") List<String> tfList,
                                                     @Param("geneList") List<String> geneList,
                                                     @Param("meanScore") Double meanScore,
                                                     @Param("coScore") Double coScore);

}
