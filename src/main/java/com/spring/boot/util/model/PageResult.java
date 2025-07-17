package com.spring.boot.util.model;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据返回结果信息
 *
 * @author Zhengmin Yu
 */
@ToString
@Data
public class PageResult<T> implements Serializable {

    /**
     * 当前分页的数据
     */
    private List<T> data;
    /**
     * 总数量
     */
    private long total;
    /**
     * 当前页的页数
     */
    private int pageNum;
    /**
     * 当前页的数量
     */
    private int pageSize;
    /**
     * 当前页的行数
     */
    private int size;
    /**
     * 当前页的开始的行数
     */
    private long startRow;
    /**
     * 当前页的结束的行数
     */
    private long endRow;
    /**
     * 总页数
     */
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
