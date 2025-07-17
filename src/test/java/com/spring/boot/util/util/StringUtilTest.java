package com.spring.boot.util.util;

import com.spring.boot.pojo.BaseSample;
import com.spring.boot.pojo.SampleCellType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StringUtilTest {

    @Test
    void getUniqueId() {
        System.out.println(StringUtil.getUniqueId());
    }
}