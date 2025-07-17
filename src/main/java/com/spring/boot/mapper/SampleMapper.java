package com.spring.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.pojo.Sample;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleMapper extends BaseMapper<Sample> {
}