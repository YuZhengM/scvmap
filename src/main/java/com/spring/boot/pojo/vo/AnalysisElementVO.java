package com.spring.boot.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Schema(name = "AnalysisElementVO", description = "Value object related to gene or TF analysis file parameters")
public class AnalysisElementVO implements Serializable {

    @Schema(description = "Content of the genes or TFs", example = "GLI1\nRCC2\nCASC15\n")
    private String content;

    @Schema(description = "File ID of the file submitted with gene and transcription factor parameters", example = "example_genes.txt")
    private String fileId;

    @Schema(description = "Flag indicating whether the user submitted a file (1 for yes, 0 for no)", example = "0")
    private Integer isFile;

}
