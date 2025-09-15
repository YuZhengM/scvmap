package com.spring.boot.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class BaseSnpGene implements Serializable {

    @Schema(description = "Gene name")
    private String gene;

    @Schema(description = "rsID identifier")
    private String rsId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseSnpGene that = (BaseSnpGene) o;
        return Objects.equals(gene, that.gene) && Objects.equals(rsId, that.rsId);
    }

}
