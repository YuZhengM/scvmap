package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class BaseTraitCount implements Serializable {

    @TableField("f_count")
    private Integer count;

    @TableField("f_genome")
    private String genome;

}
