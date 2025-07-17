package com.spring.boot.mapper;

import com.spring.boot.pojo.GeneEnrichment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneEnrichmentMapper {
    List<GeneEnrichment> selectBySampleIdAndCellType(@Param("sampleId") String sampleId,
                                                     @Param("geneSet") String geneSet,
                                                     @Param("cellType") String cellType,
                                                     @Param("value") Double value);

}
