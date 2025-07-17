package com.spring.boot.util.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 存放 Token 信息
 *
 * @author Zhengmin Yu
 */
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Token implements Serializable {

    /**
     * 用户 ID
     */
    private String userId;

    /**
     * 用户角色 ID 集合
     */
    private List<String> roleIdList;

    /**
     * 其他信息
     */
    private Map<String, Object> content;

    /**
     * 用户登录时间
     */
    private Date loginTime;

}
