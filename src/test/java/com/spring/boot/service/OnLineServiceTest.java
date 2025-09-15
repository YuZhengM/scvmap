package com.spring.boot.service;

import com.spring.boot.config.bean.ExecLinux;
import com.spring.boot.config.bean.ExecLinux2;
import com.spring.boot.pojo.vo.OnLineVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class OnLineServiceTest {

    @Autowired
    private OnLineService onLineService;

    @Autowired
    private ExecLinux execLinux;

    @Autowired
    private ExecLinux2 execLinux2;

    @Test
    void execUserAnalysisFunction() throws IOException {
        onLineService.execUserAnalysisFunction(
                "655d030036_GSE139369_ELM_sim_0.65_ATAC.rds",
                "84d769d9cd_BBJ_Mono_55.bed",
                OnLineVO.builder()
                        .jobId("750c470707").genome("hg19").userIp("0.0.0.0").layer("counts")
                        .email("yuzmbio@163.com").build()
        );
    }

    @Test
    void execLinux2() throws IOException {
//        List<String> results = execLinux.execCommand("ls").getResultList();
        List<String> results = execLinux2.execCommand("ls").getResultList();
        results.forEach(System.out::println);
    }

    @Test
    void jobResultAnalysis() {
        System.out.println(onLineService.jobResultAnalysis("15dac98905"));
    }
}