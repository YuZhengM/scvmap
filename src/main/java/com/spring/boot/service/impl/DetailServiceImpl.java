package com.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.spring.boot.config.bean.ExecLinux;
import com.spring.boot.config.bean.Path;
import com.spring.boot.mapper.*;
import com.spring.boot.pojo.*;
import com.spring.boot.pojo.vo.DifferenceElementHeatmapVO;
import com.spring.boot.pojo.vo.SampleTraitInfo;
import com.spring.boot.service.DetailService;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.vo.FieldNumber;
import com.spring.boot.util.model.vo.canvasXpress.CanvasXpressHeatMapData;
import com.spring.boot.util.model.vo.echarts.EchartsPieData;
import com.spring.boot.util.model.vo.echarts.SeriesPieData;
import com.spring.boot.util.model.vo.plotly.PlotlyClusterData;
import com.spring.boot.util.model.vo.plotly.PlotlyData;
import com.spring.boot.util.util.NumberUtil;
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
                             TraitEnrichMapper traitEnrichMapper, DifferenceGeneChunkMapper differenceGeneChunkMapper, DifferenceTfChunkMapper differenceTfChunkMapper) {
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
    public Trait getTraitData(String traitId) {
        // Create a new LambdaQueryWrapper for the Trait entity
        LambdaQueryWrapper<Trait> queryWrapper = new LambdaQueryWrapper<>();
        // Set the query condition to match the trait ID
        queryWrapper.eq(Trait::getTraitId, traitId);
        // Execute the query and retrieve the Trait object
        Trait trait = traitMapper.selectOne(queryWrapper);
        // Fetch the source data for the trait
        Source source = getTraitSourceData(trait.getSourceId());
        // Set the source on the trait object
        trait.setSource(source);
        // Return the trait object with the source data
        return trait;
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
    public EchartsPieData<String, String> getCellTypeCount(String sampleId) {
        // Retrieve the list of SampleCellType objects for the given sample ID
        List<SampleCellType> sampleCellTypeList = listSampleCellTypeData(sampleId);
        // Convert the list to Echarts pie chart data format
        return getCellTypeCountEchartsData(sampleCellTypeList);
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
    public EchartsPieData<String, String> getTraitChrCountData(String traitId, String genome) {
        // Create a new LambdaQueryWrapper for the TraitChrCount entity
        LambdaQueryWrapper<TraitChrCount> queryWrapper = new LambdaQueryWrapper<>();
        // Set the query condition to match the trait ID
        queryWrapper.eq(TraitChrCount::getFTraitId, traitId);
        // Set the query condition to match the genome version
        queryWrapper.eq(TraitChrCount::getFGenome, genome);
        // Execute the query and retrieve the list of TraitChrCount objects
        List<TraitChrCount> traitChrCountList = traitChrCountMapper.selectList(queryWrapper);
        // Map the list to FieldNumber objects for Echarts data format
        List<FieldNumber> fieldNumberList = traitChrCountList.stream()
                .map(traitChrCount -> FieldNumber.builder()
                        .field(traitChrCount.getFChr())
                        .number(traitChrCount.getFCount())
                        .build())
                .toList();
        // Convert the list to Echarts pie chart data format
        return getChrCountEchartsData(fieldNumberList);
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
    public SampleTraitInfo<SampleEnrichSampleId> listTraitBySampleId(String sampleId, String method) {
        // Define the label for the pie chart
        String label = "traits";
        // Retrieve the list of TraitSample objects for the given sample ID and method
        List<SampleEnrichSampleId> traitEnrichList = sampleEnrichSampleIdMapper.selectBySampleIdAndMethod(sampleId, method);
        // Calculate the number of overlapping samples
        int overlap_count = traitEnrichList.size();
        // Calculate the number of non-overlapping samples
        int no_overlap_count = 15805 - overlap_count;
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
    public SampleTraitInfo<TraitEnrich> listSampleInfoByTraitId(String traitId, String method) {
        // Define the label for the pie chart
        String label = "samples";
        // Retrieve the list of TraitSample objects for the given trait ID and method
        String signalId = getTraitSignalId(traitId);
        List<TraitEnrich> traitSampleList = traitEnrichMapper.selectByTraitIdAndMethod(traitId, method, signalId);
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
    public PlotlyClusterData<Double, Double> listClusterCoordinate(String sampleId, Double cellRate) {
        // Retrieve the list of SampleCellType objects for the given sample ID
        List<SampleCellType> sampleCellTypeList = listSampleCellTypeData(sampleId);
        // Initialize a list to hold the Plotly data for each cell type
        List<PlotlyData<Double, Double>> plotlyDataList = Lists.newArrayListWithCapacity(sampleCellTypeList.size());
        // Declare lists to hold x-coordinates, y-coordinates, and text labels
        List<Double> xList;
        List<Double> yList;
        List<String> textList;
        // Iterate over each SampleCellType to process clustering data
        for (SampleCellType sampleCellType : sampleCellTypeList) {
            // Determine the number of cells to include based on the cell rate
            int cellNumber = getRandomCellNumber(sampleCellType.getCellCount(), cellRate);
            // Create a query wrapper to select SampleCell objects
            LambdaQueryWrapper<SampleCell> queryWrapper = new LambdaQueryWrapper<>();
            // Set query conditions: match sample ID and cell type, and limit by cell type index
            queryWrapper.eq(SampleCell::getSampleId, sampleId)
                    .eq(SampleCell::getCellType, sampleCellType.getCellType())
                    .lt(SampleCell::getCellTypeIndex, cellNumber);
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
                    .name(sampleCellType.getCellType())
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
    public PlotlyClusterData<Double, Double> listTraitClusterCoordinate(String sampleId, String traitId, String method, Double cellRate) throws IOException {
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
        String trs_file = workPath + "/trs/" + sample.getLabel() + "/chunk/" + sample.getLabel() + "_" + signalId + "_trs_" + method + ".h5ad";
        // Construct the file path for the Python script
        String python_file = workPath + "/python/get_method_trait.py";
        // Construct the command to execute the Python script
        String exec = "python3 " + python_file + " " + trs_file + " " + traitId + " " + cellRate;
        // Execute the Python command and retrieve the results
        List<String> results = execLinux.execCommand(exec);
        // Process the Python results to extract barcodes, cell types, UMAP coordinates, and values
        List<String> barcodesList = listStringByPythonResult(results.get(0));
        List<String> cellTypeList = listStringByPythonResult(results.get(1));
        List<Double> umap1s = listDoubleByPythonResult(results.get(2));
        List<Double> umap2s = listDoubleByPythonResult(results.get(3));
        List<Double> values = listDoubleByPythonResult(results.get(4));
        // Initialize a list to hold the Plotly data for the trait
        List<PlotlyData<Double, Double>> plotlyDataList = Lists.newArrayListWithCapacity(1);
        // Create text labels by combining cell types and barcodes
        List<String> textList = IntStream.range(0, barcodesList.size())
                .mapToObj(i -> cellTypeList.get(i) + "-" + barcodesList.get(i))
                .collect(Collectors.toList());
        // Build the Plotly data object for the trait and add it to the list
        plotlyDataList.add(PlotlyData.<Double, Double>builder()
                .x(umap1s)
                .y(umap2s)
                .color(values)
                .name(trait.getTraitAbbr())
                .text(textList)
                .extraText(cellTypeList)
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
    public PageResult<VariantInfo> listTraitInfoData(String traitId, String genome, Page page) {
        // Fetch the trait signal ID using the trait ID
        String signalId = getTraitSignalId(traitId);
        // Select variant info data by trait ID, trait signal ID, and genome
        return PageResultUtil.format(page, () -> variantInfoMapper.selectByTraitId(traitId, signalId, genome, page));
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
    public PageResult<? extends DifferenceGene> listDifferenceGeneBySampleId(String sampleId, String cellType, Page page) {
        // Select differential gene data by sample ID and cell type
        return PageResultUtil.format(page, () -> differenceGeneChunkMapper.selectBySampleIdAndCellType(sampleId, cellType, page));
    }

    @Cacheable
    @Override
    public CanvasXpressHeatMapData<Double> getDifferenceGeneHeatmapBySampleId(DifferenceElementHeatmapVO differenceElementHeatmapVO) {

        String sampleId = differenceElementHeatmapVO.getSampleId();
        Integer topCount = differenceElementHeatmapVO.getTopCount();
        Double log2FoldChange = differenceElementHeatmapVO.getLog2FoldChange();

        List<SampleCellType> sampleCellTypeList = listSampleCellTypeData(sampleId);

        List<String> cellTypeList = sampleCellTypeList.stream().map(SampleCellType::getCellType).toList();

        List<String> geneList = differenceGeneChunkMapper.selectGeneBySampleIdWithTop(sampleId, log2FoldChange, topCount);

        String genes = differenceElementHeatmapVO.getNames();

        if (StringUtil.isNotEmpty(genes)) {
            String[] geneArray = genes.split(", *");
            for (String gene : geneArray) {
                if (StringUtil.isNotContain(gene, geneList)) {
                    geneList.add(gene);
                }
            }
        }

        List<List<Double>> cellValueList = Lists.newArrayListWithCapacity(cellTypeList.size());
        for (int i = 0; i < cellTypeList.size(); i++) {
            cellValueList.add(new ArrayList<>(Collections.nCopies(geneList.size(), 0.0)));
        }

        List<DifferenceGeneChunk> differenceGeneChunkList = differenceGeneChunkMapper.selectBySampleIdAndCellTypeAndGeneListWithTop(sampleId, ALL_DATA_SYMBOL,
                geneList, 0D, 0D, 0D, 0);

        for (DifferenceGene differenceGene : differenceGeneChunkList) {
            String cellType = differenceGene.getCellType();
            if (StringUtil.isContain(cellType, cellTypeList)) {
                cellValueList.get(cellTypeList.indexOf(cellType)).set(geneList.indexOf(differenceGene.getGene()), differenceGene.getScore());
            }
        }

        return CanvasXpressHeatMapData.<Double>builder().data(cellValueList).xLabelList(cellTypeList).yLabelList(geneList).build();
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
    public List<SampleCellType> listSampleCellType(String sampleId) {
        // Create a new LambdaQueryWrapper for SampleCellType
        LambdaQueryWrapper<SampleCellType> queryWrapper = new LambdaQueryWrapper<>();
        // Set the equal condition for the sample ID in the query wrapper
        queryWrapper.eq(SampleCellType::getSampleId, sampleId);
        // Execute the select query and return the list of SampleCellType objects
        return sampleCellTypeMapper.selectList(queryWrapper);
    }
}
