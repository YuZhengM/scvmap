package com.spring.boot.service;

import com.spring.boot.pojo.*;
import com.spring.boot.pojo.vo.DifferenceElementHeatmapVO;
import com.spring.boot.pojo.vo.SampleTraitInfo;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.vo.canvasXpress.CanvasXpressHeatMapData;
import com.spring.boot.util.model.vo.echarts.EchartsPieData;
import com.spring.boot.util.model.vo.plotly.PlotlyClusterData;
import com.spring.boot.util.util.result.Page;

import java.io.IOException;
import java.util.List;

public interface DetailService {

    Sample getSampleData(String sampleId);

    Source getTraitSourceData(String sourceId);

    Trait getTraitData(String traitId);

    List<SampleCellType> listSampleCellTypeData(String sampleId);

    EchartsPieData<String, String> getCellTypeCount(String sampleId);

    EchartsPieData<String, String> getTraitChrCountData(String traitId, String genome);

    SampleTraitInfo<SampleEnrichSampleId> listTraitBySampleId(String sampleId, String method);

    SampleTraitInfo<TraitEnrich> listSampleInfoByTraitId(String traitId, String method);

    PlotlyClusterData<Double, Double> listClusterCoordinate(String sampleId, Double cellRate);

    PlotlyClusterData<Double, Double> listTraitClusterCoordinate(String sampleId, String traitId, String method, Double cellRate) throws IOException;

    PageResult<VariantInfo> listTraitInfoData(String traitId, String genome, Page page);

    PageResult<? extends DifferenceGene> listDifferenceGeneBySampleId(String sampleId, String cellType, Page page);

    CanvasXpressHeatMapData<Double> getDifferenceGeneHeatmapBySampleId(DifferenceElementHeatmapVO differenceElementHeatmapVO);

    List<? extends DifferenceTf> listDifferenceTfBySampleId(String sampleId, String cellType);

    CanvasXpressHeatMapData<Double> getDifferenceTfHeatmapBySampleId(DifferenceElementHeatmapVO differenceElementHeatmapVO);

    List<Magma> listMagmaGeneByTraitId(String traitId, String genome);

    List<Homer> listHomerTfByTraitId(String traitId, String genome);

    List<SampleCellType> listSampleCellType(String sampleId);
}
