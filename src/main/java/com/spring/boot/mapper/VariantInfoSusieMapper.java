package com.spring.boot.mapper;

import com.spring.boot.pojo.VariantInfo;
import com.spring.boot.util.util.result.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantInfoSusieMapper {
    List<VariantInfo> selectByTraitId(@Param("traitId") String traitId,
                                      @Param("genome") String genome,
                                      @Param("page") Page page);
}
