package com.spring.boot.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommonSnpMapperTest {

    @Autowired
    private CommonSnpMapper commonSnpMapper;

    @Test
    void selectByRegionAndGenome() {
        System.out.println(commonSnpMapper.selectByRegionAndGenome("chr19", 44905791, 44909393, "hg38"));
    }
}