package com.spring.boot.mapper;

import com.spring.boot.pojo.EnhancerSedb;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnhancerSedbMapper {
    List<? extends EnhancerSedb> selectByRegionAndGenome(String chr, Integer start, Integer end, String genome);
}
