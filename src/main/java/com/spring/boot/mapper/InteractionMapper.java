package com.spring.boot.mapper;

import com.spring.boot.pojo.Interaction;
import com.spring.boot.util.util.result.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionMapper {
    List<Interaction> selectByTraitId(@Param("traitId") String traitId,
                                      @Param("signalId") String signalId,
                                      @Param("genome") String genome,
                                      @Param("page") Page page);
}
