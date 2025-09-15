package com.spring.boot.mapper;

import com.spring.boot.pojo.UserOnLine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class UserOnLineMapperTest {

    @Autowired
    private UserOnLineMapper userOnLineMapper;

    @Test
    void test() {

        UserOnLine userOnLine = UserOnLine.builder()
                .jobId("Test")
                .userEmail("test")
                .jobStatus("In progress")
                .scFileId("test")
                .variantFileId("test")
                .userIp("test")
                .createTime(new Date())
                .updateTime(new Date()).build();

        userOnLineMapper.insert(userOnLine);
    }

}