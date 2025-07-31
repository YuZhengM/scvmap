package com.spring.boot.service;

import com.spring.boot.pojo.vo.HomeResultVO;
import com.spring.boot.util.util.result.Page;

public interface HomeService {

    HomeResultVO getIdByContent(String label, String content, Page page);

}
