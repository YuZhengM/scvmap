package com.spring.boot.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class AnalysisElementVO implements Serializable {

    private String content;
    private String fileId;
    private Integer isFile;

}
