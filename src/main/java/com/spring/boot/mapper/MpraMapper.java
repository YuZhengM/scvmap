package com.spring.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.pojo.Mpra;
import com.spring.boot.util.util.result.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MpraMapper extends BaseMapper<Mpra> {

    List<Mpra> selectByTraitIdAndGenome(String traitId, String signalId, String genome, Page page);

}
