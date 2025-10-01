package com.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.spring.boot.config.bean.ExecLinux;
import com.spring.boot.config.bean.Path;
import com.spring.boot.mapper.*;
import com.spring.boot.pojo.*;
import com.spring.boot.pojo.vo.DifferenceElementHeatmapVO;
import com.spring.boot.pojo.vo.SampleTraitInfo;
import com.spring.boot.service.DetailService;
import com.spring.boot.util.constant.SystemException;
import com.spring.boot.util.exception.RunException;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.vo.FieldNumber;
import com.spring.boot.util.model.vo.canvasXpress.CanvasXpressHeatMapData;
import com.spring.boot.util.model.vo.echarts.*;
import com.spring.boot.util.model.vo.plotly.PlotlyClusterData;
import com.spring.boot.util.model.vo.plotly.PlotlyData;
import com.spring.boot.util.util.ApplicationUtil;
import com.spring.boot.util.util.ListUtil;
import com.spring.boot.util.util.NullUtil;
import com.spring.boot.util.util.StringUtil;
import com.spring.boot.util.util.result.Page;
import com.spring.boot.util.util.result.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.spring.boot.util.constant.ApplicationConstant.ALL_DATA_SYMBOL;
import static com.spring.boot.util.util.ApplicationUtil.*;

/**
 * Service implementation for detailed data operations.
 * Contains methods for retrieving and processing data related to samples, traits, and other entities.
 *
 * @author Zhengmin Yu
 */
@CacheConfig(cacheNames = "detail", keyGenerator = "cacheKeyGenerator")
@Service
public class DetailServiceImpl implements DetailService {

    private SourceMapper sourceMapper;
    private TraitMapper traitMapper;
    private SampleMapper sampleMapper;
    private SampleCellMapper sampleCellMapper;
    private SampleCellTypeMapper sampleCellTypeMapper;
    private VariantInfoMapper variantInfoMapper;
    private TraitChrCountMapper traitChrCountMapper;
    private MagmaMapper magmaMapper;
    private HomerMapper homerMapper;
    private ExecLinux execLinux;
    private Path path;
    private SampleEnrichSampleIdMapper sampleEnrichSampleIdMapper;
    private TraitEnrichMapper traitEnrichMapper;
    private DifferenceGeneChunkMapper differenceGeneChunkMapper;
    private DifferenceTfChunkMapper differenceTfChunkMapper;
    private CiceroSampleTraitGeneMapper ciceroSampleTraitGeneMapper;
    private MagmaAnnoMapper magmaAnnoMapper;
    private ChromVarDifferenceTfMapper chromVarDifferenceTfMapper;
    private SampleTimeSexDrugMapper sampleTimeSexDrugMapper;
    private GimmeSampleTraitTfMapper gimmeSampleTraitTfMapper;
    private VariantInfoSusieMapper variantInfoSusieMapper;
    private TraitChrCountSusieMapper traitChrCountSusieMapper;
    private TraitSusieMapper traitSusieMapper;
    private TimeSexDrugDifferenceGeneMapper timeSexDrugDifferenceGeneMapper;
    private SampleTraitEnrichSusieMapper sampleTraitEnrichSusieMapper;
    private MpraMapper mpraMapper;
    private EqtlMapper eqtlMapper;
    private InteractionMapper interactionMapper;
    private TrsDistributionScoreMapper trsDistributionScoreMapper;

    /**
     * Default constructor for DetailServiceImpl.
     */
    public DetailServiceImpl() {
    }

    /**
     * Constructor with dependency injection for all required mappers and utilities.
     *
     * @param sourceMapper         the mapper for source data
     * @param traitMapper          the mapper for trait data
     * @param sampleMapper         the mapper for sample data
     * @param sampleCellMapper     the mapper for sample cell data
     * @param sampleCellTypeMapper the mapper for sample cell type data
     * @param variantInfoMapper    the mapper for variant info data
     * @param traitChrCountMapper  the mapper for trait chromosome count data
     * @param magmaMapper          the mapper for Magma data
     * @param homerMapper          the mapper for Homer data
     * @param execLinux            the utility for executing Linux commands
     * @param path                 the utility for handling file paths
     */
    @Autowired
    public DetailServiceImpl(SourceMapper sourceMapper,
                             TraitMapper traitMapper,
                             SampleMapper sampleMapper,
                             SampleCellMapper sampleCellMapper,
                             SampleCellTypeMapper sampleCellTypeMapper,
                             VariantInfoMapper variantInfoMapper,
                             TraitChrCountMapper traitChrCountMapper,
                             MagmaMapper magmaMapper,
                             HomerMapper homerMapper,
                             ExecLinux execLinux,
                             Path path,
                             SampleEnrichSampleIdMapper sampleEnrichSampleIdMapper,
                             TraitEnrichMapper traitEnrichMapper, DifferenceGeneChunkMapper differenceGeneChunkMapper, DifferenceTfChunkMapper differenceTfChunkMapper, CiceroSampleTraitGeneMapper ciceroSampleTraitGeneMapper, MagmaAnnoMapper magmaAnnoMapper, ChromVarDifferenceTfMapper chromVarDifferenceTfMapper, SampleTimeSexDrugMapper sampleTimeSexDrugMapper, GimmeSampleTraitTfMapper gimmeSampleTraitTfMapper, VariantInfoSusieMapper variantInfoSusieMapper, TraitChrCountSusieMapper traitChrCountSusieMapper, TraitSusieMapper traitSusieMapper, TimeSexDrugDifferenceGeneMapper timeSexDrugDifferenceGeneMapper, SampleTraitEnrichSusieMapper sampleTraitEnrichSusieMapper, MpraMapper mpraMapper, EqtlMapper eqtlMapper, InteractionMapper interactionMapper, TrsDistributionScoreMapper trsDistributionScoreMapper) {
        this.sourceMapper = sourceMapper;
        this.traitMapper = traitMapper;
        this.sampleMapper = sampleMapper;
        this.sampleCellMapper = sampleCellMapper;
        this.sampleCellTypeMapper = sampleCellTypeMapper;
        this.variantInfoMapper = variantInfoMapper;
        this.traitChrCountMapper = traitChrCountMapper;
        this.magmaMapper = magmaMapper;
        this.homerMapper = homerMapper;
        this.execLinux = execLinux;
        this.path = path;
        this.sampleEnrichSampleIdMapper = sampleEnrichSampleIdMapper;
        this.traitEnrichMapper = traitEnrichMapper;
        this.differenceGeneChunkMapper = differenceGeneChunkMapper;
        this.differenceTfChunkMapper = differenceTfChunkMapper;
        this.ciceroSampleTraitGeneMapper = ciceroSampleTraitGeneMapper;
        this.magmaAnnoMapper = magmaAnnoMapper;
        this.chromVarDifferenceTfMapper = chromVarDifferenceTfMapper;
        this.sampleTimeSexDrugMapper = sampleTimeSexDrugMapper;
        this.gimmeSampleTraitTfMapper = gimmeSampleTraitTfMapper;
        this.variantInfoSusieMapper = variantInfoSusieMapper;
        this.traitChrCountSusieMapper = traitChrCountSusieMapper;
        this.traitSusieMapper = traitSusieMapper;
        this.timeSexDrugDifferenceGeneMapper = timeSexDrugDifferenceGeneMapper;
        this.sampleTraitEnrichSusieMapper = sampleTraitEnrichSusieMapper;
        this.mpraMapper = mpraMapper;
        this.eqtlMapper = eqtlMapper;
        this.interactionMapper = interactionMapper;
        this.trsDistributionScoreMapper = trsDistributionScoreMapper;
    }

    /**
     * This method constructs a SampleTraitInfo object containing pie chart data for Echarts.
     * It uses the provided label, list of TraitSample, and counts for overlapped and non-overlapped samples.
     * The method builds the pie chart data with two segments: enriched and not enriched.
     *
     * @param label            the label for the pie chart segments
     * @param traitSampleList  the list of TraitSample objects
     * @param overlap_count    the count of samples that are enriched
     * @param no_overlap_count the count of samples that are not enriched
     * @return a SampleTraitInfo object with the constructed pie chart data
     */
    private <T> SampleTraitInfo<T> getEchartsPieInfo(String label, List<T> traitSampleList, int overlap_count, int no_overlap_count) {
        // Build the pie chart data with two segments: enriched and not enriched
        EchartsPieData<String, Integer> echartsPieData = EchartsPieData.<String, Integer>builder()
                .data(Arrays.asList( // Add the data for the pie chart segments
                        SeriesPieData.builder().name("Enriched " + label).value(overlap_count).build(), // Enriched segment
                        SeriesPieData.builder().name("No enriched " + label).value(no_overlap_count).build() // Not enriched segment
                ))
                .legend(Arrays.asList("Enriched " + label, "No enriched " + label)) // Set the legend for the pie chart
                .build();
        // Return the constructed SampleTraitInfo object with the pie chart data and trait sample list
        return SampleTraitInfo.<T>builder().traitSampleList(traitSampleList).echartsPieData(echartsPieData).build();
    }

    /**
     * This method retrieves a Sample object from the database based on the provided sampleId.
     * It uses caching to improve performance.
     * The method constructs a query to select a single Sample where the sampleId matches the provided value.
     *
     * @param sampleId the ID of the sample to retrieve
     * @return the Sample object retrieved from the database
     */
    @Cacheable
    @Override
    public Sample getSampleData(String sampleId) {
        // Create a new LambdaQueryWrapper for the Sample entity
        LambdaQueryWrapper<Sample> queryWrapper = new LambdaQueryWrapper<>();
        // Define the query condition to match the sampleId
        queryWrapper.eq(Sample::getSampleId, sampleId); // SQL: SELECT * FROM sample WHERE sample_id = [sampleId]
        // Execute the query and return the single Sample object
        return sampleMapper.selectOne(queryWrapper);
    }

    /**
     * Retrieves source data for a trait based on the source ID.
     * This method is cached to improve performance.
     *
     * @param sourceId the ID of the source to retrieve
     * @return the Source object corresponding to the sourceId
     */
    @Cacheable
    @Override
    public Source getTraitSourceData(String sourceId) {
        // Create a new LambdaQueryWrapper for the Source entity
        LambdaQueryWrapper<Source> queryWrapper = new LambdaQueryWrapper<>();
        // Set the query condition to match the source ID
        queryWrapper.eq(Source::getId, sourceId);
        // Execute the query and return the single Source object
        return sourceMapper.selectOne(queryWrapper);
    }

    @SuppressWarnings("unchecked")
    private <T extends Trait, K extends BaseMapper<T>> T buildTraitData(T t, String traitId, K k) {
        // Create a new LambdaQueryWrapper for the Trait entity
        LambdaQueryWrapper<T> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.setEntityClass((Class<T>) t.getClass());
        // Set the query condition to match the trait ID
        queryWrapper.eq(T::getTraitId, traitId);
        // Execute the query and retrieve the Trait object
        T trait = k.selectOne(queryWrapper);
        // Fetch the source data for the trait
        Source source = getTraitSourceData(trait.getSourceId());
        // Set the source on the trait object
        trait.setSource(source);
        // Return the trait object with the source data
        return trait;
    }

    /**
     * Retrieves trait data based on the trait ID.
     * This method is cached to improve performance.
     * It also fetches the associated source data and sets it on the trait.
     *
     * @param traitId the ID of the trait to retrieve
     * @return the Trait object corresponding to the traitId
     */
    @Cacheable
    @Override
    public Trait getTraitData(String traitId, String method) {
        return StringUtil.isEqual(method, "finemap") ? buildTraitData(new Trait(), traitId, traitMapper)
                : buildTraitData(new TraitSusie(), traitId, traitSusieMapper);
    }

    /**
     * Lists sample cell type data based on the sample ID.
     * This method is cached to improve performance.
     *
     * @param sampleId the ID of the sample
     * @return a list of SampleCellType objects associated with the sample ID
     */
    @Cacheable
    @Override
    public List<SampleCellType> listSampleCellTypeData(String sampleId) {
        // Create a new LambdaQueryWrapper for the SampleCellType entity
        LambdaQueryWrapper<SampleCellType> queryWrapper = new LambdaQueryWrapper<>();
        // Set the query condition to match the sample ID
        queryWrapper.eq(SampleCellType::getSampleId, sampleId);
        // Execute the query and return the list of SampleCellType objects
        return sampleCellTypeMapper.selectList(queryWrapper);
    }

    /**
     * Retrieves and caches the cell type count data for a given sample ID.
     * This data is used to generate a pie chart in Echarts.
     *
     * @param sampleId the ID of the sample
     * @return EchartsPieData containing the cell type count data
     */
    @Cacheable
    @Override
    public EchartsPieData<String, String> getCellTypeCount(String sampleId, String metadata) {
        List<FieldNumber> fieldNumberList;
        if (StringUtil.isEqual(metadata, "cell_type")) {
            // Retrieve the list of SampleCellType objects for the given sample ID
            List<SampleCellType> sampleCellTypeList = listSampleCellTypeData(sampleId);
            fieldNumberList = sampleCellTypeList
                    .stream().map(stringObjectMap -> FieldNumber.builder()
                            .field(String.valueOf(stringObjectMap.getCellType()))
                            .number(stringObjectMap.getCellCount()).build())
                    .toList();
        } else {
            LambdaQueryWrapper<SampleTimeSexDrug> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SampleTimeSexDrug::getSampleId, sampleId);
            queryWrapper.eq(SampleTimeSexDrug::getType, metadata);
            List<SampleTimeSexDrug> sampleTimeSexDrugList = sampleTimeSexDrugMapper.selectList(queryWrapper);

            fieldNumberList = sampleTimeSexDrugList
                    .stream().map(stringObjectMap -> FieldNumber.builder()
                            .field(String.valueOf(stringObjectMap.getTypeValue()))
                            .number(stringObjectMap.getTypeCount()).build())
                    .toList();
        }
        return getFieldCountEchartsData(fieldNumberList);
    }

    @SuppressWarnings("unchecked")
    private static <T extends TraitChrCount, K extends BaseMapper<T>> EchartsPieData<String, String> buildTraitChrCountData(T t, String traitId, String genome, K k) {
        // Create a new LambdaQueryWrapper for the TraitChrCount entity
        LambdaQueryWrapper<T> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.setEntityClass((Class<T>) t.getClass());

        // Set the query condition to match the trait ID
        queryWrapper.eq(T::getTraitId, traitId);
        // Set the query condition to match the genome version
        queryWrapper.eq(T::getGenome, genome);
        // Execute the query and retrieve the list of TraitChrCount objects
        List<T> tList = k.selectList(queryWrapper);
        // Map the list to FieldNumber objects for Echarts data format
        List<FieldNumber> fieldNumberList = tList.stream()
                .map(T -> FieldNumber.builder()
                        .field(T.getChr())
                        .number(T.getCount())
                        .build())
                .toList();
        // Convert the list to Echarts pie chart data format
        return ApplicationUtil.getFieldCountEchartsData(fieldNumberList);
    }

    /**
     * Retrieves and caches the trait chromosome count data for a given trait ID and genome.
     * This data is used to generate a pie chart in Echarts.
     *
     * @param traitId the ID of the trait
     * @param genome  the genome version
     * @return EchartsPieData containing the trait chromosome count data
     */
    @Cacheable
    @Override
    public EchartsPieData<String, String> getTraitChrCountData(String traitId, String genome, String method) {
        return StringUtil.isEqual(method, "finemap") ? buildTraitChrCountData(new TraitChrCount(), traitId, genome, traitChrCountMapper)
                : buildTraitChrCountData(new TraitChrCountSusie(), traitId, genome, traitChrCountSusieMapper);
    }

    /**
     * Retrieves and caches the trait information for a given sample ID and method.
     * This data is used to generate a pie chart in Echarts.
     *
     * @param sampleId the ID of the sample
     * @param method   the method used to select traits
     * @return SampleTraitInfo containing the trait information
     */
    @Cacheable
    @Override
    public SampleTraitInfo<? extends SampleEnrichSampleId> listTraitBySampleId(String sampleId, String method, String fineMappingMethod) {
        // Define the label for the pie chart
        String label = "traits";
        // Retrieve the list of TraitSample objects for the given sample ID and method
        List<? extends SampleEnrichSampleId> traitEnrichList;

        int total_size = 15805;

        if (StringUtil.isEqual(fineMappingMethod, "finemap")) {
            traitEnrichList = sampleEnrichSampleIdMapper.selectBySampleIdAndMethod(sampleId, method);
        } else {
            traitEnrichList = sampleTraitEnrichSusieMapper.selectBySampleIdAndMethod(sampleId, method);
            total_size = 79;
        }

        // Calculate the number of overlapping samples
        int overlap_count = traitEnrichList.size();
        // Calculate the number of non-overlapping samples
        int no_overlap_count = total_size - overlap_count;
        // Get the Echarts pie chart data for the trait information
        return getEchartsPieInfo(label, traitEnrichList, overlap_count, no_overlap_count);
    }

    /**
     * Retrieves and caches the sample information for a given trait ID and method.
     * This data is used to generate a pie chart in Echarts.
     *
     * @param traitId the ID of the trait
     * @param method  the method used to select samples
     * @return SampleTraitInfo containing the sample information
     */
    @Cacheable
    @Override
    public SampleTraitInfo<? extends TraitEnrich> listSampleInfoByTraitId(String traitId, String method, String fineMappingMethod) {
        // Define the label for the pie chart
        String label = "samples";
        // Retrieve the list of TraitSample objects for the given trait ID and method
        String signalId = getTraitSignalId(traitId);

        List<? extends TraitEnrich> traitSampleList;

        if (StringUtil.isEqual(fineMappingMethod, "finemap")) {
            traitSampleList = traitEnrichMapper.selectByTraitIdAndMethod(traitId, method, signalId);
        } else {
            traitSampleList = sampleTraitEnrichSusieMapper.selectByTraitIdAndMethod(traitId, method);
        }

        // Calculate the number of overlapping samples
        int overlap_count = traitSampleList.size();
        // Calculate the number of non-overlapping samples
        int no_overlap_count = 183 - overlap_count;
        // Get the Echarts pie chart data for the sample information
        return getEchartsPieInfo(label, traitSampleList, overlap_count, no_overlap_count);
    }

    /**
     * Retrieves and caches the cluster coordinate data for a given sample ID and cell rate.
     * This data is used to generate a Plotly cluster graph.
     *
     * @param sampleId the ID of the sample
     * @param cellRate the rate of cells to be considered for clustering
     * @return PlotlyClusterData containing the cluster coordinate data
     */
    @Cacheable
    @Override
    public PlotlyClusterData<Double, Double> listClusterCoordinate(String sampleId, Double cellRate, String metadata) {

        int exist = 0;
        List<FieldNumber> fieldNumberList;

        if (StringUtil.isEqual(metadata, "cell_type")) {
            exist = 1;
            // Retrieve the list of SampleCellType objects for the given sample ID
            List<SampleCellType> sampleCellTypeList = listSampleCellTypeData(sampleId);
            fieldNumberList = sampleCellTypeList.stream().map(stringObjectMap -> FieldNumber.builder()
                    .field(stringObjectMap.getCellType())
                    .number(stringObjectMap.getCellCount()).build()).toList();
        } else {
            if (StringUtil.isEqual(metadata, "time")) {
                Sample sample = getSampleData(sampleId);
                exist = sample.getTimeExist();
            } else if (StringUtil.isEqual(metadata, "sex")) {
                Sample sample = getSampleData(sampleId);
                exist = sample.getSexExist();
            } else if (StringUtil.isEqual(metadata, "drug")) {
                Sample sample = getSampleData(sampleId);
                exist = sample.getDrugExist();
            }

            LambdaQueryWrapper<SampleTimeSexDrug> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SampleTimeSexDrug::getSampleId, sampleId);
            queryWrapper.eq(SampleTimeSexDrug::getType, metadata);
            List<SampleTimeSexDrug> sampleTimeSexDrugs = sampleTimeSexDrugMapper.selectList(queryWrapper);

            fieldNumberList = sampleTimeSexDrugs.stream().map(stringObjectMap -> FieldNumber.builder()
                    .field(stringObjectMap.getTypeValue())
                    .number(stringObjectMap.getTypeCount()).build()).toList();
        }

        if (exist == 0) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }

        // Initialize a list to hold the Plotly data for each cell type
        List<PlotlyData<Double, Double>> plotlyDataList = Lists.newArrayListWithCapacity(fieldNumberList.size());
        // Declare lists to hold x-coordinates, y-coordinates, and text labels
        List<Double> xList;
        List<Double> yList;
        List<String> textList;
        // Iterate over each SampleCellType to process clustering data
        for (FieldNumber fieldNumber : fieldNumberList) {
            // Determine the number of cells to include based on the cell rate
            int cellNumber = getRandomCellNumber(fieldNumber.getNumber(), cellRate);
            // Create a query wrapper to select SampleCell objects
            LambdaQueryWrapper<SampleCell> queryWrapper = new LambdaQueryWrapper<>();
            // Set query conditions: match sample ID and cell type, and limit by cell type index
            queryWrapper.eq(SampleCell::getSampleId, sampleId);

            if (StringUtil.isEqual(metadata, "cell_type")) {
                queryWrapper.eq(SampleCell::getCellType, fieldNumber.getField());
                queryWrapper.lt(SampleCell::getCellTypeIndex, cellNumber);
            } else if (StringUtil.isEqual(metadata, "time")) {
                queryWrapper.eq(SampleCell::getTime, fieldNumber.getField());
                queryWrapper.lt(SampleCell::getTimeIndex, cellNumber);
            } else if (StringUtil.isEqual(metadata, "sex")) {
                queryWrapper.eq(SampleCell::getSex, fieldNumber.getField());
                queryWrapper.lt(SampleCell::getSexIndex, cellNumber);
            } else if (StringUtil.isEqual(metadata, "drug")) {
                queryWrapper.eq(SampleCell::getDrug, fieldNumber.getField());
                queryWrapper.lt(SampleCell::getDrugIndex, cellNumber);
            }

            // Execute the query to retrieve the list of SampleCell objects
            List<SampleCell> sampleCellList = sampleCellMapper.selectList(queryWrapper);
            // Initialize containers for the coordinates and text labels
            xList = Lists.newArrayListWithExpectedSize(64);
            yList = Lists.newArrayListWithExpectedSize(64);
            textList = Lists.newArrayListWithExpectedSize(64);
            // Iterate over the SampleCell list to populate the coordinate and text lists
            for (SampleCell sampleCell : sampleCellList) {
                // Add the UMAP x and y coordinates and cell type to the respective lists
                xList.add(sampleCell.getUmapX());
                yList.add(sampleCell.getUmapY());
                textList.add(sampleCell.getCellType());
            }
            // Build the Plotly data object for the current cell type and add it to the list
            plotlyDataList.add(PlotlyData.<Double, Double>builder()
                    .x(xList)
                    .y(yList)
                    .name(fieldNumber.getField())
                    .text(textList)
                    .build());
        }
        // Build and return the PlotlyClusterData object containing all the Plotly data
        return PlotlyClusterData.<Double, Double>builder()
                .plotlyDataList(plotlyDataList)
                .build();
    }

    /**
     * Retrieves and caches the trait cluster coordinate data for a given sample ID, trait ID, method, and cell rate.
     * This data is used to generate a Plotly cluster graph for trait-specific clustering.
     *
     * @param sampleId the ID of the sample
     * @param traitId  the ID of the trait
     * @param method   the method used for clustering
     * @param cellRate the rate of cells to be considered for clustering
     * @return PlotlyClusterData containing the trait cluster coordinate data
     * @throws IOException if an I/O error occurs during the execution of the Python script
     */
    @Cacheable
    @Override
    public PlotlyClusterData<Double, Double> listTraitClusterCoordinate(String sampleId, String traitId, String method, Double cellRate, String metadata, String fineMappingMethod) throws IOException {
        // Retrieve the signal ID for the given trait ID
        String signalId = getTraitSignalId(traitId);
        // Create a query wrapper to select the Trait object
        LambdaQueryWrapper<Trait> queryWrapper = new LambdaQueryWrapper<>();
        // Set the query condition to match the trait ID
        queryWrapper.eq(Trait::getTraitId, traitId);
        // Execute the query to retrieve the Trait object
        Trait trait = traitMapper.selectOne(queryWrapper);
        // Retrieve the Sample object for the given sample ID
        Sample sample = getSampleData(sampleId);
        // Get the work path for file operations
        String workPath = path.getWorkPath();
        // Construct the file path for the TRS file
        String trs_file = StringUtil.isEqual(fineMappingMethod, "finemap") ? workPath + "/trs/" + sample.getLabel() + "/chunk/" + sample.getLabel() + "_" + signalId + "_trs_" + method + ".h5ad"
                : workPath + "/download/trs_big_susie/" + sample.getLabel() + "/" + sample.getLabel() + "_trs_" + method + ".h5ad";
        // Construct the file path for the Python script
        String python_file = workPath + "/python/get_method_trait.py";
        // Construct the command to execute the Python script
        String exec = "python3 " + python_file + " " + trs_file + " " + traitId + " " + cellRate + " " + metadata;
        // Execute the Python command and retrieve the results
        List<String> results = execLinux.execCommand(exec).getResultList();
        // Process the Python results to extract barcodes, cell types, UMAP coordinates, and values
        List<String> barcodesList = listStringByPythonResult(results.get(0));
        List<String> metadataList = listStringByPythonResult(results.get(1));
        List<Double> umap1s = listDoubleByPythonResult(results.get(2));
        List<Double> umap2s = listDoubleByPythonResult(results.get(3));
        List<Double> values = listDoubleByPythonResult(results.get(4));
        // Initialize a list to hold the Plotly data for the trait
        List<PlotlyData<Double, Double>> plotlyDataList = Lists.newArrayListWithCapacity(1);
        // Create text labels by combining cell types and barcodes
        List<String> textList = IntStream.range(0, barcodesList.size())
                .mapToObj(i -> metadataList.get(i) + "-" + barcodesList.get(i))
                .collect(Collectors.toList());
        // Build the Plotly data object for the trait and add it to the list
        plotlyDataList.add(PlotlyData.<Double, Double>builder()
                .x(umap1s)
                .y(umap2s)
                .color(values)
                .name(trait.getTraitAbbr())
                .text(textList)
                .extraText(metadataList)
                .build());
        // Build and return the PlotlyClusterData object containing the Plotly data for the trait
        return PlotlyClusterData.<Double, Double>builder()
                .plotlyDataList(plotlyDataList)
                .build();
    }

    /**
     * Retrieves and caches a list of VariantInfo objects based on trait ID and genome.
     * This method fetches variant information associated with a specific trait and genome.
     *
     * @param traitId the ID of the trait
     * @param genome  the genome version
     * @param page    page
     * @return a list of VariantInfo objects
     */
    @Cacheable
    @Override
    public PageResult<VariantInfo> listTraitInfoData(String traitId, String genome, String method, Page page) {
        // Fetch the trait signal ID using the trait ID
        String signalId = getTraitSignalId(traitId);
        // Select variant info data by trait ID, trait signal ID, and genome
        if (StringUtil.isEqual(method, "finemap")) {
            return PageResultUtil.format(page, () -> variantInfoMapper.selectByTraitIdAndPage(traitId, signalId, genome, page));
        } else {

            return PageResultUtil.format(page, () -> variantInfoSusieMapper.selectByTraitId(traitId, genome, page));
        }
    }

    /**
     * Retrieves and caches a list of DifferenceGene objects based on sample ID and cell type.
     * This method fetches differential gene data for a specific sample and cell type.
     *
     * @param sampleId the ID of the sample
     * @param cellType Cell type
     * @param page     page
     * @return a list of DifferenceGene objects
     */
    @Cacheable
    @Override
    public PageResult<? extends DifferenceGene> listDifferenceGeneBySampleId(String sampleId, String metadata, String cellType, Page page) {
        if (StringUtil.isEqual(metadata, "cell_type")) {
            return PageResultUtil.format(page, () -> differenceGeneChunkMapper.selectBySampleIdAndCellType(sampleId, cellType, page));
        } else {
            return PageResultUtil.format(page, () -> timeSexDrugDifferenceGeneMapper.selectBySampleIdAndCellType(sampleId, metadata, cellType, page));
        }
    }

    @Cacheable
    @Override
    public CanvasXpressHeatMapData<Double> getDifferenceGeneHeatmapBySampleId(DifferenceElementHeatmapVO differenceElementHeatmapVO) {

        String sampleId = differenceElementHeatmapVO.getSampleId();
        Integer topCount = differenceElementHeatmapVO.getTopCount();
        Double log2FoldChange = differenceElementHeatmapVO.getLog2FoldChange();

        String metadata = differenceElementHeatmapVO.getMetadata();
        String valueType = differenceElementHeatmapVO.getValueType();

        List<String> metadataList;
        List<String> geneList;

        if (StringUtil.isEqual(metadata, "cell_type")) {
            List<SampleCellType> sampleCellTypeList = listSampleCellTypeData(sampleId);
            metadataList = sampleCellTypeList.stream().map(SampleCellType::getCellType).toList();

            geneList = differenceGeneChunkMapper.selectGeneBySampleIdWithTop(sampleId, log2FoldChange, topCount);
        } else {
            LambdaQueryWrapper<SampleTimeSexDrug> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SampleTimeSexDrug::getSampleId, sampleId);
            queryWrapper.eq(SampleTimeSexDrug::getType, metadata);
            List<SampleTimeSexDrug> sampleTimeSexDrugList = sampleTimeSexDrugMapper.selectList(queryWrapper);
            metadataList = sampleTimeSexDrugList.stream().map(SampleTimeSexDrug::getTypeValue).toList();

            geneList = timeSexDrugDifferenceGeneMapper.selectGeneBySampleIdWithTop(sampleId, metadata, log2FoldChange, topCount);
        }

        String genes = differenceElementHeatmapVO.getNames();

        if (StringUtil.isNotEmpty(genes)) {
            String[] geneArray = genes.split(", *");
            for (String gene : geneArray) {
                if (StringUtil.isNotContain(gene, geneList)) {
                    geneList.add(gene);
                }
            }
        }

        if (ListUtil.isEmpty(geneList)) {
            return CanvasXpressHeatMapData.<Double>builder().xLabelList(metadataList).build();
        }

        List<List<Double>> metadataValueList = Lists.newArrayListWithCapacity(metadataList.size());
        for (int i = 0; i < metadataList.size(); i++) {
            metadataValueList.add(new ArrayList<>(Collections.nCopies(geneList.size(), 0.0)));
        }

        Double score;

        if (StringUtil.isEqual(metadata, "cell_type")) {
            List<DifferenceGeneChunk> differenceGeneChunkList = differenceGeneChunkMapper.selectBySampleIdAndCellTypeAndGeneListWithTop(sampleId, ALL_DATA_SYMBOL,
                    geneList, 0D, 0D, 0D, 0D, 0);

            for (DifferenceGene differenceGene : differenceGeneChunkList) {
                String cellType = differenceGene.getCellType();
                if (StringUtil.isContain(cellType, metadataList)) {
                    score = StringUtil.isEqual(valueType, "score") ? differenceGene.getScore() : differenceGene.getLog2FoldChange();
                    metadataValueList.get(metadataList.indexOf(cellType)).set(geneList.indexOf(differenceGene.getGene()), score);
                }
            }
        } else {
            List<TimeSexDrugDifferenceGene> timeSexDrugDifferenceGeneList = timeSexDrugDifferenceGeneMapper.selectBySampleIdAndCellTypeAndGeneListWithTop(sampleId, metadata, ALL_DATA_SYMBOL,
                    geneList, 0D, 0D, 0D, 0);

            for (TimeSexDrugDifferenceGene timeSexDrugDifferenceGene : timeSexDrugDifferenceGeneList) {
                String typeValue = timeSexDrugDifferenceGene.getTypeValue();
                if (StringUtil.isContain(typeValue, metadataList)) {
                    score = StringUtil.isEqual(valueType, "score") ? timeSexDrugDifferenceGene.getScore() : timeSexDrugDifferenceGene.getLog2FoldChange();
                    metadataValueList.get(metadataList.indexOf(typeValue)).set(geneList.indexOf(timeSexDrugDifferenceGene.getGene()), score);
                }
            }
        }
        return CanvasXpressHeatMapData.<Double>builder().data(metadataValueList).xLabelList(metadataList).yLabelList(geneList).build();
    }

    /**
     * Retrieves and caches a list of DifferenceTf objects based on sample ID and cell type.
     * This method fetches differential transcription factor data for a specific sample and cell type.
     *
     * @param sampleId the ID of the sample
     * @param cellType Cell type
     * @return a list of DifferenceTf objects
     */
    @Cacheable
    @Override
    public List<? extends DifferenceTf> listDifferenceTfBySampleId(String sampleId, String cellType) {
        return differenceTfChunkMapper.selectBySampleIdAndCellType(sampleId, cellType);
    }

    @Cacheable
    @Override
    public CanvasXpressHeatMapData<Double> getDifferenceTfHeatmapBySampleId(DifferenceElementHeatmapVO differenceElementHeatmapVO) {

        String sampleId = differenceElementHeatmapVO.getSampleId();
        Integer topCount = differenceElementHeatmapVO.getTopCount();
        Double log2FoldChange = differenceElementHeatmapVO.getLog2FoldChange();

        List<SampleCellType> sampleCellTypeList = listSampleCellTypeData(sampleId);

        List<String> cellTypeList = sampleCellTypeList.stream().map(SampleCellType::getCellType).toList();

        List<String> tfList = differenceTfChunkMapper.selectGeneBySampleIdWithTop(sampleId, log2FoldChange, 0D, topCount);

        String tfs = differenceElementHeatmapVO.getNames();

        if (StringUtil.isNotEmpty(tfs)) {
            String[] tfArray = tfs.split(", *");
            for (String tf : tfArray) {
                if (StringUtil.isNotContain(tf, tfList)) {
                    tfList.add(tf);
                }
            }
        }

        List<List<Double>> cellValueList = Lists.newArrayListWithCapacity(cellTypeList.size());
        for (int i = 0; i < cellTypeList.size(); i++) {
            cellValueList.add(new ArrayList<>(Collections.nCopies(tfList.size(), 0.0)));
        }

        List<DifferenceTfChunk> differenceTfChunkList = differenceTfChunkMapper.selectBySampleIdAndCellTypeAndTfList(sampleId, ALL_DATA_SYMBOL,
                tfList, log2FoldChange, 0D, 0D);

        for (DifferenceTf differenceTf : differenceTfChunkList) {
            String cellType = differenceTf.getCellType();
            if (StringUtil.isContain(cellType, cellTypeList)) {
                cellValueList.get(cellTypeList.indexOf(cellType)).set(tfList.indexOf(differenceTf.getTf()), -Math.log10(differenceTf.getPValue()));
            }
        }
        return CanvasXpressHeatMapData.<Double>builder().data(cellValueList).xLabelList(cellTypeList).yLabelList(tfList).build();
    }

    /**
     * Retrieves and caches a list of Magma objects based on trait ID and genome.
     * This method fetches Magma gene data associated with a specific trait and genome.
     *
     * @param traitId the ID of the trait
     * @param genome  the genome version
     * @return a list of Magma objects
     */
    @Cacheable
    @Override
    public List<Magma> listMagmaGeneByTraitId(String traitId, String genome) {
        // Fetch the signal ID using the trait ID
        String signalId = getTraitSignalId(traitId);
        // Select Magma gene data by signal ID, trait ID, and genome
        return magmaMapper.selectByTraitId(signalId, traitId, genome);
    }

    @Cacheable
    @Override
    public List<CiceroSampleTraitGene> listCiceroTraitGeneBySampleIdAndTraitId(String sampleId, String traitId) {
        String signalId = getTrait20SignalId(traitId);
        return ciceroSampleTraitGeneMapper.selectBySampleIdAndTraitId(sampleId, signalId, traitId);
    }

    @SuppressWarnings("SameParameterValue")
    private void addNode(String id,
                         String category,
                         double size,
                         List<EchartsNode> echartsNodeList,
                         boolean isShow,
                         double fontSize) {
        // Create a new Echarts node
        EchartsNode echartsNode = new EchartsNode();
        // Set the id of the node
        echartsNode.setId(id);
        // Set the name of the node
        echartsNode.setName(id);
        // Set the category of the node
        echartsNode.setCategory(category);
        // Set the item style of the node
        echartsNode.setItemStyle(EchartsItemStyle.builder().build());

        String color = "#000000";

        echartsNode.setLabel(EchartsLabel.builder().show(isShow).fontSize(fontSize).color(color).build());

        echartsNode.setEmphasis(EchartsEmphasis.builder().label(EchartsLabel.builder()
                .show(true).fontSize(17D).position("top").color("#000000").build()).build());

        // Set the symbol size of the node
        echartsNode.setSymbolSize(size);
        // Add the node to the list
        echartsNodeList.add(echartsNode);
    }

    @SuppressWarnings("SameParameterValue")
    private void addLink(String source, String target, Double width, List<EchartsLink> echartsLinkList) {
        // Create a new Echarts link
        EchartsLink echartsLink = new EchartsLink();
        // Set the source of the link
        echartsLink.setSource(source);
        // Set the target of the link
        echartsLink.setTarget(target);
        // Set the value of the link
        echartsLink.setValue(width);
        // Set the line style of the link
        echartsLink.setLineStyle(EchartsLineStyle.builder().color("source").width(width).build());
        // Add the link to the list
        echartsLinkList.add(echartsLink);
    }

    @Cacheable
    @Override
    public Map<String, Object> getCiceroAndMagmaOverlapGene(String sampleId, String traitId, String genome) {
        String signalId = getTraitSignalId(traitId);
        List<Magma> magmaList = magmaMapper.selectByTraitId(signalId, traitId, genome);
        List<String> magmaGeneList = magmaList.stream().map(Magma::getGene).distinct().toList();

        String signalId20 = getTrait20SignalId(traitId);
        List<CiceroSampleTraitGene> ciceroSampleTraitGeneList = ciceroSampleTraitGeneMapper.selectBySampleIdAndTraitId(sampleId, signalId20, traitId);
        List<String> ciceroGeneList = ciceroSampleTraitGeneList.stream().map(BaseSnpGene::getGene).distinct().toList();

        List<String> overlapGeneList = magmaGeneList.stream().filter(ciceroGeneList::contains).toList();
        Integer intersectionSize = overlapGeneList.size();
        Integer magmaMinusCiceroSize = Math.toIntExact(magmaGeneList.stream().filter(e -> !ciceroGeneList.contains(e)).count());
        Integer ciceroMinusMagmaSize = Math.toIntExact(ciceroGeneList.stream().filter(e -> !magmaGeneList.contains(e)).count());

        List<MagmaAnno> magmaAnnoList = ListUtil.isEmpty(overlapGeneList) ? NullUtil.listEmpty()
                : magmaAnnoMapper.selectByTraitIdAndGeneList(signalId, traitId, genome, overlapGeneList);

        // Initialize network visualization elements with expected capacity
        List<EchartsLink> linkList = Lists.newArrayListWithExpectedSize(16);
        List<EchartsNode> nodeList = Lists.newArrayListWithExpectedSize(16);

        // Configure chart category taxonomy with 7 predefined types:ml-citation{ref="3" data="citationList"}
        List<EchartsCategories> categoriesList = Lists.newArrayListWithCapacity(4);
        categoriesList.add(EchartsCategories.builder().name("Gene").symbolSize(8D).itemStyle(EchartsItemStyle.builder().color("#e16563").build()).build());
        categoriesList.add(EchartsCategories.builder().name("SNP (Cicero)").symbolSize(8D).itemStyle(EchartsItemStyle.builder().color("#8cc372").build()).build());
        categoriesList.add(EchartsCategories.builder().name("SNP (MAGMA)").symbolSize(8D).itemStyle(EchartsItemStyle.builder().color("#f0bf57").build()).build());
        categoriesList.add(EchartsCategories.builder().name("SNP (Both)").symbolSize(8D).itemStyle(EchartsItemStyle.builder().color("#506bb2").build()).build());

        for (String overlapGene : overlapGeneList) {
            addNode(overlapGene, "Gene", 8D, nodeList, true, 10);
        }

        List<Integer> vennData = Arrays.asList(ciceroMinusMagmaSize, intersectionSize, magmaMinusCiceroSize);

        List<String> magmaRsIdList = magmaAnnoList.stream().map(BaseSnpGene::getRsId).distinct().toList();

        ciceroSampleTraitGeneList = ciceroSampleTraitGeneList.stream().filter(e -> overlapGeneList.contains(e.getGene())).toList();
        List<String> ciceroRsIdList = ciceroSampleTraitGeneList.stream().map(BaseSnpGene::getRsId).distinct().toList();

        for (String magmaRsId : magmaRsIdList) {
            if (ciceroRsIdList.contains(magmaRsId)) {
                addNode(magmaRsId, "SNP (Both)", 8D, nodeList, true, 10);
            } else {
                addNode(magmaRsId, "SNP (MAGMA)", 8D, nodeList, false, 8);
            }
        }

        for (String ciceroRsId : ciceroRsIdList) {
            if (!magmaRsIdList.contains(ciceroRsId)) {
                addNode(ciceroRsId, "SNP (Cicero)", 8D, nodeList, false, 8);
            }
        }

        List<BaseSnpGene> combinedList = new ArrayList<>();
        combinedList.addAll(magmaAnnoList);
        combinedList.addAll(ciceroSampleTraitGeneList);

        combinedList = combinedList.stream()
                .collect(Collectors.toMap(
                        gene -> gene.getGene() + "|" + gene.getRsId(),
                        gene -> gene,
                        (existing, replacement) -> existing
                )).values().stream().toList();

        List<String> overlapSnpList = Lists.newArrayListWithExpectedSize(4);

        for (BaseSnpGene baseSnpGene : combinedList) {
            if (ciceroRsIdList.contains(baseSnpGene.getRsId()) && magmaRsIdList.contains(baseSnpGene.getRsId())) {
                overlapSnpList.add(baseSnpGene.getRsId());
            }
            addLink(baseSnpGene.getRsId(), baseSnpGene.getGene(), 2D, linkList);
        }

        Map<String, Long> rsIdCountMap = combinedList.stream()
                .collect(Collectors.groupingBy(
                        BaseSnpGene::getRsId,
                        Collectors.counting()
                ));

        Map<String, Long> filteredRsIdCountMap = rsIdCountMap.entrySet().stream()
                .filter(entry -> overlapSnpList.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // 根据数量从高到低排序
        List<Map.Entry<String, Long>> sortedEntries = filteredRsIdCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()).toList();

        // 提取排序后的 rsId 和 count 到两个独立的列表
        List<String> rsIdList = sortedEntries.stream()
                .map(Map.Entry::getKey).toList();

        List<Long> countList = sortedEntries.stream()
                .map(Map.Entry::getValue).toList();

        Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
        map.put("venn", vennData);
        map.put("graph", EchartsGraphData.builder().nodes(nodeList).links(linkList).categories(categoriesList).build());
        map.put("snpCount", EchartsData.<String, Long>builder().xData(rsIdList).yData(countList).build());
        return map;
    }

    /**
     * Retrieves and caches a list of Homer objects based on trait ID and genome.
     * This method fetches Homer transcription factor data associated with a specific trait and genome.
     *
     * @param traitId the ID of the trait
     * @param genome  the genome version
     * @return a list of Homer objects
     */
    @Cacheable
    @Override
    public List<Homer> listHomerTfByTraitId(String traitId, String genome) {
        // Fetch the signal ID using the trait ID
        String signalId = getTraitSignalId(traitId);
        // Select Homer TF data by signal ID, trait ID, and genome
        return homerMapper.selectByTraitId(signalId, traitId, genome);
    }

    /**
     * Retrieves and caches a list of SampleCellType objects based on sample ID.
     * This method fetches sample cell type data for a specific sample.
     *
     * @param sampleId the ID of the sample
     * @return a list of SampleCellType objects
     */
    @Cacheable
    @Override
    public List<FieldNumber> listSampleCellTypeTimeSexDrug(String sampleId, String metadata) {

        List<FieldNumber> fieldNumberList;

        if (StringUtil.isEqual(metadata, "cell_type")) {
            // Create a new LambdaQueryWrapper for SampleCellType
            LambdaQueryWrapper<SampleCellType> queryWrapper = new LambdaQueryWrapper<>();
            // Set the equal condition for the sample ID in the query wrapper
            queryWrapper.eq(SampleCellType::getSampleId, sampleId);

            fieldNumberList = sampleCellTypeMapper.selectList(queryWrapper)
                    .stream().map(stringObjectMap -> FieldNumber.builder()
                            .field(String.valueOf(stringObjectMap.getCellType()))
                            .number(stringObjectMap.getCellCount()).build())
                    .toList();
        } else {
            // Create a new LambdaQueryWrapper for SampleCellType
            LambdaQueryWrapper<SampleTimeSexDrug> queryWrapper = new LambdaQueryWrapper<>();
            // Set the equal condition for the sample ID in the query wrapper
            queryWrapper.eq(SampleTimeSexDrug::getSampleId, sampleId);
            queryWrapper.eq(SampleTimeSexDrug::getType, metadata);

            fieldNumberList = sampleTimeSexDrugMapper.selectList(queryWrapper)
                    .stream().map(stringObjectMap -> FieldNumber.builder()
                            .field(String.valueOf(stringObjectMap.getTypeValue()))
                            .number(stringObjectMap.getTypeCount()).build())
                    .toList();
        }

        return fieldNumberList;
    }

    @Cacheable
    @Override
    public List<ChromVarDifferenceTf> listChromvarDifferenceTfBySampleId(String sampleId, String cellType) {
        LambdaQueryWrapper<ChromVarDifferenceTf> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChromVarDifferenceTf::getSampleId, sampleId);
        if (StringUtil.isNotEqual(cellType, ALL_DATA_SYMBOL)) {
            queryWrapper.eq(ChromVarDifferenceTf::getCellType, cellType);
        }
        return chromVarDifferenceTfMapper.selectList(queryWrapper);
    }

    @Cacheable
    @Override
    public PageResult<GimmeSampleTraitTf> listGimmeTfByTraitId(String sampleId, String traitId, Page page) {
        String signalId = getTraitSignalId(traitId);
        return PageResultUtil.format(page, () -> gimmeSampleTraitTfMapper.selectBySampleIdAndTraitId(sampleId, signalId, traitId, page));
    }

    @Cacheable
    @Override
    public PageResult<Mpra> listMpraByTraitId(String traitId, String genome, Page page) {
        String signalId = getTraitSignalId(traitId);
        return PageResultUtil.format(page, () -> mpraMapper.selectByTraitIdAndGenome(traitId, signalId, genome, page));
    }

    @Cacheable
    @Override
    public PageResult<Eqtl> listEqtlByTraitId(String traitId, String genome, String chr, Page page) {
        String signalId = getTraitSignalId(traitId);
        return PageResultUtil.format(page, () -> eqtlMapper.selectByTraitIdAndChrPosition(Collections.singletonList(chr), traitId, signalId, genome, page));
    }

    @Cacheable
    @Override
    public PageResult<Interaction> listInteractionByTraitId(String traitId, String genome, Page page) {
        String signalId = getTraitSignalId(traitId);
        return PageResultUtil.format(page, () -> interactionMapper.selectByTraitId(traitId, signalId, genome, page));
    }

    @Cacheable
    @Override
    public List<TrsDistributionScore> listKlScoreDataByTraitId(String traitId) {
        LambdaQueryWrapper<TrsDistributionScore> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TrsDistributionScore::getTraitId, traitId);
        return trsDistributionScoreMapper.selectList(queryWrapper);
    }

    @Cacheable
    @Override
    public List<TrsDistributionScore> listKlScoreDataBySampleId(String sampleId) {
        LambdaQueryWrapper<TrsDistributionScore> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TrsDistributionScore::getSampleId, sampleId);
        return trsDistributionScoreMapper.selectList(queryWrapper);
    }

    @Cacheable
    @Override
    public Double getKlScoreData(String sampleId, String traitId) {
        LambdaQueryWrapper<TrsDistributionScore> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TrsDistributionScore::getSampleId, sampleId);
        queryWrapper.eq(TrsDistributionScore::getTraitId, traitId);
        TrsDistributionScore trsDistributionScore = trsDistributionScoreMapper.selectOne(queryWrapper);
        return trsDistributionScore.getKlScore();
    }
}
