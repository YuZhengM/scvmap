package com.spring.boot.util.util.result;

import lombok.*;

import java.io.Serializable;

/**
 * 分页的请求信息
 *
 * @author Zhengmin Yu
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Page implements Serializable {

    /**
     * 展示的页数
     */
    @Builder.Default
    private int page = 1;

    /**
     * 每页的行数
     */
    @Builder.Default
    private int size = 10;

    /**
     * 排序的字段
     */
    private String field;

    /**
     * 排序字段的顺序类型
     * 0: 不排序
     * 1: 升序
     * -1: 降序
     */
    @Builder.Default
    private int order = 0;

    /**
     * 搜索字段的字段名
     */
    private String searchField;

    /**
     * 搜索字段的内容信息
     */
    private String content;

    /**
     * 搜索字段的类型信息
     * 1: 字符类型
     * 2: 数字类型
     */
    private int type = 1;

}
