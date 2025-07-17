package com.spring.boot.mapper;

import com.spring.boot.pojo.dto.TraitIdDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MagmaMapperTest {

    @Autowired
    private MagmaMapper magmaMapper;

    @Test
    void selectByTraitIdList() {
        List<TraitIdDTO> traitIdDTOList = new ArrayList<>();
        traitIdDTOList.add(TraitIdDTO.builder().id("1").traitIdList(Arrays.asList("trait_id_1", "trait_id_11")).build());
        traitIdDTOList.add(TraitIdDTO.builder().id("2").traitIdList(Arrays.asList("trait_id_2", "trait_id_12")).build());
        System.out.println(magmaMapper.selectByTraitIdList(traitIdDTOList, Arrays.asList("NEURL1", "NFYB", "KCND3"), 1, 1.0E-5, "hg19"));
    }
}
