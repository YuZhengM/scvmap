package com.spring.boot.controller;

import com.spring.boot.pojo.*;
import com.spring.boot.pojo.vo.DifferenceElementHeatmapVO;
import com.spring.boot.pojo.vo.SampleTraitInfo;
import com.spring.boot.service.DetailService;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.Result;
import com.spring.boot.util.model.vo.canvasXpress.CanvasXpressHeatMapData;
import com.spring.boot.util.model.vo.echarts.EchartsPieData;
import com.spring.boot.util.model.vo.plotly.PlotlyClusterData;
import com.spring.boot.util.util.result.Page;
import com.spring.boot.util.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.spring.boot.util.util.ApplicationUtil.*;

/**
 * Controller class for handling detailed data requests.
 *
 * @author Zhengmin Yu
 */
@RequestMapping("/detail")
@CrossOrigin
@RestController
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
    @GetMapping("/trait/{trait_id}")
    public Result<Trait> getTraitData(@PathVariable("trait_id") String traitId) {
        checkTraitId(traitId);
        Trait trait = detailService.getTraitData(traitId);
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
    @PostMapping("/trait_info/{trait_id}/{genome}")
    public Result<PageResult<VariantInfo>> listTraitInfoData(@PathVariable("trait_id") String traitId,
                                                             @PathVariable("genome") String genome,
                                                             @RequestBody Page page) {
        checkTraitId(traitId);
        checkGenome(genome);
        PageResult<VariantInfo> variantInfo = detailService.listTraitInfoData(traitId, genome, page);
        return ResultUtil.success("[listTraitInfoData]: Trait select success", variantInfo);
    }

    /**
     * Retrieves sample cell type data by sample ID.
     *
     * @param sampleId the ID of the sample
     * @return Result containing a list of SampleCellType
     */
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
    @GetMapping("/cell_type/count/{sample_id}")
    public Result<EchartsPieData<String, String>> getCellTypeCount(@PathVariable("sample_id") String sampleId) {
        checkSampleId(sampleId);
        EchartsPieData<String, String> echartsPieData = detailService.getCellTypeCount(sampleId);
        return ResultUtil.success("[getCellTypeCount]: Query result", echartsPieData);
    }

    /**
     * Lists sample cell type data by sample ID.
     *
     * @param sampleId the ID of the sample
     * @return Result containing a list of SampleCellType
     */
    @GetMapping("/cell_type/{sample_id}")
    public Result<List<SampleCellType>> listSampleCellType(@PathVariable("sample_id") String sampleId) {
        checkSampleId(sampleId);
        List<SampleCellType> sampleCellTypeList = detailService.listSampleCellType(sampleId);
        return ResultUtil.success("[listSampleCellType]: Query result", sampleCellTypeList);
    }

    /**
     * Retrieves trait chromosome count data by trait ID and genome.
     *
     * @param traitId the ID of the trait
     * @param genome  the genome identifier
     * @return Result containing EchartsPieData for trait chromosome count
     */
    @GetMapping("/trait/chr/count/{trait_id}/{genome}")
    public Result<EchartsPieData<String, String>> getTraitChrCountData(@PathVariable("trait_id") String traitId, @PathVariable("genome") String genome) {
        checkTraitId(traitId);
        checkGenome(genome);
        EchartsPieData<String, String> echartsPieData = detailService.getTraitChrCountData(traitId, genome);
        return ResultUtil.success("[getTraitChrCountData]: Trait select success", echartsPieData);
    }

    /**
     * Lists trait information by sample ID and method.
     *
     * @param sampleId the ID of the sample
     * @param method   the method used for querying
     * @return Result containing SampleTraitInfo
     */
    @GetMapping("/trait/overlap/{sample_id}/{method}")
    public Result<SampleTraitInfo<SampleEnrichSampleId>> listTraitBySampleId(@PathVariable("sample_id") String sampleId, @PathVariable("method") String method) {
        checkSampleId(sampleId);
        checkMethod(method);
        SampleTraitInfo<SampleEnrichSampleId> sampleTraitInfo = detailService.listTraitBySampleId(sampleId, method);
        return ResultUtil.success("[listTraitBySampleId]: Query result", sampleTraitInfo);
    }

    /**
     * Lists sample information by trait ID and method.
     *
     * @param traitId the ID of the trait
     * @param method  the method used for querying
     * @return Result containing SampleTraitInfo
     */
    @GetMapping("/sample/overlap/{trait_id}/{method}")
    public Result<SampleTraitInfo<TraitEnrich>> listSampleInfoByTraitId(@PathVariable("trait_id") String traitId, @PathVariable("method") String method) {
        checkTraitId(traitId);
        checkMethod(method);
        SampleTraitInfo<TraitEnrich> sampleTraitInfo = detailService.listSampleInfoByTraitId(traitId, method);
        return ResultUtil.success("[listSampleInfoByTraitId]: Query result", sampleTraitInfo);
    }

    /**
     * Lists cluster coordinates by sample ID and cell rate.
     *
     * @param sampleId the ID of the sample
     * @param cellRate the cell rate for clustering
     * @return Result containing PlotlyClusterData for cluster coordinates
     */
    @GetMapping("/cluster_coordinate/{sample_id}/{cell_rate}")
    public Result<PlotlyClusterData<Double, Double>> listClusterCoordinate(@PathVariable("sample_id") String sampleId, @PathVariable("cell_rate") Double cellRate) {
        checkSampleId(sampleId);
        PlotlyClusterData<Double, Double> plotlyClusterData = detailService.listClusterCoordinate(sampleId, cellRate);
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
    @GetMapping("/cluster_coordinate/{sample_id}/{method}/{trait_id}/{cell_rate}")
    public Result<PlotlyClusterData<Double, Double>> listTraitClusterCoordinate(@PathVariable("sample_id") String sampleId,
                                                                                @PathVariable("method") String method,
                                                                                @PathVariable("trait_id") String traitId,
                                                                                @PathVariable("cell_rate") Double cellRate) throws IOException {
        checkSampleId(sampleId);
        checkMethod(method);
        checkTraitId(traitId);
        PlotlyClusterData<Double, Double> plotlyClusterData = detailService.listTraitClusterCoordinate(sampleId, traitId, method, cellRate);
        return ResultUtil.success("[listTraitClusterCoordinate]: Query result", plotlyClusterData);
    }

    /**
     * Lists difference genes by sample ID and cell type.
     *
     * @param sampleId the ID of the sample
     * @param cellType the type of the cell
     * @return Result containing a list of DifferenceGene
     */
    @PostMapping("/difference_gene/{sample_id}/{cell_type}")
    public Result<PageResult<? extends DifferenceGene>> listDifferenceGeneBySampleId(@PathVariable("sample_id") String sampleId,
                                                                                     @PathVariable("cell_type") String cellType,
                                                                                     @RequestBody Page page) {
        checkSampleId(sampleId);
        PageResult<? extends DifferenceGene> differenceGeneList = detailService.listDifferenceGeneBySampleId(sampleId, cellType, page);
        return ResultUtil.success("[listDifferenceGeneBySampleId]: Query result", differenceGeneList);
    }

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
     * @param cellType the type of the cell
     * @return Result containing a list of DifferenceTf
     */
    @GetMapping("/difference_tf/{sample_id}/{cell_type}")
    public Result<List<? extends DifferenceTf>> listDifferenceTfBySampleId(@PathVariable("sample_id") String sampleId, @PathVariable("cell_type") String cellType) {
        checkSampleId(sampleId);
        List<? extends DifferenceTf> differenceTfList = detailService.listDifferenceTfBySampleId(sampleId, cellType);
        return ResultUtil.success("[listDifferenceTfBySampleId]: Query result", differenceTfList);
    }

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
    @GetMapping("/magma_gene/{trait_id}/{genome}")
    public Result<List<Magma>> listMagmaGeneByTraitId(@PathVariable("trait_id") String traitId, @PathVariable("genome") String genome) {
        checkTraitId(traitId);
        checkGenome(genome);
        List<Magma> magmaList = detailService.listMagmaGeneByTraitId(traitId, genome);
        return ResultUtil.success("Magma genes retrieved successfully", magmaList);
    }

    /**
     * Controller method to retrieve a list of Homer transcription factors based on trait ID and genome.
     *
     * @param traitId The ID of the trait.
     * @param genome  The genome identifier.
     * @return A Result object containing a list of Homer transcription factors.
     */
    @GetMapping("/homer_tf/{trait_id}/{genome}")
    public Result<List<Homer>> listHomerTfByTraitId(@PathVariable("trait_id") String traitId, @PathVariable("genome") String genome) {
        checkTraitId(traitId);
        checkGenome(genome);
        List<Homer> homerList = detailService.listHomerTfByTraitId(traitId, genome);
        return ResultUtil.success("Homer transcription factors retrieved successfully", homerList);
    }

}
