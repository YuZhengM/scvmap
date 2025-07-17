package com.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.collect.Lists;
import com.spring.boot.mapper.*;
import com.spring.boot.pojo.*;
import com.spring.boot.service.GeneTfDetailService;
import com.spring.boot.util.model.vo.echarts.EchartsPieData;
import com.spring.boot.util.model.vo.echarts.SeriesPieData;
import com.spring.boot.util.util.ApplicationUtil;
import com.spring.boot.util.util.ListUtil;
import com.spring.boot.util.util.NullUtil;
import com.spring.boot.util.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.spring.boot.util.util.ApplicationUtil.getElementSignalId;

/**
 * Element component result interface information    private TraitGeneHg19Mapper traitGeneHg19Mapper;
 *
 * @author Zhengmin Yu
 */
@CacheConfig(cacheNames = "gene_tf_detail", keyGenerator = "cacheKeyGenerator")
@Service
public class GeneTfDetailServiceImpl implements GeneTfDetailService {

    private GeneMapper geneMapper;
    private TraitMapper traitMapper;
    private MagmaMapper magmaMapper;
    private HomerMapper homerMapper;
    private CommonSnpMapper commonSnpMapper;
    private EnhancerSeaHg19Mapper enhancerSeaHg19Mapper;
    private EnhancerSeaHg38Mapper enhancerSeaHg38Mapper;
    private EnhancerSedbMapper enhancerSedbMapper;
    private EqtlMapper eqtlMapper;
    private RiskSnpHg19Mapper riskSnpHg19Mapper;
    private SuperEnhancerHg19DbsuperMapper superEnhancerHg19DbsuperMapper;
    private SuperEnhancerSeav3Hg19Mapper superEnhancerSeav3Hg19Mapper;
    private SuperEnhancerSedbv2Hg19Mapper superEnhancerSedbv2Hg19Mapper;
    private RiskSnpHg38Mapper riskSnpHg38Mapper;
    private SuperEnhancerHg38DbsuperMapper superEnhancerHg38DbsuperMapper;
    private SuperEnhancerSeav3Hg38Mapper superEnhancerSeav3Hg38Mapper;
    private SuperEnhancerSedbv2Hg38Mapper superEnhancerSedbv2Hg38Mapper;
    private GeneTraitCountMapper geneTraitCountMapper;
    private TfTraitCountMapper tfTraitCountMapper;
    private TfMapper tfMapper;
    private TraitGeneChunkMapper traitGeneChunkMapper;
    private TraitTfChunkMapper traitTfChunkMapper;

    public GeneTfDetailServiceImpl() {
    }

    @Autowired
    public GeneTfDetailServiceImpl(GeneMapper geneMapper,
                                   TraitMapper traitMapper,
                                   MagmaMapper magmaMapper,
                                   HomerMapper homerMapper,
                                   CommonSnpMapper commonSnpMapper,
                                   EnhancerSeaHg19Mapper enhancerSeaHg19Mapper, EnhancerSedbMapper enhancerSedbMapper,
                                   EqtlMapper eqtlMapper,
                                   RiskSnpHg19Mapper riskSnpHg19Mapper,
                                   SuperEnhancerHg19DbsuperMapper superEnhancerHg19DbsuperMapper,
                                   SuperEnhancerSeav3Hg19Mapper superEnhancerSeav3Hg19Mapper,
                                   SuperEnhancerSedbv2Hg19Mapper superEnhancerSedbv2Hg19Mapper,
                                   EnhancerSeaHg38Mapper enhancerSeaHg38Mapper,
                                   RiskSnpHg38Mapper riskSnpHg38Mapper,
                                   SuperEnhancerHg38DbsuperMapper superEnhancerHg38DbsuperMapper,
                                   SuperEnhancerSeav3Hg38Mapper superEnhancerSeav3Hg38Mapper,
                                   SuperEnhancerSedbv2Hg38Mapper superEnhancerSedbv2Hg38Mapper,
                                   GeneTraitCountMapper geneTraitCountMapper,
                                   TfTraitCountMapper tfTraitCountMapper,
                                   TfMapper tfMapper, TraitGeneChunkMapper traitGeneChunkMapper, TraitTfChunkMapper traitTfChunkMapper) {
        this.geneMapper = geneMapper;
        this.traitMapper = traitMapper;
        this.magmaMapper = magmaMapper;
        this.homerMapper = homerMapper;
        this.commonSnpMapper = commonSnpMapper;
        this.enhancerSeaHg19Mapper = enhancerSeaHg19Mapper;
        this.enhancerSedbMapper = enhancerSedbMapper;
        this.eqtlMapper = eqtlMapper;
        this.riskSnpHg19Mapper = riskSnpHg19Mapper;
        this.superEnhancerHg19DbsuperMapper = superEnhancerHg19DbsuperMapper;
        this.superEnhancerSeav3Hg19Mapper = superEnhancerSeav3Hg19Mapper;
        this.superEnhancerSedbv2Hg19Mapper = superEnhancerSedbv2Hg19Mapper;
        this.enhancerSeaHg38Mapper = enhancerSeaHg38Mapper;
        this.riskSnpHg38Mapper = riskSnpHg38Mapper;
        this.superEnhancerHg38DbsuperMapper = superEnhancerHg38DbsuperMapper;
        this.superEnhancerSeav3Hg38Mapper = superEnhancerSeav3Hg38Mapper;
        this.superEnhancerSedbv2Hg38Mapper = superEnhancerSedbv2Hg38Mapper;
        this.geneTraitCountMapper = geneTraitCountMapper;
        this.tfTraitCountMapper = tfTraitCountMapper;
        this.tfMapper = tfMapper;
        this.traitGeneChunkMapper = traitGeneChunkMapper;
        this.traitTfChunkMapper = traitTfChunkMapper;
    }

    /**
     * Retrieves a list of Trait objects based on the given genome and traitTfList.
     * Sets the entity class for the query wrapper.
     * Extracts distinct trait IDs from the traitTfList.
     * If the traitIdList is not empty, creates a query wrapper for Trait and filters by traitIdList and genome.
     * Returns the list of Trait objects or an empty list if the traitIdList is empty.
     *
     * @param traitIdList the list of trait ID
     * @return a list of Trait objects
     */
    private List<Trait> getTraitList(List<String> traitIdList) {
        // Check if the traitIdList is not empty
        if (ListUtil.isNotEmpty(traitIdList)) {
            // Create a query wrapper for Trait
            LambdaQueryWrapper<Trait> traitQueryWrapper = new LambdaQueryWrapper<>();
            // Filter by traitIdList
            traitQueryWrapper.in(Trait::getTraitId, traitIdList);
            // Execute the query and return the result
            return traitMapper.selectList(traitQueryWrapper);
        }
        // Return an empty list if the traitIdList is empty
        return NullUtil.listEmpty();
    }

    /**
     * Retrieves a list of gene annotation objects based on the given geneInfo.
     * Sets the entity class for the query wrapper.
     * Creates a query wrapper for gene annotations and filters by chromosome and position.
     * Returns the list of gene annotation objects.
     *
     * @param t        the mapper to use for the query
     * @param geneInfo the gene information to filter by
     * @param k        the type of the gene annotation objects
     * @return a list of gene annotation objects
     */
    private <K extends BasePosition, T extends BaseMapper<K>> List<K> getGeneAnnotationBasePosition(T t, Gene geneInfo, K k) {
        // Create a query wrapper for gene annotations
        LambdaQueryWrapper<K> queryWrapper = new LambdaQueryWrapper<>();
        // Set the entity class for the query wrapper
        //noinspection unchecked
        queryWrapper.setEntityClass((Class<K>) k.getClass());
        // Filter by chromosome and position
        queryWrapper.between(K::getPosition, geneInfo.getStart(), geneInfo.getEnd());
        // Execute the query and return the result
        return t.selectList(queryWrapper);
    }

    private <K extends BaseRegion, T extends BaseMapper<K>> List<K> getGeneAnnotationBaseRegion(T t, Gene geneInfo, K k) {
        // Create a query wrapper for gene annotations
        LambdaQueryWrapper<K> queryWrapper = new LambdaQueryWrapper<>();
        // Set the entity class for the query wrapper
        //noinspection unchecked
        queryWrapper.setEntityClass((Class<K>) k.getClass());
        // Filter by chromosome and region
        //noinspection DuplicatedCode
        queryWrapper.eq(K::getChr, geneInfo.getChr());
        queryWrapper.and(qw -> qw.between(K::getStart, geneInfo.getStart(), geneInfo.getEnd()).or().between(K::getEnd, geneInfo.getStart(), geneInfo.getEnd()).or(qw1 -> qw1.le(K::getStart, geneInfo.getStart()).ge(K::getEnd, geneInfo.getStart())).or(qw2 -> qw2.le(K::getStart, geneInfo.getEnd()).ge(K::getEnd, geneInfo.getEnd())));
        // Execute the query and return the result
        return t.selectList(queryWrapper);
    }

    /**
     * Retrieves Echarts pie data based on the given query wrapper and mapper.
     * Sets the entity class for the query wrapper.
     * Clones the query wrapper and filters by genome 'hg19' and 'hg38'.
     * Counts the results for each genome and creates pie data series.
     * Returns the Echarts pie data object.
     *
     * @param tList the count
     * @return an EchartsPieData object
     */
    private <T extends BaseTraitCount> EchartsPieData<String, String> getEchartsPieData(List<T> tList) {
        // Create the legend list
        List<String> legendList = Arrays.asList("hg19", "hg38");

        // Create the series pie data list
        List<SeriesPieData> seriesPieDataList = Lists.newArrayListWithCapacity(2);

        for (T t : tList) {
            if (StringUtil.isEqual(t.getGenome(), "hg19")) {
                seriesPieDataList.add(SeriesPieData.builder().name("hg19").value(t.getCount()).build());
            } else {
                seriesPieDataList.add(SeriesPieData.builder().name("hg38").value(t.getCount()).build());
            }
        }
        // Build and return the Echarts pie data object
        return EchartsPieData.<String, String>builder().legend(legendList).data(seriesPieDataList).build();
    }

    /**
     * Retrieves and caches gene information based on gene name and genome.
     *
     * @param gene   the name of the gene
     * @param genome the genome version
     * @return the gene information
     */
    @Cacheable
    @Override
    public List<Gene> listGeneInfo(String gene, String genome) {
        // Create a new LambdaQueryWrapper for the Gene entity
        LambdaQueryWrapper<Gene> queryWrapper = new LambdaQueryWrapper<>();
        // Add an equal condition for the gene name
        queryWrapper.eq(Gene::getGeneName, gene);
        // Add an equal condition for the genome
        queryWrapper.eq(Gene::getGenome, genome);
        // Execute the query and return the first result
        return geneMapper.selectList(queryWrapper);
    }

    @Cacheable
    @Override
    public Gene listGeneById(String geneId, String genome) {
        LambdaQueryWrapper<Gene> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Gene::getGeneId, geneId);
        queryWrapper.eq(Gene::getGenome, genome);
        return geneMapper.selectOne(queryWrapper);
    }

    @Cacheable
    @Override
    public Tf listTfInfo(String tf) {
        LambdaQueryWrapper<Tf> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tf::getTfName, tf);
        return tfMapper.selectOne(queryWrapper);
    }

    private Gene getGeneInfo(String geneId, String genome) {
        // Create a new LambdaQueryWrapper for the Gene entity
        LambdaQueryWrapper<Gene> queryWrapper = new LambdaQueryWrapper<>();
        // Add an equal condition for the gene name
        queryWrapper.eq(Gene::getGeneId, geneId);
        // Add an equal condition for the genome
        queryWrapper.eq(Gene::getGenome, genome);
        // Execute the query and return the first result
        return geneMapper.selectOne(queryWrapper);
    }


    /**
     * Retrieves and caches a list of CommonSnp objects interacting with a specific gene.
     *
     * @param geneId the ID of the gene
     * @param genome the genome version
     * @return a list of CommonSnp objects
     */
    @Cacheable
    @Override
    public List<? extends CommonSnp> listGeneInteractiveCommonSnp(String geneId, String genome) {
        // Get gene information
        Gene geneInfo = getGeneInfo(geneId, genome);
        // Get gene annotations for CommonSnp
        return commonSnpMapper.selectByRegionAndGenome(geneInfo.getChr(), geneInfo.getStart(), geneInfo.getEnd(), genome);
    }

    /**
     * Retrieves and caches a list of eQTL objects interacting with a specific gene.
     *
     * @param geneId the ID of the gene
     * @param genome the genome version
     * @return a list of eQTL objects
     */
    @Cacheable
    @Override
    public List<? extends Eqtl> listEqtlByGene(String geneId, String genome) {
        // Get gene information
        Gene geneInfo = getGeneInfo(geneId, genome);
        String geneName = geneInfo.getGeneName();
        int symbol = getElementSignalId(geneName);
        // Get gene annotations for eQTL
        return eqtlMapper.selectByRegionAndGenome(geneName, genome, symbol);
    }

    /**
     * Retrieves and caches a list of RiskSnp objects interacting with a specific gene.
     *
     * @param geneId the ID of the gene
     * @param genome the genome version
     * @return a list of RiskSnp objects
     */
    @Cacheable
    @Override
    public List<? extends RiskSnp> listGeneInteractiveRiskSnp(String geneId, String genome) {
        // Get gene information
        Gene geneInfo = getGeneInfo(geneId, genome);
        // Get gene annotations for RiskSnp
        return StringUtil.isEqual(genome, "hg19") ? getGeneAnnotationBasePosition(riskSnpHg19Mapper, geneInfo, new RiskSnpHg19()) : getGeneAnnotationBasePosition(riskSnpHg38Mapper, geneInfo, new RiskSnpHg38());
    }

    /**
     * Retrieves and caches a list of enhancer (SEA) objects interacting with a specific gene.
     *
     * @param geneId the ID of the gene
     * @param genome the genome version
     * @return a list of enhancer (SEA) objects
     */
    @Cacheable
    @Override
    public List<? extends EnhancerSea> listGeneInteractiveEnhancerSea(String geneId, String genome) {
        // Get gene information
        Gene geneInfo = getGeneInfo(geneId, genome);
        // Get gene annotations for enhancer (SEA)
        return StringUtil.isEqual(genome, "hg19") ? getGeneAnnotationBaseRegion(enhancerSeaHg19Mapper, geneInfo, new EnhancerSeaHg19()) : getGeneAnnotationBaseRegion(enhancerSeaHg38Mapper, geneInfo, new EnhancerSeaHg38());
    }

    /**
     * Retrieves and caches a list of EnhancerSedb objects interacting with a specific gene.
     *
     * @param geneId the ID of the gene
     * @param genome the genome version
     * @return a list of EnhancerSedb objects
     */
    @Cacheable
    @Override
    public List<? extends EnhancerSedb> listGeneInteractiveEnhancerSedb(String geneId, String genome) {
        // Get gene information
        Gene geneInfo = getGeneInfo(geneId, genome);
        // Get gene annotations for EnhancerSedb
        return enhancerSedbMapper.selectByRegionAndGenome(geneInfo.getChr(), geneInfo.getStart(), geneInfo.getEnd(), genome);
    }

    /**
     * Retrieves a list of SuperEnhancerDbsuper objects associated with a specific gene and genome.
     *
     * @param geneId The Ensembl ID.
     * @param genome The genome version.
     * @return List of SuperEnhancerDbsuper objects.
     */
    @Cacheable
    @Override
    public List<? extends SuperEnhancerDbsuper> listGeneInteractiveSuperEnhancerDbsuper(String geneId, String genome) {
        // Get gene information
        Gene geneInfo = getGeneInfo(geneId, genome);
        // Get the list of SuperEnhancerDbsuper annotations for the gene
        return StringUtil.isEqual(genome, "hg19") ? getGeneAnnotationBaseRegion(superEnhancerHg19DbsuperMapper, geneInfo, new SuperEnhancerDbsuperHg19()) : getGeneAnnotationBaseRegion(superEnhancerHg38DbsuperMapper, geneInfo, new SuperEnhancerDbsuperHg38());
    }

    /**
     * Retrieves a list of SuperEnhancerSea objects associated with a specific gene and genome.
     *
     * @param geneId The Ensembl ID.
     * @param genome The genome version.
     * @return List of SuperEnhancerSea objects.
     */
    @Cacheable
    @Override
    public List<? extends SuperEnhancerSea> listGeneInteractiveSuperEnhancerSea(String geneId, String genome) {
        // Get gene information
        Gene geneInfo = getGeneInfo(geneId, genome);
        // Get the list of SuperEnhancerSea annotations for the gene
        return StringUtil.isEqual(genome, "hg19") ? getGeneAnnotationBaseRegion(superEnhancerSeav3Hg19Mapper, geneInfo, new SuperEnhancerSeaHg19()) : getGeneAnnotationBaseRegion(superEnhancerSeav3Hg38Mapper, geneInfo, new SuperEnhancerSeaHg38());
    }

    /**
     * Retrieves a list of SuperEnhancerSedb objects associated with a specific gene and genome.
     *
     * @param geneId The Ensembl ID.
     * @param genome The genome version.
     * @return List of SuperEnhancerSedb objects.
     */
    @Cacheable
    @Override
    public List<? extends SuperEnhancerSedb> listGeneInteractiveSuperEnhancerSedb(String geneId, String genome) {
        // Get gene information
        Gene geneInfo = getGeneInfo(geneId, genome);
        // Get the list of SuperEnhancerSedb annotations for the gene
        return StringUtil.isEqual(genome, "hg19") ? getGeneAnnotationBaseRegion(superEnhancerSedbv2Hg19Mapper, geneInfo, new SuperEnhancerSedbHg19()) : getGeneAnnotationBaseRegion(superEnhancerSedbv2Hg38Mapper, geneInfo, new SuperEnhancerSedbHg38());
    }

    /**
     * Retrieves a list of traits associated with a specific gene and genome.
     *
     * @param gene   The gene symbol or identifier.
     * @param genome The genome version or identifier.
     * @return List of Trait objects.
     */
    @Cacheable
    @Override
    public List<Trait> listTraitByGeneGenome(String gene, String genome) {
        // Execute the query to retrieve a list of TraitGene entities
        int signalId = getElementSignalId(gene);
        List<String> traitIdList = traitGeneChunkMapper.selectListByGeneAndGenome(gene, genome, signalId);
        // Convert TraitGene list to Trait list
        return getTraitList(traitIdList);
    }

    /**
     * Retrieves a list of Magma information data by trait ID and gene.
     *
     * @param traitId The trait ID.
     * @param gene    The gene symbol or identifier.
     * @param genome  The genome version or identifier.
     * @return List of Magma objects.
     */
    @Cacheable
    @Override
    public List<Magma> listMagmaInfoDataByTraitIdAndGene(String traitId, String gene, String genome) {
        // Get the signal ID associated with the trait ID
        String signalId = ApplicationUtil.getTraitSignalId(traitId);
        // Retrieve Magma data using the signal ID, trait ID, gene, and genome
        return magmaMapper.selectByTraitIdAndGene(signalId, traitId, gene, genome);
    }

    /**
     * Retrieves the count of gene traits and returns the data in a format suitable for Echarts pie chart.
     *
     * @param gene The gene symbol or identifier.
     * @return EchartsPieData<String, String> object.
     */
    @Cacheable
    @Override
    public EchartsPieData<String, String> getGeneTraitCount(String gene) {
        // Create a new LambdaQueryWrapper for TraitGene entity
        LambdaQueryWrapper<GeneTraitCount> queryWrapper = new LambdaQueryWrapper<>();
        // Add an equality condition for the gene field
        queryWrapper.eq(GeneTraitCount::getGene, gene);
        // Retrieve and construct the Echarts pie data
        List<GeneTraitCount> geneTraitCountList = geneTraitCountMapper.selectList(queryWrapper);
        return getEchartsPieData(geneTraitCountList);
    }

    /**
     * Retrieves the count of TF traits and returns the data in a format suitable for Echarts pie chart.
     *
     * @param tf The transcription factor symbol or identifier.
     * @return EchartsPieData<String, String> object.
     */
    @Cacheable
    @Override
    public EchartsPieData<String, String> getTfTraitCount(String tf) {
        // Create a new LambdaQueryWrapper for TraitGene entity
        LambdaQueryWrapper<TfTraitCount> queryWrapper = new LambdaQueryWrapper<>();
        // Add an equality condition for the gene field
        queryWrapper.eq(TfTraitCount::getTf, tf);
        // Retrieve and construct the Echarts pie data
        List<TfTraitCount> tfTraitCountList = tfTraitCountMapper.selectList(queryWrapper);
        return getEchartsPieData(tfTraitCountList);
    }

    /**
     * Retrieves a list of Homer information data by trait ID and TF.
     *
     * @param traitId The trait ID.
     * @param tf      The transcription factor symbol or identifier.
     * @param genome  The genome version or identifier.
     * @return List of Homer objects.
     */
    @Cacheable
    @Override
    public List<Homer> listHomerInfoDataByTraitIdAndTf(String traitId, String tf, String genome) {
        // Get the signal ID associated with the trait ID
        String signalId = ApplicationUtil.getTraitSignalId(traitId);
        // Retrieve Homer data using the signal ID, trait ID, TF, and genome
        return homerMapper.selectByTraitIdAndGene(signalId, traitId, tf, genome);
    }

    /**
     * Retrieves a list of traits associated with a specific TF and genome.
     *
     * @param tf     The transcription factor symbol or identifier.
     * @param genome The genome version or identifier.
     * @return List of Trait objects.
     */
    @Cacheable
    @Override
    public List<Trait> listTraitByTfGenome(String tf, String genome) {
        int signalId = getElementSignalId(tf);
        List<String> traitIdList = traitTfChunkMapper.selectListByTfAndGenome(tf, genome, signalId);
        return getTraitList(traitIdList);
    }
}
