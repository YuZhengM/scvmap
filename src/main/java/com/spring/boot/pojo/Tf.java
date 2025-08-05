package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * t_tf
 */
@TableName("t_tf")
@Data
@Schema(name = "Tf", description = "Table for storing TF information")
public class Tf implements Serializable {

    @Schema(description = "TF ID")
    @TableId("f_tf_id")
    private String tfId;

    @Schema(description = "TF name")
    @TableField("f_tf_name")
    private String tfName;

    @Schema(description = "TF family")
    @TableField("f_family")
    private String family;

    @Schema(description = "TF protein")
    @TableField("f_protein")
    private String protein;

    @Schema(description = "Entrez ID")
    @TableField("f_entrez_id")
    private String entrezId;

    @Serial
    private static final long serialVersionUID = 1L;
}
