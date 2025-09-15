package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class BaseCellTypeTf implements Serializable {

    @Schema(description = "Cell type")
    @TableField("f_cell_type")
    private String cellType;

    @Schema(description = "Transcription factor name")
    @TableField("f_tf")
    private String tf;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseCellTypeTf that = (BaseCellTypeTf) o;
        return Objects.equals(tf, that.tf) && Objects.equals(cellType, that.cellType);
    }

}
