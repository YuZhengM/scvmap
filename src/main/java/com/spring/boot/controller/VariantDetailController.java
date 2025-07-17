package com.spring.boot.controller;

import com.spring.boot.pojo.Trait;
import com.spring.boot.service.VariantDetailService;
import com.spring.boot.util.model.Result;
import com.spring.boot.util.model.vo.echarts.EchartsGraphData;
import com.spring.boot.util.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spring.boot.util.util.ApplicationUtil.checkGenome;

/**
 * Controller for handling requests related to variant details.
 *
 * @author Zhengmin Yu
 */
@RequestMapping("/variant/detail")
@CrossOrigin
@RestController
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
    @GetMapping("/variant/graph/{rsId}/{genome}")
    public Result<EchartsGraphData> getVariantRelevanceGraph(@PathVariable("rsId") String rsId, @PathVariable("genome") String genome) {
        checkGenome(genome);
        EchartsGraphData echartsGraphData = variantDetailService.getVariantRelevanceGraph(rsId, genome);
        return ResultUtil.success("[getVariantRelevanceGraph]: Obtain SNP-relevant graph", echartsGraphData);
    }

}
