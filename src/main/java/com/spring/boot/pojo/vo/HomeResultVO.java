package com.spring.boot.pojo.vo;

import com.spring.boot.util.model.PageResult;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;

@Builder
@Data
@Schema(name = "HomeResultVO", description = "Results returned by simple query")
public class HomeResultVO implements Serializable {
    
    /**
     * Category of query conditions
     */
    @Schema(name = "name", description = "Category of query conditions")
    private String name;

    /**
     * Content of query conditions
     */
    @Schema(name = "content", description = "Content of query conditions")
    private String content;

    /**
     * Results after query
     */
    @Schema(name = "dataList", description = "Results after query")
    private PageResult<?> dataList;

}
