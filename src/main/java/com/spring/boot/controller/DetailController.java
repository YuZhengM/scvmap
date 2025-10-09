package com.spring.boot.controller;

import com.spring.boot.pojo.*;
import com.spring.boot.pojo.vo.DifferenceElementHeatmapVO;
import com.spring.boot.pojo.vo.SampleTraitInfo;
import com.spring.boot.service.DetailService;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.Result;
import com.spring.boot.util.model.vo.FieldNumber;
import com.spring.boot.util.model.vo.canvasXpress.CanvasXpressHeatMapData;
import com.spring.boot.util.model.vo.echarts.EchartsPieData;
import com.spring.boot.util.model.vo.plotly.PlotlyClusterData;
import com.spring.boot.util.util.result.Page;
import com.spring.boot.util.util.result.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.spring.boot.util.constant.ApplicationConstant.*;
import static com.spring.boot.util.util.ApplicationUtil.*;

/**
 * Controller class for handling detailed data requests.
 *
 * @author Zhengmin Yu
 */
@RequestMapping("/detail")
@CrossOrigin
@RestController
@Tag(name = "Detail-API", description = "Controller for handling detailed data requests")
public class DetailController {

    private DetailService detailService;

    /**
     * Default constructor for DetailController.
     */
    public DetailController() {
    }

    /**
     * Constructor with DetailService dependency injection.
     *
     * @param detailService the service to be injected
     */
    @Autowired
    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    /**
     * Retrieves sample data by sample ID.
     *
     * @param sampleId the ID of the sample
     * @return Result containing the sample data
     */
    @Operation(
            summary = "Retrieve sample data by sample ID",
            description = "Retrieves sample data based on the provided sample ID.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "The ID of the sample to search for.", required = true, example = SAMPLE_EXAMPLE)
            }
    )
    @GetMapping("/sample/{sample_id}")
    public Result<Sample> getSampleData(@PathVariable("sample_id") String sampleId) {
        checkSampleId(sampleId);
        Sample sample = detailService.getSampleData(sampleId);
        return ResultUtil.success("[getSampleData]: Sample select success", sample);
    }

    /**
     * Retrieves trait data by trait ID.
     *
     * @param traitId the ID of the trait
     * @return Result containing the trait data
     */
    @Operation(
            summary = "Retrieve trait data by trait ID",
            description = "Retrieves trait data based on the provided trait ID.",
            parameters = {
                    @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for.", required = true, example = TRAIT_EXAMPLE)
            }
    )
    @GetMapping("/trait/{trait_id}/{method}")
    public Result<Trait> getTraitData(@PathVariable("trait_id") String traitId, @PathVariable("method") String method) {
        checkTraitId(traitId);
        checkFineMappingMethod(method);
        Trait trait = detailService.getTraitData(traitId, method);
        return ResultUtil.success("[getTraitData]: Trait select success", trait);
    }

    /**
     * Lists trait information data by trait ID and genome.
     *
     * @param traitId the ID of the trait
     * @param genome  the genome identifier
     * @param page    page
     * @return Result containing a list of VariantInfo
     */
    @Operation(
            summary = "List trait information data by trait ID and genome",
            description = "Lists trait information data based on the provided trait ID, genome, and pagination information.",
            parameters = {
                    @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for.", required = true, example = TRAIT_EXAMPLE),
                    @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome identifier.", required = true, example = GENOME_EXAMPLE)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Pagination information for the search.", required = true
            )
    )
    @PostMapping("/trait_info/{trait_id}/{genome}/{method}")
    public Result<PageResult<VariantInfo>> listTraitInfoData(@PathVariable("trait_id") String traitId,
                                                             @PathVariable("genome") String genome,
                                                             @PathVariable("method") String method,
                                                             @RequestBody Page page) {
        checkTraitId(traitId);
        checkGenome(genome);
        checkFineMappingMethod(method);
        PageResult<VariantInfo> variantInfo = detailService.listTraitInfoData(traitId, genome, method, page);
        return ResultUtil.success("[listTraitInfoData]: Trait select success", variantInfo);
    }

    /**
     * Retrieves sample cell type data by sample ID.
     *
     * @param sampleId the ID of the sample
     * @return Result containing a list of SampleCellType
     */
    @Operation(
            summary = "Retrieve sample cell type data by sample ID",
            description = "Retrieves sample cell type data based on the provided sample ID.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "The ID of the sample to search for.", required = true, example = SAMPLE_EXAMPLE)
            }
    )
    @GetMapping("/sample_cell_type/{sample_id}")
    public Result<List<SampleCellType>> sampleCellTypeData(@PathVariable("sample_id") String sampleId) {
        checkSampleId(sampleId);
        List<SampleCellType> sampleCellTypeList = detailService.listSampleCellTypeData(sampleId);
        return ResultUtil.success("[sampleCellTypeData]: Sample select success", sampleCellTypeList);
    }

    /**
     * Retrieves the count of cell types by sample ID.
     *
     * @param sampleId the ID of the sample
     * @return Result containing EchartsPieData for cell type count
     */
    @Operation(
            summary = "Retrieve the count of cell types by sample ID",
            description = "Retrieves the count of cell types based on the provided sample ID.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "The ID of the sample to search for.", required = true, example = SAMPLE_EXAMPLE)
            }
    )
    @GetMapping("/cell_type/count/{sample_id}/{metadata}")
    public Result<EchartsPieData<String, String>> getCellTypeCount(@PathVariable("sample_id") String sampleId,
                                                                   @PathVariable("metadata") String metadata) {
        checkSampleId(sampleId);
        checkMetadata(metadata);
        EchartsPieData<String, String> echartsPieData = detailService.getCellTypeCount(sampleId, metadata);
        return ResultUtil.success("[getCellTypeCount]: Query result", echartsPieData);
    }

    /**
     * Lists sample cell type data by sample ID.
     *
     * @param sampleId the ID of the sample
     * @return Result containing a list of SampleCellType
     */
    @Operation(
            summary = "List sample cell type data by sample ID",
            description = "Lists sample cell type data based on the provided sample ID.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "The ID of the sample to search for.", required = true, example = SAMPLE_EXAMPLE)
            }
    )
    @GetMapping("/cell_type/{sample_id}/{metadata}")
    public Result<List<FieldNumber>> listSampleCellTypeTimeSexDrug(@PathVariable("sample_id") String sampleId, @PathVariable("metadata") String metadata) {
        checkSampleId(sampleId);
        checkMetadata(metadata);
        List<FieldNumber> fieldNumberList = detailService.listSampleCellTypeTimeSexDrug(sampleId, metadata);
        return ResultUtil.success("[listSampleCellType]: Query result", fieldNumberList);
    }

    /**
     * Retrieves trait chromosome count data by trait ID and genome.
     *
     * @param traitId the ID of the trait
     * @param genome  the genome identifier
     * @return Result containing EchartsPieData for trait chromosome count
     */
    @Operation(
            summary = "Retrieve trait chromosome count data by trait ID and genome",
            description = "Retrieves trait chromosome count data based on the provided trait ID and genome.",
            parameters = {
                    @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for.", required = true, example = TRAIT_EXAMPLE),
                    @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome identifier.", required = true, example = GENOME_EXAMPLE)
            }
    )
    @GetMapping("/trait/chr/count/{trait_id}/{genome}/{method}")
    public Result<EchartsPieData<String, String>> getTraitChrCountData(@PathVariable("trait_id") String traitId,
                                                                       @PathVariable("genome") String genome,
                                                                       @PathVariable("method") String method) {
        checkTraitId(traitId);
        checkGenome(genome);
        checkFineMappingMethod(method);
        EchartsPieData<String, String> echartsPieData = detailService.getTraitChrCountData(traitId, genome, method);
        return ResultUtil.success("[getTraitChrCountData]: Trait select success", echartsPieData);
    }

    /**
     * Lists trait information by sample ID and method.
     *
     * @param sampleId the ID of the sample
     * @param method   the method used for querying
     * @return Result containing SampleTraitInfo
     */
    @Operation(
            summary = "List trait information by sample ID and method",
            description = "Lists trait information based on the provided sample ID and query method.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "The ID of the sample to search for.", required = true, example = SAMPLE_EXAMPLE),
                    @Parameter(name = "method", in = ParameterIn.PATH, description = "The method used for querying.", required = true, example = METHOD_EXAMPLE)
            }
    )
    @GetMapping("/trait/overlap/{sample_id}/{method}/{fine_mapping_method}")
    public Result<SampleTraitInfo<? extends SampleEnrichSampleId>> listTraitBySampleId(@PathVariable("sample_id") String sampleId,
                                                                                       @PathVariable("method") String method,
                                                                                       @PathVariable("fine_mapping_method") String fineMappingMethod) {
        checkSampleId(sampleId);
        checkMethod(method);
        SampleTraitInfo<? extends SampleEnrichSampleId> sampleTraitInfo = detailService.listTraitBySampleId(sampleId, method, fineMappingMethod);
        return ResultUtil.success("[listTraitBySampleId]: Query result", sampleTraitInfo);
    }

    /**
     * Lists sample information by trait ID and method.
     *
     * @param traitId the ID of the trait
     * @param method  the method used for querying
     * @return Result containing SampleTraitInfo
     */
    @Operation(
            summary = "List sample information by trait ID and method",
            description = "Lists sample information based on the provided trait ID and query method.",
            parameters = {
                    @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for.", required = true, example = TRAIT_EXAMPLE),
                    @Parameter(name = "method", in = ParameterIn.PATH, description = "The method used for querying.", required = true, example = METHOD_EXAMPLE)
            }
    )
    @GetMapping("/sample/overlap/{trait_id}/{method}/{fine_mapping_method}")
    public Result<SampleTraitInfo<? extends TraitEnrich>> listSampleInfoByTraitId(@PathVariable("trait_id") String traitId,
                                                                                  @PathVariable("method") String method,
                                                                                  @PathVariable("fine_mapping_method") String fineMappingMethod) {
        checkTraitId(traitId);
        checkMethod(method);
        checkFineMappingMethod(fineMappingMethod);
        SampleTraitInfo<? extends TraitEnrich> sampleTraitInfo = detailService.listSampleInfoByTraitId(traitId, method, fineMappingMethod);
        return ResultUtil.success("[listSampleInfoByTraitId]: Query result", sampleTraitInfo);
    }

    /**
     * Lists cluster coordinates by sample ID and cell rate.
     *
     * @param sampleId the ID of the sample
     * @param cellRate the cell rate for clustering
     * @return Result containing PlotlyClusterData for cluster coordinates
     */
    @Operation(
            summary = "List cluster coordinates by sample ID and cell rate",
            description = "Lists cluster coordinates based on the provided sample ID and cell rate.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "The ID of the sample to search for.", required = true, example = SAMPLE_EXAMPLE),
                    @Parameter(name = "cell_rate", in = ParameterIn.PATH, description = "The cell rate for clustering.", required = true, example = CELL_RATE_EXAMPLE)
            }
    )
    @GetMapping("/cluster_coordinate/{sample_id}/{cell_rate}/{metadata}")
    public Result<PlotlyClusterData<Double, Double>> listClusterCoordinate(@PathVariable("sample_id") String sampleId,
                                                                           @PathVariable("cell_rate") Double cellRate,
                                                                           @PathVariable("metadata") String metadata) {
        checkSampleId(sampleId);
        checkMetadata(metadata);
        PlotlyClusterData<Double, Double> plotlyClusterData = detailService.listClusterCoordinate(sampleId, cellRate, metadata);
        return ResultUtil.success("[listClusterCoordinate]: Query result", plotlyClusterData);
    }

    /**
     * Lists trait cluster coordinates by sample ID, method, trait ID, and cell rate.
     *
     * @param sampleId the ID of the sample
     * @param method   the method used for clustering
     * @param traitId  the ID of the trait
     * @param cellRate the cell rate for clustering
     * @return Result containing PlotlyClusterData for trait cluster coordinates
     * @throws IOException if an I/O error occurs
     */
    @Operation(
            summary = "List trait cluster coordinates by sample ID, method, trait ID, and cell rate",
            description = "Lists trait cluster coordinates based on the provided sample ID, method, trait ID, and cell rate.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "The ID of the sample to search for.", required = true, example = SAMPLE_EXAMPLE),
                    @Parameter(name = "method", in = ParameterIn.PATH, description = "The method used for clustering.", required = true, example = METHOD_EXAMPLE),
                    @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for.", required = true, example = TRAIT_EXAMPLE),
                    @Parameter(name = "cell_rate", in = ParameterIn.PATH, description = "The cell rate for clustering.", required = true, example = CELL_RATE_EXAMPLE)
            }
    )
    @GetMapping("/cluster_coordinate/{sample_id}/{method}/{trait_id}/{cell_rate}/{metadata}/{fineMappingMethod}")
    public Result<PlotlyClusterData<Double, Double>> listTraitClusterCoordinate(@PathVariable("sample_id") String sampleId,
                                                                                @PathVariable("method") String method,
                                                                                @PathVariable("trait_id") String traitId,
                                                                                @PathVariable("cell_rate") Double cellRate,
                                                                                @PathVariable("metadata") String metadata,
                                                                                @PathVariable("fineMappingMethod") String fineMappingMethod) throws IOException {
        checkSampleId(sampleId);
        checkMethod(method);
        checkTraitId(traitId);
        checkMetadata(metadata);
        checkFineMappingMethod(fineMappingMethod);
        PlotlyClusterData<Double, Double> plotlyClusterData = detailService.listTraitClusterCoordinate(sampleId, traitId, method, cellRate, metadata, fineMappingMethod);
        return ResultUtil.success("[listTraitClusterCoordinate]: Query result", plotlyClusterData);
    }

    /**
     * Lists difference genes by sample ID and cell type.
     *
     * @param sampleId the ID of the sample
     * @param cellType Cell type
     * @return Result containing a list of DifferenceGene
     */
    @Operation(
            summary = "List difference genes by sample ID and cell type",
            description = "Lists difference genes based on the provided sample ID and cell type with pagination information.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "The ID of the sample to search for.", required = true, example = SAMPLE_EXAMPLE),
                    @Parameter(name = "cell_type", in = ParameterIn.PATH, description = "Cell type.", required = true, example = CELL_TYPE_EXAMPLE)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Pagination information for the search.", required = true
            )
    )
    @PostMapping("/difference_gene/{sample_id}/{metadata}/{cell_type}")
    public Result<PageResult<? extends DifferenceGene>> listDifferenceGeneBySampleId(@PathVariable("sample_id") String sampleId,
                                                                                     @PathVariable("metadata") String metadata,
                                                                                     @PathVariable("cell_type") String cellType,
                                                                                     @RequestBody Page page) {
        checkSampleId(sampleId);
        checkMetadata(metadata);
        PageResult<? extends DifferenceGene> differenceGeneList = detailService.listDifferenceGeneBySampleId(sampleId, metadata, cellType, page);
        return ResultUtil.success("[listDifferenceGeneBySampleId]: Query result", differenceGeneList);
    }

    @Operation(
            summary = "Get difference gene heatmap by sample ID",
            description = "Retrieves the difference gene heatmap data based on the provided sample ID and other parameters.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "VO object containing sample ID and other parameters for heatmap data retrieval.", required = true
            )
    )
    @PostMapping("/difference_gene/heatmap")
    public Result<CanvasXpressHeatMapData<Double>> getDifferenceGeneHeatmapBySampleId(@RequestBody DifferenceElementHeatmapVO differenceElementHeatmapVO) {
        checkSampleId(differenceElementHeatmapVO.getSampleId());
        CanvasXpressHeatMapData<Double> canvasXpressHeatMapData = detailService.getDifferenceGeneHeatmapBySampleId(differenceElementHeatmapVO);
        return ResultUtil.success("[getDifferenceGeneHeatmapBySampleId]: Query result for Differential gene heatmap", canvasXpressHeatMapData);
    }

    /**
     * Lists difference transcription factors by sample ID and cell type.
     *
     * @param sampleId the ID of the sample
     * @param cellType Cell type
     * @return Result containing a list of DifferenceTf
     */
    @Operation(
            summary = "List difference transcription factors by sample ID and cell type",
            description = "Lists difference transcription factors based on the provided sample ID and cell type.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "The ID of the sample to search for.", required = true, example = SAMPLE_EXAMPLE),
                    @Parameter(name = "cell_type", in = ParameterIn.PATH, description = "Cell type.", required = true, example = CELL_TYPE_EXAMPLE)
            }
    )
    @GetMapping("/difference_tf/{sample_id}/{cell_type}")
    public Result<List<? extends DifferenceTf>> listDifferenceTfBySampleId(@PathVariable("sample_id") String sampleId, @PathVariable("cell_type") String cellType) {
        checkSampleId(sampleId);
        List<? extends DifferenceTf> differenceTfList = detailService.listDifferenceTfBySampleId(sampleId, cellType);
        return ResultUtil.success("[listDifferenceTfBySampleId]: Query result", differenceTfList);
    }

    @GetMapping("/chromvar_difference_tf/{sample_id}/{cell_type}")
    public Result<List<ChromVarDifferenceTf>> listChromvarDifferenceTfBySampleId(@PathVariable("sample_id") String sampleId, @PathVariable("cell_type") String cellType) {
        checkSampleId(sampleId);
        List<ChromVarDifferenceTf> chromVarDifferenceTfList = detailService.listChromvarDifferenceTfBySampleId(sampleId, cellType);
        return ResultUtil.success("[listChromvarDifferenceTfBySampleId]: Query result", chromVarDifferenceTfList);
    }

    @Operation(
            summary = "Get difference TF heatmap by sample ID",
            description = "Retrieves the difference TF heatmap data based on the provided sample ID and other parameters.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "VO object containing sample ID and other parameters for heatmap data retrieval.", required = true
            )
    )
    @PostMapping("/difference_tf/heatmap")
    public Result<CanvasXpressHeatMapData<Double>> getDifferenceTfHeatmapBySampleId(@RequestBody DifferenceElementHeatmapVO differenceElementHeatmapVO) {
        checkSampleId(differenceElementHeatmapVO.getSampleId());
        CanvasXpressHeatMapData<Double> canvasXpressHeatMapData = detailService.getDifferenceTfHeatmapBySampleId(differenceElementHeatmapVO);
        return ResultUtil.success("[getDifferenceTfHeatmapBySampleId]: Query result for Differential TF heatmap", canvasXpressHeatMapData);
    }

    /**
     * Controller method to retrieve a list of Magma genes based on trait ID and genome.
     *
     * @param traitId The ID of the trait.
     * @param genome  The genome identifier.
     * @return A Result object containing a list of Magma genes.
     */
    @Operation(
            summary = "Retrieve a list of Magma genes by trait ID and genome",
            description = "Retrieves a list of Magma genes based on the provided trait ID and genome.",
            parameters = {
                    @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for.", required = true, example = TRAIT_EXAMPLE),
                    @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome identifier.", required = true, example = GENOME_EXAMPLE)
            }
    )
    @GetMapping("/magma_gene/{trait_id}/{genome}")
    public Result<List<Magma>> listMagmaGeneByTraitId(@PathVariable("trait_id") String traitId, @PathVariable("genome") String genome) {
        checkTraitId(traitId);
        checkGenome(genome);
        List<Magma> magmaList = detailService.listMagmaGeneByTraitId(traitId, genome);
        return ResultUtil.success("Magma genes retrieved successfully", magmaList);
    }

    @GetMapping("/cicero_trait_gene/{sample_id}/{trait_id}")
    public Result<List<CiceroSampleTraitGene>> listCiceroTraitGeneBySampleIdAndTraitId(@PathVariable("sample_id") String sampleId, @PathVariable("trait_id") String traitId) {
        checkSampleId(sampleId);
        checkTraitId(traitId);
        List<CiceroSampleTraitGene> ciceroSampleTraitGeneList = detailService.listCiceroTraitGeneBySampleIdAndTraitId(sampleId, traitId);
        return ResultUtil.success("Cicero trait-gene pairs retrieved successfully", ciceroSampleTraitGeneList);
    }

    @GetMapping("/cicero_magma_gene/overlap/{sample_id}/{trait_id}/{genome}")
    public Result<Map<String, Object>> getCiceroAndMagmaOverlapGene(@PathVariable("sample_id") String sampleId,
                                                                    @PathVariable("trait_id") String traitId,
                                                                    @PathVariable("genome") String genome) {
        checkSampleId(sampleId);
        checkTraitId(traitId);
        checkGenome(genome);
        Map<String, Object> overlapGeneList = detailService.getCiceroAndMagmaOverlapGene(sampleId, traitId, genome);
        return ResultUtil.success("Cicero (ATAC-based) and MAGMA (LD) identify the overlap of trait-relevant genes", overlapGeneList);
    }

    /**
     * Controller method to retrieve a list of Homer transcription factors based on trait ID and genome.
     *
     * @param traitId The ID of the trait.
     * @param genome  The genome identifier.
     * @return A Result object containing a list of Homer transcription factors.
     */
    @Operation(
            summary = "Retrieve a list of Homer transcription factors by trait ID and genome",
            description = "Retrieves a list of Homer transcription factors based on the provided trait ID and genome.",
            parameters = {
                    @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for.", required = true, example = TRAIT_EXAMPLE),
                    @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome identifier.", required = true, example = GENOME_EXAMPLE)
            }
    )
    @GetMapping("/homer_tf/{trait_id}/{genome}")
    public Result<List<Homer>> listHomerTfByTraitId(@PathVariable("trait_id") String traitId, @PathVariable("genome") String genome) {
        checkTraitId(traitId);
        checkGenome(genome);
        List<Homer> homerList = detailService.listHomerTfByTraitId(traitId, genome);
        return ResultUtil.success("Homer transcription factors retrieved successfully", homerList);
    }

    /**
     * Controller method to retrieve a list of GimmeMotifs transcription factors based on sample ID and trait ID.
     *
     * @param sampleId The ID of the sample.
     * @param traitId  The ID of the trait.
     * @param page     The pagination information.
     * @return A Result object containing a list of GimmeMotifs transcription factors.
     */
    @Operation(
            summary = "Retrieve a list of GimmeMotifs transcription factors by sample ID and trait ID",
            description = "Retrieves a list of GimmeMotifs transcription factors based on the provided sample ID and trait ID.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "The ID of the sample to search for.", required = true, example = SAMPLE_EXAMPLE),
                    @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for.", required = true, example = TRAIT_EXAMPLE)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Pagination information for the search.", required = true
            )
    )
    @PostMapping("/gimme_tf/{sample_id}/{trait_id}")
    public Result<PageResult<GimmeSampleTraitTf>> listGimmeTfByTraitId(@PathVariable("sample_id") String sampleId,
                                                                       @PathVariable("trait_id") String traitId,
                                                                       @RequestBody Page page) {
        checkSampleId(sampleId);
        checkTraitId(traitId);
        PageResult<GimmeSampleTraitTf> gimmeSampleTraitTfList = detailService.listGimmeTfByTraitId(sampleId, traitId, page);
        return ResultUtil.success("GimmeMotifs transcription factors retrieved successfully", gimmeSampleTraitTfList);
    }

    /**
     * Controller method to retrieve a list of eQTLs based on trait ID, genome, and chromosome.
     *
     * @param traitId The ID of the trait.
     * @param genome  The genome identifier.
     * @param chr     The chromosome identifier.
     * @param page    The pagination information.
     * @return A Result object containing a list of eQTLs.
     */
    @Operation(
            summary = "Retrieve a list of eQTLs by trait ID, genome, and chromosome",
            description = "Retrieves a list of eQTLs based on the provided trait ID, genome, and chromosome.",
            parameters = {
                    @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for.", required = true, example = TRAIT_EXAMPLE),
                    @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome identifier.", required = true, example = GENOME_EXAMPLE),
                    @Parameter(name = "chr", in = ParameterIn.PATH, description = "The chromosome identifier.", required = true, example = "chr1")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Pagination information for the search.", required = true
            )
    )
    @PostMapping("/eqtl/{trait_id}/{genome}/{chr}")
    public Result<PageResult<Eqtl>> listEqtlByTraitId(@PathVariable("trait_id") String traitId,
                                                      @PathVariable("genome") String genome,
                                                      @PathVariable("chr") String chr,
                                                      @RequestBody Page page) {
        checkTraitId(traitId);
        checkGenome(genome);
        PageResult<Eqtl> eqtlList = detailService.listEqtlByTraitId(traitId, genome, chr, page);
        return ResultUtil.success("eQTL information", eqtlList);
    }

    /**
     * Controller method to retrieve a list of MPRA data based on trait ID and genome.
     *
     * @param traitId The ID of the trait.
     * @param genome  The genome identifier.
     * @param page    The pagination information.
     * @return A Result object containing a list of MPRA data.
     */
    @Operation(
            summary = "Retrieve a list of MPRA data by trait ID and genome",
            description = "Retrieves a list of MPRA data based on the provided trait ID and genome.",
            parameters = {
                    @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for.", required = true, example = TRAIT_EXAMPLE),
                    @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome identifier.", required = true, example = GENOME_EXAMPLE)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Pagination information for the search.", required = true
            )
    )
    @PostMapping("/mpra/{trait_id}/{genome}")
    public Result<PageResult<Mpra>> listMpraByTraitId(@PathVariable("trait_id") String traitId,
                                                      @PathVariable("genome") String genome,
                                                      @RequestBody Page page) {
        checkTraitId(traitId);
        checkGenome(genome);
        PageResult<Mpra> mpraList = detailService.listMpraByTraitId(traitId, genome, page);
        return ResultUtil.success("MPRA information", mpraList);
    }

    /**
     * Controller method to retrieve a list of interaction data based on trait ID and genome.
     *
     * @param traitId The ID of the trait.
     * @param genome  The genome identifier.
     * @param page    The pagination information.
     * @return A Result object containing a list of interaction data.
     */
    @Operation(
            summary = "Retrieve a list of interaction data by trait ID and genome",
            description = "Retrieves a list of interaction data based on the provided trait ID and genome.",
            parameters = {
                    @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for.", required = true, example = TRAIT_EXAMPLE),
                    @Parameter(name = "genome", in = ParameterIn.PATH, description = "The genome identifier.", required = true, example = GENOME_EXAMPLE)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Pagination information for the search.", required = true
            )
    )
    @PostMapping("/interaction/{trait_id}/{genome}")
    public Result<PageResult<Interaction>> listInteractionByTraitId(@PathVariable("trait_id") String traitId,
                                                                    @PathVariable("genome") String genome,
                                                                    @RequestBody Page page) {
        checkTraitId(traitId);
        checkGenome(genome);
        PageResult<Interaction> interactionList = detailService.listInteractionByTraitId(traitId, genome, page);
        return ResultUtil.success("Interaction information", interactionList);
    }

    /**
     * Controller method to retrieve a list of KL score data based on trait ID.
     *
     * @param traitId The ID of the trait.
     * @return A Result object containing a list of KL score data.
     */
    @Operation(
            summary = "Retrieve a list of KL score data by trait ID",
            description = "Retrieves a list of KL score data based on the provided trait ID.",
            parameters = {
                    @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for.", required = true, example = TRAIT_EXAMPLE)
            }
    )
    @GetMapping("/kl_score/trait/{trait_id}")
    public Result<List<TrsDistributionScore>> listKlScoreDataByTraitId(@PathVariable("trait_id") String traitId) {
        checkTraitId(traitId);
        List<TrsDistributionScore> trsDistributionScoreList = detailService.listKlScoreDataByTraitId(traitId);
        return ResultUtil.success("listKlScoreDataByTraitId", trsDistributionScoreList);
    }

    /**
     * Controller method to retrieve a list of KL score data based on sample ID.
     *
     * @param sampleId The ID of the sample.
     * @return A Result object containing a list of KL score data.
     */
    @Operation(
            summary = "Retrieve a list of KL score data by sample ID",
            description = "Retrieves a list of KL score data based on the provided sample ID.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "The ID of the sample to search for.", required = true, example = SAMPLE_EXAMPLE)
            }
    )
    @GetMapping("/kl_score/sample/{sample_id}")
    public Result<List<TrsDistributionScore>> listKlScoreDataBySampleId(@PathVariable("sample_id") String sampleId) {
        checkSampleId(sampleId);
        List<TrsDistributionScore> trsDistributionScoreList = detailService.listKlScoreDataBySampleId(sampleId);
        return ResultUtil.success("listKlScoreDataBySampleId", trsDistributionScoreList);
    }

    /**
     * Controller method to retrieve the KL score data for a specific sample and trait.
     *
     * @param sampleId The ID of the sample.
     * @param traitId  The ID of the trait.
     * @return A Result object containing the KL score data.
     */
    @Operation(
            summary = "Retrieve KL score data by sample ID and trait ID",
            description = "Retrieves the KL score data for a specific sample and trait based on the provided IDs.",
            parameters = {
                    @Parameter(name = "sample_id", in = ParameterIn.PATH, description = "The ID of the sample to search for.", required = true, example = SAMPLE_EXAMPLE),
                    @Parameter(name = "trait_id", in = ParameterIn.PATH, description = "The ID of the trait to search for.", required = true, example = TRAIT_EXAMPLE)
            }
    )
    @GetMapping("/kl_score/{sample_id}/{trait_id}")
    public Result<Double> getKlScoreData(@PathVariable("sample_id") String sampleId,
                                         @PathVariable("trait_id") String traitId) {
        checkSampleId(sampleId);
        checkTraitId(traitId);
        Double klScore = detailService.getKlScoreData(sampleId, traitId);
        return ResultUtil.success("getKlScoreData", klScore);
    }

}
