package com.spring.boot.service;

import com.spring.boot.pojo.vo.HomeResultVO;

public interface HomeService {

    HomeResultVO getIdByContent(String label, String content);

}
