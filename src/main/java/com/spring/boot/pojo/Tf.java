package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_tf
 */
@TableName("t_tf")
@Data
public class Tf implements Serializable {

    @TableId("f_tf_id")
    private String tfId;

    @TableField("f_tf_name")
    private String tfName;

    @TableField("f_family")
    private String family;

    @TableField("f_protein")
    private String protein;

    @TableField("f_entrez_id")
    private String entrezId;

    @Serial
    private static final long serialVersionUID = 1L;
}