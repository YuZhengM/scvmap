package com.spring.boot.util.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class NumberUtilTest {

    @Test
    void getUniqueId() {
    }

    public static void main(String[] args) {
        List<String> data = Arrays.asList("d", "a", "d", "d", "a");
        String sampleId = "trait_id_3340";
        data.add(sampleId);

    }

}