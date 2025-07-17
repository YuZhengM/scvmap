package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class BaseRegion implements Serializable {

    @TableField("f_chr")
    private String chr;

    @TableField("f_start")
    private Integer start;

    @TableField("f_end")
    private Integer end;

}
