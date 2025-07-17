package com.spring.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.pojo.SampleCell;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleCellMapper extends BaseMapper<SampleCell> {
}