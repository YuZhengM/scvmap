package com.spring.boot.service;

import com.spring.boot.pojo.Trait;
import com.spring.boot.pojo.vo.TraitSearchVO;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.util.result.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchServiceTest {

    @Autowired
    private SearchService searchService;

    @Test
    void listTraitByTraitInfo() {
        TraitSearchVO param = new TraitSearchVO();
        param.setPage(Page.builder().field("f_trait_id").page(2).searchField("f_variant_count").content("4").order(1).build());
        PageResult<Trait> traitPageResult = searchService.listTraitByTraitInfo(param);
        System.out.println(traitPageResult.getData());
    }
}
