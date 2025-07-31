package com.spring.boot.pojo.vo;

import com.spring.boot.util.model.PageResult;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class HomeResultVO implements Serializable {

    private String name;

    private String content;

    private PageResult<?> dataList;

}
