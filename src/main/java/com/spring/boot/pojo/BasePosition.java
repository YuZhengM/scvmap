package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class BasePosition implements Serializable {

    @TableField("f_chr")
    private String chr;

    @TableField("f_position")
    private Integer position;

}
