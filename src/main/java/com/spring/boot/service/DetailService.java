package com.spring.boot.service;

import com.spring.boot.pojo.*;
import com.spring.boot.pojo.vo.DifferenceElementHeatmapVO;
import com.spring.boot.pojo.vo.SampleTraitInfo;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.vo.FieldNumber;
import com.spring.boot.util.model.vo.canvasXpress.CanvasXpressHeatMapData;
import com.spring.boot.util.model.vo.echarts.EchartsPieData;
import com.spring.boot.util.model.vo.plotly.PlotlyClusterData;
import com.spring.boot.util.util.result.Page;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DetailService {

    Sample getSampleData(String sampleId);

    Source getTraitSourceData(String sourceId);

    Trait getTraitData(String traitId, String method);

    List<SampleCellType> listSampleCellTypeData(String sampleId);

    EchartsPieData<String, String> getCellTypeCount(String sampleId, String metadata);

    EchartsPieData<String, String> getTraitChrCountData(String traitId, String genome, String method);

    SampleTraitInfo<? extends SampleEnrichSampleId> listTraitBySampleId(String sampleId, String method, String fineMappingMethod);

    SampleTraitInfo<? extends TraitEnrich> listSampleInfoByTraitId(String traitId, String method, String fineMappingMethod);

    PlotlyClusterData<Double, Double> listClusterCoordinate(String sampleId, Double cellRate, String metadata);

    PlotlyClusterData<Double, Double> listTraitClusterCoordinate(String sampleId, String traitId, String method, Double cellRate, String metadata, String fineMappingMethod) throws IOException;

    PageResult<VariantInfo> listTraitInfoData(String traitId, String genome, String method, Page page);

    PageResult<? extends DifferenceGene> listDifferenceGeneBySampleId(String sampleId, String metadata, String cellType, Page page);

    CanvasXpressHeatMapData<Double> getDifferenceGeneHeatmapBySampleId(DifferenceElementHeatmapVO differenceElementHeatmapVO);

    List<? extends DifferenceTf> listDifferenceTfBySampleId(String sampleId, String cellType);

    CanvasXpressHeatMapData<Double> getDifferenceTfHeatmapBySampleId(DifferenceElementHeatmapVO differenceElementHeatmapVO);

    List<Magma> listMagmaGeneByTraitId(String traitId, String genome);

    List<CiceroSampleTraitGene> listCiceroTraitGeneBySampleIdAndTraitId(String sampleId, String traitId);

    Map<String, Object> getCiceroAndMagmaOverlapGene(String sampleId, String traitId, String genome);

    List<Homer> listHomerTfByTraitId(String traitId, String genome);

    List<FieldNumber> listSampleCellTypeTimeSexDrug(String sampleId, String metadata);

    List<ChromVarDifferenceTf> listChromvarDifferenceTfBySampleId(String sampleId, String cellType);

    PageResult<GimmeSampleTraitTf> listGimmeTfByTraitId(String sampleId, String traitId, Page page);

    PageResult<Mpra> listMpraByTraitId(String traitId, String genome, Page page);

    PageResult<Eqtl> listEqtlByTraitId(String traitId, String genome, String chr, Page page);

    PageResult<Interaction> listInteractionByTraitId(String traitId, String genome, Page page);

    List<TrsDistributionScore> listKlScoreDataByTraitId(String traitId);

    List<TrsDistributionScore> listKlScoreDataBySampleId(String sampleId);

    Double getKlScoreData(String sampleId, String traitId);
}
