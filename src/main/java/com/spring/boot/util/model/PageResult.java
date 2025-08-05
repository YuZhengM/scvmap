package com.spring.boot.util.model;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Paged data return result information
 *
 * @author Zhengmin Yu
 */
@ToString
@Data
public class PageResult<T> implements Serializable {

    /**
     * Data of the current page
     */
    @Schema(description = "Data of the current page")
    private List<T> data;

    /**
     * Total quantity
     */
    @Schema(description = "Total quantity")
    private long total;

    /**
     * Page number of the current page
     */
    @Schema(description = "Page number of the current page")
    private int pageNum;

    /**
     * Number of items per page
     */
    @Schema(description = "Number of items per page")
    private int pageSize;

    /**
     * Number of rows on the current page
     */
    @Schema(description = "Number of rows on the current page")
    private int size;

    /**
     * Starting row number of the current page
     */
    @Schema(description = "Starting row number of the current page")
    private long startRow;

    /**
     * Ending row number of the current page
     */
    @Schema(description = "Ending row number of the current page")
    private long endRow;

    /**
     * Total number of pages
     */
    @Schema(description = "Total number of pages")
    private int pages;

    public PageResult(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        this.data = pageInfo.getList();
        this.total = pageInfo.getTotal();
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.size = pageInfo.getSize();
        this.startRow = pageInfo.getStartRow();
        this.endRow = pageInfo.getEndRow();
        this.pages = pageInfo.getPages();
    }
}
