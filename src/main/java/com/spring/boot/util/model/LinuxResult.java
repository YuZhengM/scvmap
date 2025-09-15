package com.spring.boot.util.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class LinuxResult implements Serializable {

    private List<String> resultList;

    private boolean status;

}
