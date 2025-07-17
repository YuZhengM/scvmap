package com.spring.boot;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScvdbApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        String apiPath = "D:\\output\\SCVdb\\API";
        String version = "V1.0.0";

        DocsConfig config = new DocsConfig();
        // 项目根目录
        config.setProjectPath("D:\\project\\scvdb");
        // 项目名称
        config.setProjectName("SCVdb");
        // 声明该 API 的版本
        config.setApiVersion(version);
        // 生成API 文档所在目录
        config.setDocsPath(apiPath);
        // 配置自动生成
        config.setAutoGenerate(Boolean.TRUE);
        // 执行生成文档
        Docs.buildHtmlDocs(config);
    }

}
