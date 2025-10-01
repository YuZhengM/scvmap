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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.spring.boot.util.constant.ApplicationConstant.*;
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
@Tag(name = "Analysis-API", description = "Controller for handling analysis-related requests")
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
    @Operation(summary = "Retrieve all traits", description = "Retrieves a list of all traits.")
    @GetMapping("/trait/list")
    public Result<List<Trait>> listTrait() {
        List<Trait> traitList = analysisService.listTrait();
        return ResultUtil.success("[listTrait]: Query result for traits", traitList);
    }

    /**
     * Retrieves a list of traits associated with a specific sample ID.
     *
     * @param sampleId The ID of the sample.
     * @return Result object containing a list of TraitSample objects.
     */
    @Operation(
            summary = "Retrieve traits by sample ID",
            description = "Retrieves a list of traits associated with a specific sample ID.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "ID of the sample", required = true, example = SAMPLE_EXAMPLE)
            }
    )
    @GetMapping("/trait/list/{sample_id}")
    public Result<List<TraitSample>> listTraitBySampleId(@PathVariable("sample_id") String sampleId) {
        checkSampleId(sampleId);
        List<TraitSample> traitSimpleList = analysisService.listTraitBySampleId(sampleId);
        return ResultUtil.success("[listTraitBySampleId]: Query result for traits by sample ID", traitSimpleList);
    }

    /**
     * Retrieves a list of traits associated with a specific sample ID and a list of trait ID.
     *
     * @param sampleId           The ID of the sample.
     * @param sampleTraitHeatmap The request body containing a list of trait ID.
     * @return Result object containing a list of TraitSample objects.
     * @throws IOException If an I/O error occurs.
     */
    @Operation(
            summary = "Retrieve traits by sample ID and trait ID list",
            description = "Retrieves a list of traits associated with a specific sample ID and a list of trait ID.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "ID of the sample", required = true, example = SAMPLE_EXAMPLE)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body containing a list of trait ID", required = true,
                    content = @Content(schema = @Schema(implementation = SampleTraitHeatmap.class))
            )
    )
    @PostMapping("/trait/list/{sample_id}")
    public Result<List<TraitSample>> listTraitBySampleIdAndTraitIdList(@PathVariable("sample_id") String sampleId,
                                                                       @RequestBody SampleTraitHeatmap sampleTraitHeatmap) throws IOException {
        checkSampleId(sampleId);
        List<TraitSample> traitSimpleList = analysisService.listTraitBySampleIdAndTraitIdList(sampleId, sampleTraitHeatmap.getTraitIdList());
        return ResultUtil.success("[listTraitBySampleIdAndTraitIdList]: Query result for traits by sample ID and trait ID", traitSimpleList);
    }

    /**
     * Retrieves a list of all samples.
     *
     * @return Result object containing a list of Sample objects.
     */
    @Operation(summary = "Retrieve all samples", description = "Retrieves a list of all samples.")
    @GetMapping("/sample/list")
    public Result<List<Sample>> listSample() {
        List<Sample> sampleList = analysisService.listSample();
        return ResultUtil.success("[listSample]: Query result for samples", sampleList);
    }

    /**
     * Retrieves heatmap data for a specific sample and method, based on a list of trait ID.
     *
     * @param sampleId           The ID of the sample.
     * @param method             The method used for generating the heatmap.
     * @param strategy           The strategy used for generating the heatmap.
     * @param sampleTraitHeatmap The request body containing a list of trait ID.
     * @return Result object containing CanvasXpressHeatMapData.
     * @throws IOException If an I/O error occurs.
     */
    @Operation(
            summary = "Retrieve sample traits heatmap data",
            description = "Retrieves heatmap data for a specific sample and method, based on a list of trait ID.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "ID of the sample", required = true, example = SAMPLE_EXAMPLE),
                    @Parameter(name = "method", in = ParameterIn.PATH, description = "Method used for generating the heatmap", required = true, example = METHOD_EXAMPLE),
                    @Parameter(name = "strategy", in = ParameterIn.PATH, description = "Strategy used for generating the heatmap", required = true, example = STRATEGY_EXAMPLE)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body containing a list of trait ID", required = true,
                    content = @Content(schema = @Schema(implementation = SampleTraitHeatmap.class))
            )
    )
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
    @Operation(
            summary = "Retrieve analysis results by genes",
            description = "Retrieves analysis results based on a list of genes.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body containing gene-related data", required = true,
                    content = @Content(schema = @Schema(implementation = AnalysisGeneVO.class))
            )
    )
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
    @Operation(
            summary = "Retrieve analysis results by transcription factors",
            description = "Retrieves analysis results based on a list of transcription factors (TFs).",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body containing TF-related data", required = true,
                    content = @Content(schema = @Schema(implementation = AnalysisTfVO.class))
            )
    )
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
    @Operation(
            summary = "Retrieve MAGMA annotation data",
            description = "Retrieves MAGMA annotation data for a specific trait, genome, and gene.",
            parameters = {
                    @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "ID of the trait", required = true, example = TRAIT_EXAMPLE),
                    @Parameter(name = "genome", in = ParameterIn.PATH, description = "Type of the genome", required = true, example = GENOME_EXAMPLE),
                    @Parameter(name = "gene", in = ParameterIn.PATH, description = "Name of the gene", required = true, example = GENE_EXAMPLE)
            }
    )
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
    @Operation(
            summary = "Retrieve gene regulation graph data",
            description = "Retrieves gene regulation graph data.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body containing graph-related data", required = true,
                    content = @Content(schema = @Schema(implementation = RegulationGraphVO.class))
            )
    )
    @PostMapping("/gene/regulation/graph")
    public Result<EchartsGraphData> getGeneGraphData(@RequestBody RegulationGraphVO regulationGraphVO) {
        checkTraitId(regulationGraphVO.getTraitId());
        checkSampleId(regulationGraphVO.getSampleId());
        checkMetadata(regulationGraphVO.getMetadata());
        EchartsGraphData echartsGraphData = analysisService.getGeneGraphData(regulationGraphVO);
        return ResultUtil.success("[getGeneGraphData]: Query result for gene regulation graph data", echartsGraphData);
    }

    /**
     * Retrieves transcription factor (TF) regulation graph data.
     *
     * @param regulationGraphVO The request body containing graph-related data.
     * @return Result object containing EchartsGraphData.
     */
    @Operation(
            summary = "Retrieve TF regulation graph data",
            description = "Retrieves transcription factor (TF) regulation graph data.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body containing graph-related data", required = true,
                    content = @Content(schema = @Schema(implementation = RegulationGraphVO.class))
            )
    )
    @PostMapping("/tf/regulation/graph")
    public Result<EchartsGraphData> getTfGraphData(@RequestBody RegulationGraphVO regulationGraphVO) {
        checkTraitId(regulationGraphVO.getTraitId());
        checkSampleId(regulationGraphVO.getSampleId());
        EchartsGraphData echartsGraphData = analysisService.getTfGraphData(regulationGraphVO);
        return ResultUtil.success("[getTfGraphData]: Query result for TF regulation graph data", echartsGraphData);
    }

    @PostMapping("/tf_gene/regulation/graph")
    public Result<EchartsGraphData> getTfGeneGraphData(@RequestBody RegulationGraphVO regulationGraphVO) {
        checkTraitId(regulationGraphVO.getTraitId());
        checkSampleId(regulationGraphVO.getSampleId());
        EchartsGraphData echartsGraphData = analysisService.getTfGeneGraphData(regulationGraphVO);
        return ResultUtil.success("[getTfGeneGraphData]: Query result for comprehensive regulation graph data", echartsGraphData);
    }

    /**
     * Retrieves gene enrichment data based on the provided criteria.
     *
     * @param geneEnrichmentVO The request body containing gene enrichment criteria.
     * @return Result object containing GeneEnrichmentResultVO.
     */
    @Operation(
            summary = "Retrieve gene enrichment data",
            description = "Retrieves gene enrichment data based on the provided criteria.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body containing gene enrichment criteria", required = true,
                    content = @Content(schema = @Schema(implementation = GeneEnrichmentVO.class))
            )
    )
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
    @Operation(
            summary = "Retrieve samples by gene",
            description = "Retrieves a list of samples associated with a specific gene.",
            parameters = {
                    @Parameter(name = "gene", in = ParameterIn.PATH, description = "Name of the gene", required = true, example = GENE_EXAMPLE)
            }
    )
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
    @Operation(
            summary = "Retrieve samples by transcription factor",
            description = "Retrieves a list of samples associated with a specific transcription factor (TF).",
            parameters = {
                    @Parameter(name = "tf", in = ParameterIn.PATH, description = "Name of the transcription factor", required = true, example = TF_EXAMPLE)
            }
    )
    @GetMapping("/sample/tf/{tf}")
    public Result<List<Sample>> listSampleDataByTf(@PathVariable String tf) {
        List<Sample> sampleList = analysisService.listSampleDataByTf(tf);
        return ResultUtil.success("[listSampleDataByTf]: Query result for samples by TF", sampleList);
    }

}
