package com.spring.boot.controller;

import com.spring.boot.pojo.Trait;
import com.spring.boot.service.VariantDetailService;
import com.spring.boot.util.model.Result;
import com.spring.boot.util.model.vo.echarts.EchartsGraphData;
import com.spring.boot.util.util.result.ResultUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spring.boot.util.constant.ApplicationConstant.GENOME_EXAMPLE;
import static com.spring.boot.util.constant.ApplicationConstant.VARIANT_EXAMPLE;
import static com.spring.boot.util.util.ApplicationUtil.checkGenome;

/**
 * Controller for handling requests related to variant details.
 *
 * @author Zhengmin Yu
 */
@RequestMapping("/variant/detail")
@CrossOrigin
@RestController
@Tag(name = "SNP-Detail-API", description = "Controller for handling requests related to variant details")
public class VariantDetailController {

    private VariantDetailService variantDetailService;

    public VariantDetailController() {
    }

    @Autowired
    public VariantDetailController(VariantDetailService variantDetailService) {
        this.variantDetailService = variantDetailService;
    }

    /**
     * Obtain trait or disease information for the SNP.
     * @param rsId   rsID
     * @param genome Genome
     * @return Trait or disease information
     */
    @Operation(
        summary = "Obtain trait or disease information for the SNP",
        description = "Retrieves trait or disease information based on the provided rsID and genome.",
        parameters = {
            @Parameter(name = "rsId", in = ParameterIn.PATH, description = "The rsID of the SNP.", required = true, example = VARIANT_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome information.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/variant/{rsId}/{genome}")
    public Result<List<Trait>> listTraitByRsId(@PathVariable("rsId") String rsId, @PathVariable("genome") String genome) {
        checkGenome(genome);
        List<Trait> traitList = variantDetailService.listTraitByRsId(rsId, genome);
        return ResultUtil.success("[listTraitByRsId]: Obtain traits or diseases", traitList);
    }

    /**
     * Obtain the regulatory network of the SNP.
     * @param rsId   rsID
     * @param genome Genome
     * @return SNP regulation network
     */
    @Operation(
        summary = "Obtain the regulatory network of the SNP",
        description = "Retrieves the regulatory network of the SNP based on the provided rsID and genome.",
        parameters = {
            @Parameter(name = "rsId", in = ParameterIn.PATH, description = "The rsID of the SNP.", required = true, example = VARIANT_EXAMPLE),
            @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome information.", required = true, example = GENOME_EXAMPLE)
        }
    )
    @GetMapping("/variant/graph/{rsId}/{genome}")
    public Result<EchartsGraphData> getVariantRelevanceGraph(@PathVariable("rsId") String rsId, @PathVariable("genome") String genome) {
        checkGenome(genome);
        EchartsGraphData echartsGraphData = variantDetailService.getVariantRelevanceGraph(rsId, genome);
        return ResultUtil.success("[getVariantRelevanceGraph]: Obtain SNP-relevant graph", echartsGraphData);
    }

}
