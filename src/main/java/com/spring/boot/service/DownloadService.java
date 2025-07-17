package com.spring.boot.service;

import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.Trait;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.util.result.Page;

import java.util.List;

public interface DownloadService {
    PageResult<Trait> listTrait(Page page);

    List<Sample> listSample();
}
