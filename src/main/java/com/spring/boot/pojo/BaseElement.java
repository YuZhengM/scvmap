package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class BaseElement implements Serializable {

    @TableField("f_trait_id")
    private String traitId;

    @TableField(value = "count(DISTINCT f_trait_id)", select = false)
    private Long count;

}
