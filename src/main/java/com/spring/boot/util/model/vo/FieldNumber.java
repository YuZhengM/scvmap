package com.spring.boot.util.model.vo;

import lombok.*;

import java.io.Serializable;

/**
 * 字段内容数量信息
 *
 * @author Zhengmin Yu
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class FieldNumber implements Serializable {

    /**
     * 某个字段下的内容
     */
    private String field;

    /**
     * 某个字段下的内容的数量
     */
    private Integer number;

}
