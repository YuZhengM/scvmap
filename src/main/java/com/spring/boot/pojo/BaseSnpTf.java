package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Objects;

@Data
public class BaseSnpTf {

    @Schema(description = "TF name")
    private String tf;

    @Schema(description = "rsID identifier")
    private String rsId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseSnpTf that = (BaseSnpTf) o;
        return Objects.equals(tf, that.tf) && Objects.equals(rsId, that.rsId);
    }

}
