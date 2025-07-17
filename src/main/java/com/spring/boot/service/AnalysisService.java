package com.spring.boot.service;

import com.spring.boot.pojo.MagmaAnno;
import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.Trait;
import com.spring.boot.pojo.TraitSample;
import com.spring.boot.pojo.vo.*;
import com.spring.boot.util.model.vo.canvasXpress.CanvasXpressHeatMapData;
import com.spring.boot.util.model.vo.echarts.EchartsGraphData;

import java.io.IOException;
import java.util.List;

/**
 * Service interface for data analysis operations.
 */
public interface AnalysisService {

    /**
     * Retrieves a list of all traits.
     *
     * @return a list of Trait objects
     */
    List<Trait> listTrait();

    /**
     * Retrieves a list of all samples.
     *
     * @return a list of Sample objects
     */
    List<Sample> listSample();

    /**
     * Generates a heatmap data for sample traits based on the given sample ID, method, and list of trait IDs.
     *
     * @param sampleId    the ID of the sample
     * @param method      the method used for generating the heatmap
     * @param strategy    the strategy used for generating the heatmap
     * @param traitIdList the list of trait IDs
     * @return a CanvasXpressHeatMapData object containing the heatmap data
     * @throws IOException if an I/O error occurs
     */
    CanvasXpressHeatMapData<Double> getSampleTraitsHeatmap(String sampleId, String method, String strategy, List<String> traitIdList) throws IOException;

    /**
     * Retrieves a list of trait samples based on the given sample ID.
     *
     * @param sampleId the ID of the sample
     * @return a list of TraitSample objects
     */
    List<TraitSample> listTraitBySampleId(String sampleId);

    /**
     * Retrieves analysis results for genes based on the provided AnalysisGeneVO.
     *
     * @param analysisGeneVO the AnalysisGeneVO object containing the parameters for the analysis
     * @return an AnalysisGeneResultVO object containing the analysis results
     */
    AnalysisGeneResultVO listDataByGenes(AnalysisGeneVO analysisGeneVO);

    /**
     * Retrieves analysis results for transcription factors based on the provided AnalysisTfVO.
     *
     * @param analysisTfVO the AnalysisTfVO object containing the parameters for the analysis
     * @return an AnalysisTfResultVO object containing the analysis results
     */
    AnalysisTfResultVO listDataByTfs(AnalysisTfVO analysisTfVO);

    /**
     * Retrieves gene graph data for regulation based on the provided RegulationGraphVO.
     *
     * @param regulationGraphVO the RegulationGraphVO object containing the parameters for the graph
     * @return an EchartsGraphData object containing the graph data
     */
    EchartsGraphData getGeneGraphData(RegulationGraphVO regulationGraphVO);

    /**
     * Retrieves transcription factor graph data for regulation based on the provided RegulationGraphVO.
     *
     * @param regulationGraphVO the RegulationGraphVO object containing the parameters for the graph
     * @return an EchartsGraphData object containing the graph data
     */
    EchartsGraphData getTfGraphData(RegulationGraphVO regulationGraphVO);

    /**
     * Retrieves a list of MagmaAnno objects containing variant information data by trait ID and gene.
     *
     * @param traitId the ID of the trait
     * @param genome  the genome version
     * @param gene    the gene symbol
     * @return a list of MagmaAnno objects
     */
    List<MagmaAnno> listMagmaVariantInfoDataByTraitIdAndGene(String traitId, String genome, String gene);

    /**
     * Retrieves a list of TraitSample objects based on the given sample ID and list of trait IDs.
     *
     * @param sampleId    the ID of the sample
     * @param traitIdList the list of trait IDs
     * @return a list of TraitSample objects
     * @throws IOException if an I/O error occurs
     */
    List<TraitSample> listTraitBySampleIdAndTraitIdList(String sampleId, List<String> traitIdList) throws IOException;

    /**
     * Retrieves gene enrichment data based on the provided GeneEnrichmentVO.
     *
     * @param geneEnrichmentVO the GeneEnrichmentVO object containing the parameters for the enrichment analysis
     * @return a GeneEnrichmentResultVO object containing the enrichment results
     */
    GeneEnrichmentResultVO listGeneEnrichmentData(GeneEnrichmentVO geneEnrichmentVO);

    /**
     * Retrieves a list of Sample objects based on the given gene symbol.
     *
     * @param gene the gene symbol
     * @return a list of Sample objects
     */
    List<Sample> listSampleDataByGene(String gene);

    /**
     * Retrieves a list of Sample objects based on the given transcription factor symbol.
     *
     * @param tf the transcription factor symbol
     * @return a list of Sample objects
     */
    List<Sample> listSampleDataByTf(String tf);
}
