package com.spring.boot.util.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Stores Token information
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
     * User ID
     */
    private String userId;

    /**
     * Collection of user role IDs
     */
    private List<String> roleIdList;

    /**
     * Other information
     */
    private Map<String, Object> content;

    /**
     * User login time
     */
    private Date loginTime;

}
