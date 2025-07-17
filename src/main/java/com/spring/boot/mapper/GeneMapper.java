package com.spring.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.pojo.Gene;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneMapper extends BaseMapper<Gene> {
}
