package com.spring.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.pojo.VariantInfo;
import com.spring.boot.util.util.result.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantInfoMapper extends BaseMapper<VariantInfo> {

    List<VariantInfo> selectByTraitId(@Param("traitId") String traitId,
                                      @Param("signalId") String signalId,
                                      @Param("genome") String genome,
                                      @Param("page") Page page);
}
