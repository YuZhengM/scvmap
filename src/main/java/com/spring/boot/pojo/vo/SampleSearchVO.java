package com.spring.boot.pojo.vo;

import com.spring.boot.util.util.result.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YuZhengMin
 * @version V1.0.0
 * @date 2024-10-12 21:21
 */
@Data
public class SampleSearchVO implements Serializable {

    private String tissueType;
    private String cellType;

    private Page page;

}
