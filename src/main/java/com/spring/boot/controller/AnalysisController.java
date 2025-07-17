package com.spring.boot.controller;

import com.spring.boot.pojo.MagmaAnno;
import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.Trait;
import com.spring.boot.pojo.TraitSample;
import com.spring.boot.pojo.vo.*;
import com.spring.boot.service.AnalysisService;
import com.spring.boot.util.model.Result;
import com.spring.boot.util.model.vo.canvasXpress.CanvasXpressHeatMapData;
import com.spring.boot.util.model.vo.echarts.EchartsGraphData;
import com.spring.boot.util.util.ListUtil;
import com.spring.boot.util.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.spring.boot.util.util.ApplicationUtil.*;

/**
 * Controller class for handling analysis-related requests.
 * This class provides REST endpoints for various analysis operations such as querying traits, samples, genes, and transcription factors (TFs).
 *
 * @author Zhengmin Yu
 */
@RequestMapping("/analysis")
@CrossOrigin
@RestController
public class AnalysisController {

    private AnalysisService analysisService;

    public AnalysisController() {
    }

    @Autowired
    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    /**
     * Retrieves a list of all traits.
     *
     * @return Result object containing a list of Trait objects.
     */
    @GetMapping("/trait/list")
    public Result<List<Trait>> listTrait() {
        List<Trait> traitSimpleList = analysisService.listTrait();
        return ResultUtil.success("[listTrait]: Query result for traits", traitSimpleList);
    }

    /**
     * Retrieves a list of traits associated with a specific sample ID.
     *
     * @param sampleId The ID of the sample.
     * @return Result object containing a list of TraitSample objects.
     */
    @GetMapping("/trait/list/{sample_id}")
    public Result<List<TraitSample>> listTraitBySampleId(@PathVariable("sample_id") String sampleId) {
        checkSampleId(sampleId);
        List<TraitSample> traitSimpleList = analysisService.listTraitBySampleId(sampleId);
        return ResultUtil.success("[listTraitBySampleId]: Query result for traits by sample ID", traitSimpleList);
    }

    /**
     * Retrieves a list of traits associated with a specific sample ID and a list of trait IDs.
     *
     * @param sampleId           The ID of the sample.
     * @param sampleTraitHeatmap The request body containing a list of trait IDs.
     * @return Result object containing a list of TraitSample objects.
     * @throws IOException If an I/O error occurs.
     */
    @PostMapping("/trait/list/{sample_id}")
    public Result<List<TraitSample>> listTraitBySampleIdAndTraitIdList(@PathVariable("sample_id") String sampleId,
                                                                       @RequestBody SampleTraitHeatmap sampleTraitHeatmap) throws IOException {
        checkSampleId(sampleId);
        List<TraitSample> traitSimpleList = analysisService.listTraitBySampleIdAndTraitIdList(sampleId, sampleTraitHeatmap.getTraitIdList());
        return ResultUtil.success("[listTraitBySampleIdAndTraitIdList]: Query result for traits by sample ID and trait IDs", traitSimpleList);
    }

    /**
     * Retrieves a list of all samples.
     *
     * @return Result object containing a list of Sample objects.
     */
    @GetMapping("/sample/list")
    public Result<List<Sample>> listSample() {
        List<Sample> sampleList = analysisService.listSample();
        return ResultUtil.success("[listSample]: Query result for samples", sampleList);
    }

    /**
     * Retrieves heatmap data for a specific sample and method, based on a list of trait IDs.
     *
     * @param sampleId           The ID of the sample.
     * @param method             The method used for generating the heatmap.
     * @param strategy           The strategy used for generating the heatmap.
     * @param sampleTraitHeatmap The request body containing a list of trait IDs.
     * @return Result object containing CanvasXpressHeatMapData.
     * @throws IOException If an I/O error occurs.
     */
    @PostMapping("/sample/heatmap/{sample_id}/{method}/{strategy}")
    public Result<CanvasXpressHeatMapData<Double>> getSampleTraitsHeatmap(@PathVariable("sample_id") String sampleId,
                                                                          @PathVariable("method") String method,
                                                                          @PathVariable("strategy") String strategy,
                                                                          @RequestBody SampleTraitHeatmap sampleTraitHeatmap) throws IOException {
        checkSampleId(sampleId);
        checkMethod(method);
        checkStrategy(strategy);
        List<String> traitIdList = sampleTraitHeatmap.getTraitIdList();
        if (ListUtil.isEmpty(traitIdList)) {
            return ResultUtil.success("[getSampleTraitsHeatmap]: Query result for sample traits heatmap", CanvasXpressHeatMapData.<Double>builder().build());
        }
        CanvasXpressHeatMapData<Double> canvasXpressHeatMapData = analysisService.getSampleTraitsHeatmap(sampleId, method, strategy, traitIdList);
        return ResultUtil.success("[getSampleTraitsHeatmap]: Query result for sample traits heatmap", canvasXpressHeatMapData);
    }

    /**
     * Retrieves analysis results based on a list of genes.
     *
     * @param analysisGeneVO The request body containing gene-related data.
     * @return Result object containing AnalysisGeneResultVO.
     */
    @PostMapping("/gene")
    public Result<AnalysisGeneResultVO> listDataByGenes(@RequestBody AnalysisGeneVO analysisGeneVO) {
        AnalysisGeneResultVO analysisGeneResultVO = analysisService.listDataByGenes(analysisGeneVO);
        return ResultUtil.success("[listDataByGenes]: Query result for gene data", analysisGeneResultVO);
    }

    /**
     * Retrieves analysis results based on a list of transcription factors (TFs).
     *
     * @param analysisTfVO The request body containing TF-related data.
     * @return Result object containing AnalysisTfResultVO.
     */
    @PostMapping("/tf")
    public Result<AnalysisTfResultVO> listDataByTfs(@RequestBody AnalysisTfVO analysisTfVO) {
        AnalysisTfResultVO analysisTfResultVO = analysisService.listDataByTfs(analysisTfVO);
        return ResultUtil.success("[listDataByTfs]: Query result for TF data", analysisTfResultVO);
    }

    /**
     * Retrieves MAGMA annotation data for a specific trait, genome, and gene.
     *
     * @param traitId The ID of the trait.
     * @param genome  The genome type.
     * @param gene    The gene name.
     * @return Result object containing a list of MagmaAnno objects.
     */
    @GetMapping("/magma/gene/{trait_id}/{genome}/{gene}")
    public Result<List<MagmaAnno>> listMagmaVariantInfoDataByTraitIdAndGene(@PathVariable("trait_id") String traitId,
                                                                            @PathVariable("genome") String genome,
                                                                            @PathVariable("gene") String gene) {
        checkTraitId(traitId);
        checkGenome(genome);
        List<MagmaAnno> magmaAnnoList = analysisService.listMagmaVariantInfoDataByTraitIdAndGene(traitId, genome, gene);
        return ResultUtil.success("[listVariantInfoDataByTraitIdAndGene]: Query result for MAGMA variant info data", magmaAnnoList);
    }

    /**
     * Retrieves gene regulation graph data.
     *
     * @param regulationGraphVO The request body containing graph-related data.
     * @return Result object containing EchartsGraphData.
     */
    @PostMapping("/gene/regulation/graph")
    public Result<EchartsGraphData> getGeneGraphData(@RequestBody RegulationGraphVO regulationGraphVO) {
        checkTraitId(regulationGraphVO.getTraitId());
        checkSampleId(regulationGraphVO.getSampleId());
        EchartsGraphData echartsGraphData = analysisService.getGeneGraphData(regulationGraphVO);
        return ResultUtil.success("[getGeneGraphData]: Query result for gene regulation graph data", echartsGraphData);
    }

    /**
     * Retrieves transcription factor (TF) regulation graph data.
     *
     * @param regulationGraphVO The request body containing graph-related data.
     * @return Result object containing EchartsGraphData.
     */
    @PostMapping("/tf/regulation/graph")
    public Result<EchartsGraphData> getTfGraphData(@RequestBody RegulationGraphVO regulationGraphVO) {
        checkTraitId(regulationGraphVO.getTraitId());
        checkSampleId(regulationGraphVO.getSampleId());
        EchartsGraphData echartsGraphData = analysisService.getTfGraphData(regulationGraphVO);
        return ResultUtil.success("[getTfGraphData]: Query result for TF regulation graph data", echartsGraphData);
    }

    /**
     * Retrieves gene enrichment data based on the provided criteria.
     *
     * @param geneEnrichmentVO The request body containing gene enrichment criteria.
     * @return Result object containing GeneEnrichmentResultVO.
     */
    @PostMapping("/gene/enrichment")
    public Result<GeneEnrichmentResultVO> listGeneEnrichmentData(@RequestBody GeneEnrichmentVO geneEnrichmentVO) {
        GeneEnrichmentResultVO geneEnrichmentList = analysisService.listGeneEnrichmentData(geneEnrichmentVO);
        return ResultUtil.success("[listGeneEnrichmentData]: Query result for gene enrichment data", geneEnrichmentList);
    }

    /**
     * Retrieves a list of samples associated with a specific gene.
     *
     * @param gene The gene name.
     * @return Result object containing a list of Sample objects.
     */
    @GetMapping("/sample/gene/{gene}")
    public Result<List<Sample>> listSampleDataByGene(@PathVariable String gene) {
        List<Sample> sampleList = analysisService.listSampleDataByGene(gene);
        return ResultUtil.success("[listSampleDataByGene]: Query result for samples by gene", sampleList);
    }

    /**
     * Retrieves a list of samples associated with a specific transcription factor (TF).
     *
     * @param tf The transcription factor name.
     * @return Result object containing a list of Sample objects.
     */
    @GetMapping("/sample/tf/{tf}")
    public Result<List<Sample>> listSampleDataByTf(@PathVariable String tf) {
        List<Sample> sampleList = analysisService.listSampleDataByTf(tf);
        return ResultUtil.success("[listSampleDataByTf]: Query result for samples by TF", sampleList);
    }

}
