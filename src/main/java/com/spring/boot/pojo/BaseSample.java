package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class BaseSample implements Serializable {

    @TableField("f_tissue_type")
    private String tissueType;

    @TableField(value = "count(0)", select = false)
    private Long count;

}
