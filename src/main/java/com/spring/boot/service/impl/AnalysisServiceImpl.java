package com.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.spring.boot.config.bean.ExecLinux;
import com.spring.boot.config.bean.Path;
import com.spring.boot.mapper.*;
import com.spring.boot.pojo.*;
import com.spring.boot.pojo.vo.*;
import com.spring.boot.service.AnalysisService;
import com.spring.boot.util.constant.SystemException;
import com.spring.boot.util.exception.RunException;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import com.spring.boot.util.model.vo.canvasXpress.CanvasXpressHeatMapData;
import com.spring.boot.util.model.vo.echarts.*;
import com.spring.boot.util.util.*;
import com.spring.boot.util.util.callback.OneCallback;
import com.spring.boot.util.util.callback.TwoCallback;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static com.spring.boot.util.constant.ApplicationConstant.ALL_DATA_SYMBOL;
import static com.spring.boot.util.constant.ApplicationConstant.DEFAULT_METHOD;
import static com.spring.boot.util.util.ApplicationUtil.*;
import static com.spring.boot.util.util.number.MathUtil.generateRandomCoordinates;

/**
 * Service implementation for analysis operations.
 * This class provides the business logic for analysis related functionalities.
 *
 * @author Zhengmin Yu
 */
@CacheConfig(cacheNames = "analysis", keyGenerator = "cacheKeyGenerator")
@Service
public class AnalysisServiceImpl implements AnalysisService {

    private static final Log log = LogFactory.getLog(FileServiceImpl.class);

    private TraitMapper traitMapper;
    private SampleMapper sampleMapper;
    private MagmaMapper magmaMapper;
    private HomerMapper homerMapper;
    private MagmaAnnoMapper magmaAnnoMapper;
    private GeneEnrichmentMapper geneEnrichmentMapper;
    private TraitGeneEnrichmentMapper traitGeneEnrichmentMapper;
    private TraitSampleMapper traitSampleMapper;
    private ExecLinux execLinux;
    private Path path;
    private SampleGeneChunkMapper sampleGeneChunkMapper;
    private TraitGeneChunkMapper traitGeneChunkMapper;
    private DifferenceGeneChunkMapper differenceGeneChunkMapper;
    private SampleTfChunkMapper sampleTfChunkMapper;
    private DifferenceTfChunkMapper differenceTfChunkMapper;
    private TraitTfChunkMapper traitTfChunkMapper;
    private CiceroSampleTraitGeneMapper ciceroSampleTraitGeneMapper;
    private CiceroTraitGeneMapper ciceroTraitGeneMapper;
    private CiceroSampleGeneMapper ciceroSampleGeneMapper;
    private ChromVarDifferenceTfMapper chromVarDifferenceTfMapper;
    private GimmeSampleTraitTfMapper gimmeSampleTraitTfMapper;
    private TfGeneSampleMapper tfGeneSampleMapper;
    private GimmeTraitTfMapper gimmeTraitTfMapper;
    private TimeSexDrugDifferenceGeneMapper timeSexDrugDifferenceGeneMapper;

    /**
     * Default constructor for AnalysisServiceImpl.
     */
    public AnalysisServiceImpl() {
    }

    /**
     * Constructor with all dependencies injected.
     *
     * @param traitMapper               Mapper for trait operations.
     * @param sampleMapper              Mapper for sample operations.
     * @param magmaMapper               Mapper for MAGMA operations.
     * @param homerMapper               Mapper for HOMER operations.
     * @param magmaAnnoMapper           Mapper for MAGMA annotation operations.
     * @param geneEnrichmentMapper      Mapper for gene enrichment operations.
     * @param traitGeneEnrichmentMapper Mapper for trait gene enrichment operations.
     * @param traitSampleMapper         Mapper for trait sample operations.
     * @param execLinux                 Utility for executing Linux commands.
     * @param path                      The path for storing execution files, generated files, etc.
     */
    @Autowired
    public AnalysisServiceImpl(TraitMapper traitMapper,
                               SampleMapper sampleMapper,
                               MagmaMapper magmaMapper,
                               HomerMapper homerMapper,
                               MagmaAnnoMapper magmaAnnoMapper,
                               GeneEnrichmentMapper geneEnrichmentMapper,
                               TraitGeneEnrichmentMapper traitGeneEnrichmentMapper,
                               TraitSampleMapper traitSampleMapper,
                               ExecLinux execLinux,
                               Path path,
                               SampleGeneChunkMapper sampleGeneChunkMapper,
                               TraitGeneChunkMapper traitGeneChunkMapper,
                               DifferenceGeneChunkMapper differenceGeneChunkMapper,
                               SampleTfChunkMapper sampleTfChunkMapper,
                               DifferenceTfChunkMapper differenceTfChunkMapper,
                               TraitTfChunkMapper traitTfChunkMapper,
                               CiceroSampleTraitGeneMapper ciceroSampleTraitGeneMapper,
                               CiceroTraitGeneMapper ciceroTraitGeneMapper,
                               CiceroSampleGeneMapper ciceroSampleGeneMapper,
                               ChromVarDifferenceTfMapper chromVarDifferenceTfMapper,
                               GimmeSampleTraitTfMapper gimmeSampleTraitTfMapper,
                               TfGeneSampleMapper tfGeneSampleMapper, GimmeTraitTfMapper gimmeTraitTfMapper, TimeSexDrugDifferenceGeneMapper timeSexDrugDifferenceGeneMapper) {
        this.traitMapper = traitMapper;
        this.sampleMapper = sampleMapper;
        this.magmaMapper = magmaMapper;
        this.homerMapper = homerMapper;
        this.magmaAnnoMapper = magmaAnnoMapper;
        this.geneEnrichmentMapper = geneEnrichmentMapper;
        this.traitGeneEnrichmentMapper = traitGeneEnrichmentMapper;
        this.traitSampleMapper = traitSampleMapper;
        this.execLinux = execLinux;
        this.path = path;
        this.sampleGeneChunkMapper = sampleGeneChunkMapper;
        this.traitGeneChunkMapper = traitGeneChunkMapper;
        this.differenceGeneChunkMapper = differenceGeneChunkMapper;
        this.sampleTfChunkMapper = sampleTfChunkMapper;
        this.differenceTfChunkMapper = differenceTfChunkMapper;
        this.traitTfChunkMapper = traitTfChunkMapper;
        this.ciceroSampleTraitGeneMapper = ciceroSampleTraitGeneMapper;
        this.ciceroTraitGeneMapper = ciceroTraitGeneMapper;
        this.ciceroSampleGeneMapper = ciceroSampleGeneMapper;
        this.chromVarDifferenceTfMapper = chromVarDifferenceTfMapper;
        this.gimmeSampleTraitTfMapper = gimmeSampleTraitTfMapper;
        this.tfGeneSampleMapper = tfGeneSampleMapper;
        this.gimmeTraitTfMapper = gimmeTraitTfMapper;
        this.timeSexDrugDifferenceGeneMapper = timeSexDrugDifferenceGeneMapper;
    }

    /**
     * Retrieves elements based on the input parameters.
     * If analysisIsFile is 1, it reads from a file, otherwise it processes the content string.
     *
     * @param analysisIsFile flag to determine if the input is a file
     * @param content        the content string to be processed if not a file
     * @param userPath       the path to the user's directory
     * @param fileId         the identifier for the file
     * @return a list of strings representing the elements
     */
    private static List<String> getElement(Integer analysisIsFile, String content, String userPath, String fileId) {
        // Determine if the input is a file
        boolean isFile = analysisIsFile == 1;
        // Initialize the list to hold the elements
        List<String> elementList;

        // Check if the input is a file
        if (isFile) {
            // Read the elements from the file
            elementList = FileUtil.read(userPath + fileId);
        } else {
            // Split the content string into a list of elements
            elementList = Arrays.stream(content.split("(\r\n|\n)")).toList();
        }

        return elementList;
    }

    /**
     * Adds a node to the list of Echarts nodes.
     *
     * @param id              the identifier for the node
     * @param category        the category of the node
     * @param size            the size of the node
     * @param echartsNodeList the list to add the node to
     * @param doubles         the array containing the x and y coordinates
     */
    private void addNode(String id,
                         String name,
                         String category,
                         double size,
                         List<EchartsNode> echartsNodeList,
                         double[] doubles,
                         boolean isShow,
                         double fontSize,
                         String position) {
        // Create a new Echarts node
        EchartsNode echartsNode = new EchartsNode();
        // Set the id of the node
        echartsNode.setId(id);
        // Set the name of the node
        echartsNode.setName(name);
        // Set the x coordinate of the node
        echartsNode.setX(doubles[0]);
        // Set the y coordinate of the node
        echartsNode.setY(doubles[1]);
        // Set the category of the node
        echartsNode.setCategory(category);
        // Set the item style of the node
        echartsNode.setItemStyle(EchartsItemStyle.builder().build());

        String color = "#000000";

        echartsNode.setLabel(EchartsLabel.builder().show(isShow).fontSize(fontSize).position(position).color(color).build());

        echartsNode.setEmphasis(EchartsEmphasis.builder().label(EchartsLabel.builder()
                .show(true).fontSize(17D).position("top").color("#000000").build()).build());

        // Set the symbol size of the node
        echartsNode.setSymbolSize(size);
        // Add the node to the list
        echartsNodeList.add(echartsNode);
    }

    private void addNode(String id,
                         String category,
                         double size,
                         List<EchartsNode> echartsNodeList,
                         double[] doubles,
                         boolean isShow,
                         double fontSize,
                         String position) {
        addNode(id, id, category, size, echartsNodeList, doubles, isShow, fontSize, position);
    }

    /**
     * Adds a link to the list of Echarts links.
     *
     * @param source          the source node of the link
     * @param target          the target node of the link
     * @param width           the width of the link
     * @param echartsLinkList the list to add the link to
     */
    private void addLink(String source, String target, Double width, List<EchartsLink> echartsLinkList, boolean isCore) {
        // Create a new Echarts link
        EchartsLink echartsLink = new EchartsLink();
        // Set the source of the link
        echartsLink.setSource(source);
        // Set the target of the link
        echartsLink.setTarget(target);
        // Set the value of the link
        echartsLink.setValue(width);
        // Set the line style of the link
        echartsLink.setLineStyle(EchartsLineStyle.builder().color(isCore ? "source" : "#BBBBBB").width(width).build());
        // Add the link to the list
        echartsLinkList.add(echartsLink);
    }

    /**
     * Sets the cell type in the graph if it's not already present.
     * Adds a new node for the cell type and creates a link from the sample to the cell type.
     *
     * @param sampleId        the ID of the sample
     * @param echartsLinkList the list of links in the Echarts graph
     * @param echartsNodeList the list of nodes in the Echarts graph
     * @param step            the step size for positioning new nodes
     * @param cellTypeList    the list of existing cell types
     * @param cellType        the cell type to be added if not present
     */
    private void setCellTypeInGraph(String sampleId, List<EchartsLink> echartsLinkList, List<EchartsNode> echartsNodeList,
                                    double x1, double step, List<String> cellTypeList, String cellType, String category) {
        // Check if the cellType2 is not in the cellTypeList
        if (StringUtil.isNotContain(cellType, cellTypeList)) {
            // Add the new cell type to the list
            cellTypeList.add(cellType);
            // Generate random coordinates for the new node
            double[] doubles = generateRandomCoordinates(x1 * step, -3 * step, 8 * step, 3 * step);
            // Add a new node for the cell type
            addNode(cellType, category, 20, echartsNodeList, doubles, true, 8, "inside");
            // Add a link from the sample to the new cell type node
            addLink(cellType, sampleId, 1D, echartsLinkList, true);
        }
    }

    private void setCellTypeInGraph(String sampleId, List<EchartsLink> echartsLinkList, List<EchartsNode> echartsNodeList,
                                    double x1, double step, List<String> cellTypeList, String cellType) {
        setCellTypeInGraph(sampleId, echartsLinkList, echartsNodeList, x1, step, cellTypeList, cellType, "Cell type");
    }

    /**
     * Retrieves a list of Trait objects that meet specific criteria.
     * The results of this method are cached to improve performance on subsequent calls.
     *
     * @return a List of Trait objects with filtered attributes
     */
    @Cacheable
    @Override
    public List<Trait> listTrait() {
        // Initialize a new LambdaQueryWrapper for the Trait class to construct the query
        LambdaQueryWrapper<Trait> queryWrapper = new LambdaQueryWrapper<>();
        // Select specific fields from the Trait class to be included in the result
        queryWrapper.select(Trait::getTraitId, Trait::getTrait, Trait::getTraitAbbr, Trait::getSourceId, Trait::getSourceName);
        // Add an equality condition to the query to only include Trait objects where the 'filter' field is equal to 1
        queryWrapper.eq(Trait::getFilter, 1);
        // Execute the query using the traitMapper and return the list of Trait objects that match the criteria
        return traitMapper.selectList(queryWrapper);
    }

    /**
     * Retrieves a list of TraitSample objects associated with a specific sample ID and method.
     * The results of this method are cached to enhance performance for repeated calls with the same parameters.
     *
     * @param sampleId The ID of the sample for which to retrieve the TraitSample list.
     * @return a List of TraitSample objects linked to the given sample ID and method.
     */
    @Cacheable
    @Override
    public List<TraitSample> listTraitBySampleId(String sampleId) {
        // Fetch and return the list of TraitSample objects based on the sample ID and method
        return traitSampleMapper.selectBySampleIdAndMethod(sampleId, DEFAULT_METHOD.toLowerCase());
    }

    /**
     * Retrieves a list of TraitSample objects associated with a specific sample ID and a list of trait IDs.
     * The results of this method are cached to improve performance for repeated calls with the same parameters.
     *
     * @param sampleId    The ID of the sample for which to retrieve the TraitSample list.
     * @param traitIdList The list of trait IDs to filter the TraitSample objects.
     * @return a List of TraitSample objects linked to the given sample ID and trait ID list.
     */
    @Cacheable
    @Override
    public List<TraitSample> listTraitBySampleIdAndTraitIdList(String sampleId, List<String> traitIdList) {

        if (ListUtil.isEmpty(traitIdList)) {
            return NullUtil.listEmpty();
        }

        // Fetch and return the list of TraitSample objects based on the sample ID, method, and trait ID list
        return traitSampleMapper.selectByTraitIdListAndMethod(sampleId, DEFAULT_METHOD.toLowerCase(), traitIdList);
    }

    /**
     * Retrieves gene enrichment data based on the provided GeneEnrichmentVO.
     * The results of this method are cached to enhance performance for repeated calls with the same parameters.
     *
     * @param geneEnrichmentVO The value object containing parameters for the gene enrichment query.
     * @return a GeneEnrichmentResultVO containing the result of the gene enrichment data query.
     */
    @Cacheable
    @Override
    public GeneEnrichmentResultVO listGeneEnrichmentData(GeneEnrichmentVO geneEnrichmentVO) {
        // Extract sampleId from the geneEnrichmentVO
        String sampleId = geneEnrichmentVO.getSampleId();
        // Extract geneSet from the geneEnrichmentVO
        String geneSet = geneEnrichmentVO.getGeneSet();
        // Extract value from the geneEnrichmentVO
        Double value = geneEnrichmentVO.getValue();

        // Check if the sampleId is not empty
        if (StringUtil.isNotEmpty(sampleId)) {
            checkSampleId(sampleId);
            // Extract cellType from the geneEnrichmentVO
            String cellType = geneEnrichmentVO.getCellType();
            // Fetch the list of GeneEnrichment objects based on sampleId, geneSet, cellType, and value
            List<GeneEnrichment> geneEnrichmentList = geneEnrichmentMapper.selectBySampleIdAndCellType(sampleId, geneSet, cellType, value);
            // Build and return the GeneEnrichmentResultVO with the fetched list
            return GeneEnrichmentResultVO.builder().geneEnrichmentList(geneEnrichmentList).build();
        } else {
            // Extract traitId from the geneEnrichmentVO
            String traitId = geneEnrichmentVO.getTraitId();
            checkTraitId(traitId);
            // Get the signalId associated with the traitId
            String signalId = getTraitSignalId(traitId);
            // Fetch the list of TraitGeneEnrichment objects based on signalId, traitId, geneSet, value, and genome
            List<TraitGeneEnrichment> traitGeneEnrichmentList = traitGeneEnrichmentMapper.selectByTraitId(signalId, traitId, geneSet, value, geneEnrichmentVO.getGenome());
            // Build and return the GeneEnrichmentResultVO with the fetched list
            return GeneEnrichmentResultVO.builder().traitGeneEnrichmentList(traitGeneEnrichmentList).build();
        }
    }

    /**
     * Retrieves a list of Sample objects associated with a specific gene.
     * The results of this method are cached to improve performance for repeated calls with the same gene.
     *
     * @param gene The gene for which to retrieve the Sample list.
     * @return a List of Sample objects linked to the given gene.
     */
    @Cacheable
    @Override
    public List<Sample> listSampleDataByGene(String gene) {
        int signalId = getElementSignalId(gene);
        List<String> sampleIdList = sampleGeneChunkMapper.selectSampleIdByGene(gene, signalId);
        if (ListUtil.isNotEmpty(sampleIdList)) {
            // Create a query wrapper to filter Sample objects by the fetched sample IDs
            LambdaQueryWrapper<Sample> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Sample::getSampleId, sampleIdList);
            return sampleMapper.selectList(queryWrapper);
        }
        return NullUtil.listEmpty();
    }

    /**
     * Retrieves a list of Sample objects associated with a specific transcription factor (tf).
     * The results of this method are cached to improve performance for repeated calls with the same tf.
     *
     * @param tf The transcription factor for which to retrieve the Sample list.
     * @return a List of Sample objects linked to the given transcription factor.
     */
    @Cacheable
    @Override
    public List<Sample> listSampleDataByTf(String tf) {
        tf = getTfName(tf);
        int signalId = getElementSignalId(tf);
        List<String> sampleIdList = sampleTfChunkMapper.selectSampleIdByTf(tf, signalId);
        if (ListUtil.isEmpty(sampleIdList)) {
            return NullUtil.listEmpty();
        }
        // Create a query wrapper to filter Sample objects by the extracted sample IDs
        LambdaQueryWrapper<Sample> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Sample::getSampleId, sampleIdList);
        // Execute the query and return the list of Sample objects
        return sampleMapper.selectList(queryWrapper);
    }

    private <T extends AnalysisElementResultVO> T getAnalysisElementResultVO(AnalysisElementVO analysisElementVO,
                                                                             OneCallback<String, Map<String, Object>> sampleCallback,
                                                                             TwoCallback<String, Map<Integer, List<String>>, String> traitCallback,
                                                                             Class<T> clazz) {
        // Retrieve the working path and construct the user-specific path
        String workPath = path.getWorkPath();
        String userPath = workPath + "/user/";

        // Get the list of TFs based on the input parameters
        List<String> elementList = getElement(analysisElementVO.getIsFile(), analysisElementVO.getContent(), userPath, analysisElementVO.getFileId());

        // get sample ID list
        Map<Integer, List<String>> signalIdElementListMap = Maps.newHashMapWithExpectedSize(elementList.size());

        for (String element : elementList) {
            int signalId = getElementSignalId(element);
            signalIdElementListMap.computeIfAbsent(signalId, k -> new ArrayList<>()).add(element);
        }

        Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
        map.put("elementList", elementList);
        map.put("signalIdElementListMap", signalIdElementListMap);
        List<String> sampleIdList = sampleCallback.run(map);

        // Check if the retrieved list is empty and return an empty result if so
        if (ListUtil.isEmpty(sampleIdList)) {
            try {
                return clazz.getConstructor(List.class, List.class).newInstance(List.of(), List.of());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RunException(SystemException.PARSING_ERROR);
            }
        }

        // Create a query wrapper to fetch Sample records based on the Sample IDs from the finished difference TF list
        LambdaQueryWrapper<Sample> sampleWrapper = new LambdaQueryWrapper<>();
        sampleWrapper.in(Sample::getSampleId, sampleIdList).orderByAsc(Sample::getFIndex);
        List<Sample> sampleList = sampleMapper.selectList(sampleWrapper);

        // Retrieve the genome for the first sample ID from the difference TF list
        String sampleId = sampleList.get(0).getSampleId();
        LambdaQueryWrapper<Sample> sampleQueryWrapper = new LambdaQueryWrapper<>();
        sampleQueryWrapper.eq(Sample::getSampleId, sampleId);
        String genome = sampleMapper.selectOne(sampleQueryWrapper).getGenome();

        List<String> traitIdList = traitCallback.run(signalIdElementListMap, genome);
        // Check if the Trait ID list is empty and return an empty result if so
        if (ListUtil.isEmpty(traitIdList)) {
            try {
                return clazz.getConstructor(List.class, List.class).newInstance(sampleList, List.of());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RunException(SystemException.PARSING_ERROR);
            }
        }

        // Create a query wrapper to fetch Trait records based on the Trait IDs from the finished Homer list
        LambdaQueryWrapper<Trait> traitQueryWrapper = new LambdaQueryWrapper<>();
        traitQueryWrapper.in(Trait::getTraitId, traitIdList).orderByAsc(Trait::getTraitIndex);
        List<Trait> traitList = traitMapper.selectList(traitQueryWrapper);

        try {
            return clazz.getConstructor(List.class, List.class).newInstance(sampleList, traitList);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RunException(SystemException.PARSING_ERROR);
        }
    }

    /**
     * Retrieves analysis data for a list of genes based on the provided AnalysisGeneVO.
     * The results of this method are cached to improve performance for repeated calls with the same parameters.
     *
     * @param analysisGeneVO The AnalysisGeneVO containing the parameters for the analysis.
     * @return an AnalysisGeneResultVO containing the analysis results.
     */
    @Cacheable
    @Override
    public AnalysisGeneResultVO listDataByGenes(AnalysisGeneVO analysisGeneVO) {
        return getAnalysisElementResultVO(analysisGeneVO,
                (map) -> {
                    @SuppressWarnings("unchecked")
                    Map<Integer, List<String>> signalIdElementListMap = (Map<Integer, List<String>>) map.get("signalIdElementListMap");
                    List<String> magmaSampleIdList = sampleGeneChunkMapper.selectSampleIdByGeneList(signalIdElementListMap, analysisGeneVO.getLog2FoldChange(), analysisGeneVO.getAdjustedPValue(), analysisGeneVO.getPvalue());
                    List<String> ciceroSampleIdList = ciceroSampleGeneMapper.selectSampleIdByGeneList(signalIdElementListMap, analysisGeneVO.getCoScore());
                    magmaSampleIdList.addAll(ciceroSampleIdList);
                    return magmaSampleIdList.stream().distinct().toList();
                },
                (map, genome) -> {
                    List<String> magmaTraitIdList = traitGeneChunkMapper.selectTraitIdByGeneList(map, genome, analysisGeneVO.getPvalueTrait(), analysisGeneVO.getMin());
                    List<String> ciceroTraitIdList = ciceroTraitGeneMapper.selectTraitIdByGeneList(map, analysisGeneVO.getCoScore());
                    magmaTraitIdList.addAll(ciceroTraitIdList);
                    return magmaTraitIdList.stream().distinct().toList();
                },
                AnalysisGeneResultVO.class);
    }

    /**
     * Retrieves and processes data based on the provided analysis TF parameters.
     * This method is cached to improve performance on subsequent calls with the same parameters.
     *
     * @param analysisTfVO the analysis TF value object containing parameters for the query
     * @return an AnalysisTfResultVO object containing the processed data
     * @throws RunException if an illegal strategy parameter is provided
     */
    @Cacheable
    @Override
    public AnalysisTfResultVO listDataByTfs(AnalysisTfVO analysisTfVO) {
        return getAnalysisElementResultVO(analysisTfVO,
                (map) -> {
                    String strategy = analysisTfVO.getScStrategy();

                    List<String> sampleIdList;

                    if (StringUtil.isEqual(strategy, "snapatac2")) {

                        @SuppressWarnings("unchecked")
                        Map<Integer, List<String>> signalIdElementListMap = (Map<Integer, List<String>>) map.get("signalIdElementListMap");
                        sampleIdList = sampleTfChunkMapper.selectSampleIdByTfList(signalIdElementListMap, analysisTfVO.getLog2FoldChange(), analysisTfVO.getAdjustedPValue(), analysisTfVO.getPvalue());

                    } else if (StringUtil.isEqual(strategy, "chromvar")) {

                        @SuppressWarnings("unchecked")
                        List<String> elementList = (List<String>) map.get("elementList");
                        LambdaQueryWrapper<ChromVarDifferenceTf> queryWrapper = new LambdaQueryWrapper<>();
                        queryWrapper.in(ChromVarDifferenceTf::getTf, elementList);
                        if (analysisTfVO.getMean() > 0) {
                            queryWrapper.ge(ChromVarDifferenceTf::getMean1, analysisTfVO.getMean());
                        }
                        if (analysisTfVO.getPvalue() > 0) {
                            queryWrapper.le(ChromVarDifferenceTf::getPValue, analysisTfVO.getPvalue());
                        }
                        if (analysisTfVO.getAdjustedPValue() > 0) {
                            queryWrapper.le(ChromVarDifferenceTf::getPValueAdjust, analysisTfVO.getAdjustedPValue());
                        }
                        List<ChromVarDifferenceTf> chromVarDifferenceTfList = chromVarDifferenceTfMapper.selectList(queryWrapper);
                        sampleIdList = chromVarDifferenceTfList.stream().map(ChromVarDifferenceTf::getSampleId).distinct().toList();

                    } else {
                        log.error("[getGeneGraphData]: Parameter error {}", analysisTfVO);
                        throw new RunException(SystemException.ILLEGAL_PARAMETER);
                    }

                    return sampleIdList;
                },
                (map, genome) -> {
                    String strategy = analysisTfVO.getTraitStrategy();
                    List<String> traitIdList;

                    if (StringUtil.isEqual(strategy, "gimme")) {
                        traitIdList = gimmeTraitTfMapper.selectByCoScoreAndMeanScore(map, analysisTfVO.getCoScore(), analysisTfVO.getMeanScore());
                    } else if (StringUtil.isEqual(strategy, "homer")) {
                        traitIdList = traitTfChunkMapper.selectTraitIdByTfList(map, genome, analysisTfVO.getPvalueTrait(), analysisTfVO.getQvalueTrait());
                    } else {
                        log.error("[getGeneGraphData]: Parameter error {}", analysisTfVO);
                        throw new RunException(SystemException.ILLEGAL_PARAMETER);
                    }
                    return traitIdList;
                }, AnalysisTfResultVO.class);
    }

    private Map<String, Object> getTraitGeneData(String sampleId,
                                                 String traitId,
                                                 String genome,
                                                 AnalysisGeneVO analysisGeneVO) {

        String signalId20 = getTrait20SignalId(traitId);
        String signalId = getTraitSignalId(traitId);
        String traitGeneStrategy = analysisGeneVO.getStrategy();

        // Gene Information
        List<Magma> magmaList;
        List<? extends BaseSnpGene> baseSnpGeneList;

        List<String> traitGeneList;

        // trait-gene-rsID
        if (StringUtil.isEqual(traitGeneStrategy, "cicero")) {
            baseSnpGeneList = ciceroSampleTraitGeneMapper.selectBySampleIdAndTraitIdAndScore(sampleId, signalId20, traitId, analysisGeneVO.getCoScore());
            traitGeneList = ListUtil.isEmpty(baseSnpGeneList)
                    ? NullUtil.listEmpty()
                    : baseSnpGeneList.stream()
                    .map(BaseSnpGene::getGene)
                    .distinct()
                    .toList();
        } else if (StringUtil.isEqual(traitGeneStrategy, "magma")) {
            magmaList = magmaMapper.selectByTraitIdAndGeneList(signalId, traitId, genome, null,
                    analysisGeneVO.getPvalueTrait(), analysisGeneVO.getMin(), 0);
            traitGeneList = ListUtil.isEmpty(magmaList)
                    ? NullUtil.listEmpty()
                    : magmaList.stream()
                    .map(Magma::getGene)
                    .distinct()
                    .toList();
            baseSnpGeneList = ListUtil.isEmpty(traitGeneList) ? NullUtil.listEmpty()
                    : magmaAnnoMapper.selectByTraitIdAndGeneList(signalId, traitId, genome, traitGeneList);
        } else if (StringUtil.isEqual(traitGeneStrategy, "overlap")) {
            baseSnpGeneList = ciceroSampleTraitGeneMapper.selectBySampleIdAndTraitIdAndScore(sampleId, signalId20, traitId, analysisGeneVO.getCoScore());
            magmaList = magmaMapper.selectByTraitIdAndGeneList(signalId, traitId, genome, null,
                    analysisGeneVO.getPvalueTrait(), analysisGeneVO.getMin(), 0);
            if (ListUtil.isEmpty(baseSnpGeneList) || ListUtil.isEmpty(magmaList)) {
                traitGeneList = NullUtil.listEmpty();
            } else {
                traitGeneList = baseSnpGeneList.stream()
                        .map(BaseSnpGene::getGene)
                        .distinct()
                        .toList();
                List<String> magmaGenelist = magmaList.stream()
                        .map(Magma::getGene)
                        .distinct()
                        .toList();
                traitGeneList = traitGeneList.stream().filter(magmaGenelist::contains).toList();

                List<BaseSnpGene> baseSnpGeneArrayList = Lists.newArrayListWithCapacity(32);
                List<MagmaAnno> magmaAnnoList = ListUtil.isEmpty(traitGeneList) ? NullUtil.listEmpty()
                        : magmaAnnoMapper.selectByTraitIdAndGeneList(signalId, traitId, genome, traitGeneList);
                baseSnpGeneArrayList.addAll(magmaAnnoList);
                baseSnpGeneArrayList.addAll(baseSnpGeneList);
                baseSnpGeneList = baseSnpGeneArrayList;

            }
        } else {
            log.error("[getTraitGeneData]: Parameter error {}", analysisGeneVO);
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }

        baseSnpGeneList = baseSnpGeneList.stream().distinct().toList();

        Map<String, Object> map = Maps.newHashMapWithExpectedSize(3);
        map.put("baseSnpGeneList", baseSnpGeneList);
        map.put("traitGeneList", traitGeneList);
        return map;
    }

    /**
     * Retrieves the gene graph data based on the provided regulation graph data.
     * This method constructs a graph with nodes and links representing genes, traits, and cell types.
     * It uses caching to optimize performance.
     *
     * @param regulationGraphVO the data object containing the parameters for the graph
     * @return the constructed Echarts graph data
     */
    @Override
    public EchartsGraphData getGeneGraphData(RegulationGraphVO regulationGraphVO) {
        // Retrieve top count parameter for visualization limit control:ml-citation{ref="1,2" data="citationList"}
        Integer topCount = regulationGraphVO.getTopCount();

        // Extract unique sample identifier from VO object
        String sampleId = regulationGraphVO.getSampleId();

        // Extract trait/disease identifier from VO object
        String traitId = regulationGraphVO.getTraitId();
        String metadata = regulationGraphVO.getMetadata();

        // Obtain gene list
        AnalysisGeneVO analysisGeneVO = regulationGraphVO.getAnalysisGene();
        List<String> geneList = List.of();

        if (ObjectUtils.isNotEmpty(analysisGeneVO) && analysisGeneVO.getIsFile() != null) {
            // Get the working path and user-specific path
            String workPath = path.getWorkPath();
            String userPath = workPath + "/user/";
            // Determine if the input is from a file
            geneList = getElement(analysisGeneVO.getIsFile(), analysisGeneVO.getContent(), userPath, analysisGeneVO.getFileId());
        }

        // Get cell type classification for biological context
        String metadataValue = regulationGraphVO.getCellType();

        String strategy = analysisGeneVO.getStrategy();

        // Initialize network visualization elements with expected capacity
        List<EchartsLink> echartsLinkList = Lists.newArrayListWithExpectedSize(32);
        List<EchartsNode> echartsNodeList = Lists.newArrayListWithExpectedSize(32);

        // Configure chart category taxonomy with 7 predefined types:ml-citation{ref="3" data="citationList"}
        List<EchartsCategories> categoriesList = Lists.newArrayListWithCapacity(7);
        categoriesList.add(EchartsCategories.builder().name("Trait").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#506bb2").build()).build());
        categoriesList.add(EchartsCategories.builder().name("SNP").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#8cc372").build()).build());

        if (!regulationGraphVO.getIsCore()) {
            if (StringUtil.isEqual(strategy, "cicero")) {
                categoriesList.add(EchartsCategories.builder().name("Gene (Cicero)").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#f0bf57").build()).build());
            } else if (StringUtil.isEqual(strategy, "magma")) {
                categoriesList.add(EchartsCategories.builder().name("Gene (MAGMA)").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#f0bf57").build()).build());
            } else if (StringUtil.isEqual(strategy, "overlap")) {
                categoriesList.add(EchartsCategories.builder().name("Gene (Overlap)").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#f0bf57").build()).build());
            } else {
                log.error("[getGeneGraphData]: Parameter error {}", regulationGraphVO);
                throw new RunException(SystemException.ILLEGAL_PARAMETER);
            }
        }

        categoriesList.add(EchartsCategories.builder().name("Key gene").symbolSize(35D).itemStyle(EchartsItemStyle.builder().color("#e16563").build()).build());

        if (!regulationGraphVO.getIsCore()) {
            categoriesList.add(EchartsCategories.builder().name("Gene (Difference)").itemStyle(EchartsItemStyle.builder().color("#71b7d3").build()).build());
        }

        String metadataLabel = switch (metadata) {
            case "time" -> "Age/day/time";
            case "sex" -> "Sex";
            case "drug" -> "Drug";
            default -> "Cell type";
        };

        categoriesList.add(EchartsCategories.builder().name(metadataLabel).symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#3b9c6e").build()).build());
        categoriesList.add(EchartsCategories.builder().name("scATAC-seq").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#ef8152").build()).build());

        // Define coordinate calculation parameter for node positioning
        double step = 100;

        // Position trait node on left side of visualization canvas
        addNode(traitId, "Trait", 40, echartsNodeList, new double[]{-10 * step, 0D}, true, 17, "top");

        // Position sample node on right side of visualization canvas
        addNode(sampleId, "scATAC-seq", 40, echartsNodeList, new double[]{10 * step, 0D}, true, 17, "top");

        // Construct database query for sample metadata retrieval:ml-citation{ref="3,5" data="citationList"}
        LambdaQueryWrapper<Sample> sampleQueryWrapper = new LambdaQueryWrapper<>();
        // Set WHERE clause condition: sample_id column equals provided identifier
        sampleQueryWrapper.eq(Sample::getSampleId, sampleId);
        // Execute SQL SELECT statement returning single matching record
        Sample sample = sampleMapper.selectOne(sampleQueryWrapper);

        List<? extends DifferenceGene> differenceGeneList;

        if (StringUtil.isEqual(metadata, "cell_type")) {
            differenceGeneList = differenceGeneChunkMapper.selectBySampleIdAndCellTypeAndGeneListWithTop(
                    sampleId, metadataValue, geneList, analysisGeneVO.getScore(), analysisGeneVO.getLog2FoldChange(),
                    analysisGeneVO.getAdjustedPValue(), analysisGeneVO.getPvalue(), topCount);
        } else {
            differenceGeneList = timeSexDrugDifferenceGeneMapper.selectBySampleIdAndCellTypeAndGeneListWithTop(
                    sampleId, metadata, metadataValue, geneList, analysisGeneVO.getLog2FoldChange(),
                    analysisGeneVO.getAdjustedPValue(), analysisGeneVO.getPvalue(), topCount);
        }

        Map<String, Object> traitGeneData = getTraitGeneData(sampleId, traitId, sample.getGenome(), analysisGeneVO);

        @SuppressWarnings("unchecked")
        List<? extends BaseSnpGene> baseSnpGeneList = (List<? extends BaseSnpGene>) traitGeneData.get("baseSnpGeneList");
        @SuppressWarnings("unchecked")
        List<String> traitGeneList = (List<String>) traitGeneData.get("traitGeneList");

        // Ensure that the difference gene list is not null (this assertion is for debugging purposes and might be omitted in production code)
        assert differenceGeneList != null;

        // The sample gene list based on the distinct genes found in the difference gene list
        // If the difference gene list is empty, return an empty list
        List<String> sampleGeneList = ListUtil.isEmpty(differenceGeneList)
                ? NullUtil.listEmpty()
                : differenceGeneList.stream()
                .map(DifferenceGene::getGene)
                .distinct()
                .toList();

        // Iterate over each gene in the sample gene list
        for (String sampleGene : sampleGeneList) {
            // Check if the sample gene is also present in the trait gene list
            if (StringUtil.isContain(sampleGene, traitGeneList)) {
                // Generate random coordinates for nodes representing genes present in both lists
                double[] doubles = regulationGraphVO.getIsCore() ? generateRandomCoordinates(-2 * step, -5 * step, 3.5 * step, 5 * step)
                        : generateRandomCoordinates(-2 * step, -5 * step, 2 * step, 5 * step);
                // Add a node to the echarts node list, categorizing it as "Key gene"
                addNode(sampleGene, "Key gene", 35, echartsNodeList, doubles, !sampleGene.startsWith("ENSG"), 10, "inside");
            } else {
                if (!regulationGraphVO.getIsCore()) {
                    // Generate random coordinates for nodes representing genes present only in the sample list
                    double[] doubles = generateRandomCoordinates(2.5 * step, -3 * step, 5.5 * step, 3 * step);
                    // Add a node to the echarts node list, categorizing it as "Gene (Difference)"
                    addNode(sampleGene, "Gene (Difference)", 20, echartsNodeList, doubles, false, 10, "inside");
                }
            }
        }

        // Iterate over each gene in the trait gene list
        for (String traitGene : traitGeneList) {
            // Check if the trait gene is not present in the sample gene list
            if (StringUtil.isNotContain(traitGene, sampleGeneList) && !regulationGraphVO.getIsCore()) {
                // Generate random coordinates for nodes representing genes present only in the trait list
                double[] doubles = generateRandomCoordinates(-5.5 * step, -3 * step, -2.5 * step, 3 * step);
                // Add a node to the echarts node list, categorizing it as "Gene (MAGMA)"
                addNode(traitGene, "Gene (MAGMA)", 20, echartsNodeList, doubles, false, 10, "inside");
            }
        }
        // Initialize a list to store cell types, with an expected size of 32 to optimize performance
        List<String> cellTypeList = Lists.newArrayListWithExpectedSize(32);

        if (ListUtil.isNotEmpty(differenceGeneList)) {
            // Iterate over each difference gene in the list
            for (DifferenceGene differenceGene : differenceGeneList) {
                // Update the graph with cell type information for the current difference gene
                setCellTypeInGraph(sampleId, echartsLinkList, echartsNodeList, 5, step, cellTypeList, differenceGene.getCellType(), metadataLabel);

                // Check if the gene of the current difference gene is present in both the sample and trait gene lists
                if (StringUtil.isContain(differenceGene.getGene(), sampleGeneList) && StringUtil.isContain(differenceGene.getGene(), traitGeneList)) {
                    // Add a link with a weight of 2D between the cell type and the gene, indicating a stronger connection
                    addLink(differenceGene.getGene(), differenceGene.getCellType(), 2D, echartsLinkList, true);
                } else {
                    if (!regulationGraphVO.getIsCore()) {
                        // Add a link with a weight of 1D between the cell type and the gene, indicating a weaker connection
                        addLink(differenceGene.getGene(), differenceGene.getCellType(), 1D, echartsLinkList, false);
                    }
                }
            }
        }

        // Initialize a list to store rsIDs, with an expected size of 32 to optimize performance
        List<String> rsIdList = Lists.newArrayListWithExpectedSize(32);

        for (BaseSnpGene baseSnpGene : baseSnpGeneList) {
            // Check if the rsID of the current Magma annotation is not already in the rsID list to avoid duplicates
            if (StringUtil.isNotContain(baseSnpGene.getRsId(), rsIdList)) {
                if (regulationGraphVO.getIsCore()) {
                    if (StringUtil.isContain(baseSnpGene.getGene(), sampleGeneList) && StringUtil.isContain(baseSnpGene.getGene(), traitGeneList)) {
                        //noinspection DuplicatedCode
                        rsIdList.add(baseSnpGene.getRsId());
                        double[] doubles = generateRandomCoordinates(-9 * step, -4 * step, -3.5 * step, 4 * step);
                        addNode(baseSnpGene.getRsId(), "SNP", 20, echartsNodeList, doubles, false, 8, "inside");
                        addLink(traitId, baseSnpGene.getRsId(), 1D, echartsLinkList, true);
                    }
                } else {
                    //noinspection DuplicatedCode
                    rsIdList.add(baseSnpGene.getRsId());
                    double[] doubles = generateRandomCoordinates(-9 * step, -3 * step, -6 * step, 3 * step);
                    addNode(baseSnpGene.getRsId(), "SNP", 20, echartsNodeList, doubles, false, 8, "inside");
                    addLink(traitId, baseSnpGene.getRsId(), 1D, echartsLinkList, false);
                }
            }
            // Check if the gene of the current Magma annotation is present in both the sample and trait gene lists
            if (StringUtil.isContain(baseSnpGene.getGene(), sampleGeneList) && StringUtil.isContain(baseSnpGene.getGene(), traitGeneList)) {
                // Add a link with a weight of 2D between the rsID and the gene, indicating a stronger connection
                addLink(baseSnpGene.getRsId(), baseSnpGene.getGene(), 2D, echartsLinkList, true);
            } else {
                if (!regulationGraphVO.getIsCore()) {
                    // Add a link with a weight of 1D between the rsID and the gene, indicating a weaker connection
                    addLink(baseSnpGene.getRsId(), baseSnpGene.getGene(), 1D, echartsLinkList, false);
                }
            }
        }

        // Return an EchartsGraphData object built from the node list, link list, and predefined categories
        return EchartsGraphData.builder()
                .nodes(echartsNodeList)
                .links(echartsLinkList)
                .categories(categoriesList)
                .build();
    }

    /**
     * Retrieves the graph data for TF (Transcription Factors) based on the provided regulation graph parameters.
     * This method is cached to improve performance on subsequent calls with the same parameters.
     *
     * @param regulationGraphVO the value object containing the parameters for the regulation graph
     * @return the EchartsGraphData object representing the graph data for TF
     */
    @Override
    public EchartsGraphData getTfGraphData(RegulationGraphVO regulationGraphVO) {

        // Extract parameters from the regulationGraphVO
        String sampleId = regulationGraphVO.getSampleId(); // Sample ID
        String traitId = regulationGraphVO.getTraitId(); // Trait ID
        String cellType = regulationGraphVO.getCellType(); // Cell type

        // Obtain TFs list
        AnalysisTfVO analysisTfVO = regulationGraphVO.getAnalysisTf();
        List<String> tfList = NullUtil.listEmpty();

        if (ObjectUtils.isNotEmpty(analysisTfVO) && analysisTfVO.getIsFile() != null) {
            // Get the working path and user-specific path
            String workPath = path.getWorkPath();
            String userPath = workPath + "/user/";
            // Determine if the input is from a file
            tfList = getElement(analysisTfVO.getIsFile(), analysisTfVO.getContent(), userPath, analysisTfVO.getFileId());
        }

        // Initialize lists for nodes, links, and categories
        List<EchartsNode> echartsNodeList = Lists.newArrayListWithExpectedSize(32);
        List<EchartsLink> echartsLinkList = Lists.newArrayListWithExpectedSize(32);
        List<EchartsCategories> categoriesList = Lists.newArrayListWithCapacity(7);

        String traitStrategy = analysisTfVO.getTraitStrategy();

        // Add categories to the categories list
        categoriesList.add(EchartsCategories.builder().name("Trait").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#506bb2").build()).build());

        if (StringUtil.isEqual(traitStrategy, "gimme")) {
            categoriesList.add(EchartsCategories.builder().name("SNP").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#8cc372").build()).build());
        }

        if (!regulationGraphVO.getIsCore()) {
            if (StringUtil.isEqual(traitStrategy, "gimme")) {
                categoriesList.add(EchartsCategories.builder().name("TF (GimmeMotifs)").itemStyle(EchartsItemStyle.builder().color("#f1c25b").build()).build());
            } else if (StringUtil.isEqual(traitStrategy, "homer")) {
                categoriesList.add(EchartsCategories.builder().name("TF (HOMER)").itemStyle(EchartsItemStyle.builder().color("#f1c25b").build()).build());
            } else {
                log.error("[getTfGraphData]: Parameter error {}", regulationGraphVO);
                throw new RunException(SystemException.ILLEGAL_PARAMETER);
            }
        }
        categoriesList.add(EchartsCategories.builder().name("Key TF").symbolSize(35D).itemStyle(EchartsItemStyle.builder().color("#e16563").build()).build());
        if (!regulationGraphVO.getIsCore()) {
            categoriesList.add(EchartsCategories.builder().name("TF (Difference)").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#72b8d4").build()).build());
        }
        categoriesList.add(EchartsCategories.builder().name("Cell type").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#3b9c6f").build()).build());
        categoriesList.add(EchartsCategories.builder().name("scATAC-seq").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#ef8152").build()).build());

        // Define the step for positioning nodes
        double step = 100;

        // Add the trait node
        if (StringUtil.isEqual(traitStrategy, "gimme")) {
            addNode(traitId, "Trait", 40, echartsNodeList, new double[]{-10 * step, 0D}, true, 17, "top");
        } else if (StringUtil.isEqual(traitStrategy, "homer")) {
            addNode(traitId, "Trait", 40, echartsNodeList, regulationGraphVO.getIsCore() ? new double[]{-4 * step, 0D} : new double[]{-5 * step, 0D}, true, 17, "top");
        }

        // Add the sample node
        addNode(sampleId, "scATAC-seq", 40, echartsNodeList, new double[]{9 * step, 0D}, true, 17, "top");

        // Query the sample information based on the sample ID
        LambdaQueryWrapper<Sample> sampleQueryWrapper = new LambdaQueryWrapper<>();
        sampleQueryWrapper.eq(Sample::getSampleId, sampleId); // SQL: SELECT * FROM Sample WHERE sampleId = ?
        Sample sample = sampleMapper.selectOne(sampleQueryWrapper);

        String scStrategy = analysisTfVO.getScStrategy();

        List<? extends BaseCellTypeTf> cellTypeTfsList;

        if (StringUtil.isEqual(scStrategy, "snapatac2")) {
            cellTypeTfsList = differenceTfChunkMapper.selectBySampleIdAndCellTypeAndTfList(sampleId, cellType, tfList,
                    analysisTfVO.getLog2FoldChange(), analysisTfVO.getAdjustedPValue(), analysisTfVO.getPvalue());
        } else if (StringUtil.isEqual(scStrategy, "chromvar")) {
            LambdaQueryWrapper<ChromVarDifferenceTf> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ChromVarDifferenceTf::getSampleId, sampleId);
            if (StringUtil.isNotEqual(cellType, ALL_DATA_SYMBOL)) {
                queryWrapper.eq(BaseCellTypeTf::getCellType, cellType);
            }
            if (analysisTfVO.getMean() > 0) {
                queryWrapper.ge(ChromVarDifferenceTf::getMean1, analysisTfVO.getMean());
            }
            if (analysisTfVO.getPvalue() > 0) {
                queryWrapper.le(ChromVarDifferenceTf::getPValue, analysisTfVO.getPvalue());
            }
            if (analysisTfVO.getAdjustedPValue() > 0) {
                queryWrapper.le(ChromVarDifferenceTf::getPValueAdjust, analysisTfVO.getAdjustedPValue());
            }
            cellTypeTfsList = chromVarDifferenceTfMapper.selectList(queryWrapper);
        } else {
            log.error("[getTfGraphData]: Parameter error {}", regulationGraphVO);
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }

        // Ensure the difference TF list is not null
        assert cellTypeTfsList != null;

        // Extract the distinct TFs from the difference TF list
        List<String> sampleTfList = ListUtil.isEmpty(cellTypeTfsList)
                ? NullUtil.listEmpty()
                : cellTypeTfsList.stream().map(BaseCellTypeTf::getTf).distinct().toList();

        // Retrieve the signal ID for the trait
        String signalId = getTraitSignalId(traitId);

        List<String> traitTfList = Lists.newArrayListWithExpectedSize(16);

        if (StringUtil.isEqual(traitStrategy, "gimme")) {
            List<GimmeSampleTraitTf> gimmeSampleTraitTfList = gimmeSampleTraitTfMapper.selectByScoreAndMeanScore(sampleId, signalId,
                    traitId, analysisTfVO.getCoScore(), analysisTfVO.getMeanScore(), analysisTfVO.getCount(), null);

            List<String> rsIdList = Lists.newArrayListWithExpectedSize(16);

            for (GimmeSampleTraitTf gimmeSampleTraitTf : gimmeSampleTraitTfList) {
                String tf = gimmeSampleTraitTf.getTf();
                String rsId = gimmeSampleTraitTf.getRsId();

                // Check if the sample TF is also in the trait TF list
                if (StringUtil.isContain(tf, sampleTfList)) {
                    addLink(rsId, tf, 2D, echartsLinkList, true);
                } else {
                    if (!regulationGraphVO.getIsCore()) {
                        addLink(rsId, tf, 1D, echartsLinkList, false);
                    }
                }

                if (StringUtil.isNotContain(tf, traitTfList)) {
                    traitTfList.add(tf);

                    if (StringUtil.isContain(tf, sampleTfList)) {

                        double[] doubles = regulationGraphVO.getIsCore() ? generateRandomCoordinates(-2 * step, -5 * step, 3.5 * step, 5 * step)
                                : generateRandomCoordinates(-2 * step, -5 * step, 2 * step, 5 * step);
                        addNode(tf, "Key TF", 35, echartsNodeList, doubles, true, 10, "inside");

                        if (regulationGraphVO.getIsCore()) {
                            if (StringUtil.isNotContain(rsId, rsIdList)) {
                                rsIdList.add(rsId);
                                doubles = generateRandomCoordinates(-9 * step, -4 * step, -3.5 * step, 4 * step);
                                addNode(rsId, "SNP", 20, echartsNodeList, doubles, false, 10, "inside");
                                addLink(traitId, rsId, 1D, echartsLinkList, false);
                            }
                        }

                    } else {

                        if (!regulationGraphVO.getIsCore()) {
                            // Generate random coordinates for the node
                            double[] doubles = generateRandomCoordinates(-5.5 * step, -3 * step, -2.5 * step, 3 * step);
                            // Add the node with category "TF (Difference)"
                            addNode(tf, "TF (GimmeMotifs)", 20, echartsNodeList, doubles, false, 10, "inside");
                        }
                    }

                }

                if (!regulationGraphVO.getIsCore()) {
                    if (StringUtil.isNotContain(rsId, rsIdList)) {
                        rsIdList.add(rsId);
                        double[] doubles = generateRandomCoordinates(-9 * step, -3 * step, -6 * step, 3 * step);
                        addNode(rsId, "SNP", 20, echartsNodeList, doubles, false, 10, "inside");
                        addLink(traitId, rsId, 1D, echartsLinkList, false);
                    }
                }

            }

        } else if (StringUtil.isEqual(traitStrategy, "homer")) {

            // Query the HOMER data based on the trait ID, signal ID, and genome
            List<Homer> homerList = homerMapper.selectByTraitIdAndTfList(signalId, traitId, sample.getGenome(), tfList,
                    analysisTfVO.getPvalueTrait(), analysisTfVO.getQvalueTrait());

            // Extract the distinct TFs from the HOMER data
            traitTfList = ListUtil.isEmpty(homerList)
                    ? NullUtil.listEmpty()
                    : homerList.stream().map(Homer::getTf).distinct().toList();

            // Iterate over the sample TF list
            for (String sampleTf : sampleTfList) {

                // Check if the sample TF is also in the trait TF list
                if (StringUtil.isContain(sampleTf, traitTfList)) {
                    // Generate random coordinates for the node
                    double[] doubles = regulationGraphVO.getIsCore() ? generateRandomCoordinates(-2.5 * step, -4 * step, 2.5 * step, 4 * step)
                            : generateRandomCoordinates(-1 * step, -4 * step, step, 4 * step);
                    // Add the node with category "Key TF"
                    addNode(sampleTf, "Key TF", 35, echartsNodeList, doubles, true, 10, "inside");
                } else {
                    if (!regulationGraphVO.getIsCore()) {
                        // Generate random coordinates for the node
                        double[] doubles = generateRandomCoordinates(1.5 * step, -3 * step, 4.5 * step, 3 * step);
                        // Add the node with category "TF (Difference)"
                        addNode(sampleTf, "TF (Difference)", 20, echartsNodeList, doubles, false, 10, "inside");
                    }
                }
            }

            // Iterate over the trait TF list
            for (String traitTf : traitTfList) {
                // Check if the trait TF is not in the sample TF list
                if (StringUtil.isNotContain(traitTf, sampleTfList)) {
                    double[] doubles = generateRandomCoordinates(-3 * step, -3 * step, -1.5 * step, 3 * step);
                    addNode(traitTf, "TF (HOMER)", 20, echartsNodeList, doubles, false, 10, "inside");
                }
                // Check if the trait TF is in both the sample and trait TF lists
                if (StringUtil.isContain(traitTf, sampleTfList)) {
                    // Add a link with weight 2
                    addLink(traitId, traitTf, 2D, echartsLinkList, true);
                } else {
                    if (!regulationGraphVO.getIsCore()) {
                        addLink(traitId, traitTf, 1D, echartsLinkList, false);
                    }
                }
            }

        } else {
            log.error("[getTfGraphData]: Parameter error {}", regulationGraphVO);
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }

        // Initialize a list for cell types
        List<String> cellTypeList = Lists.newArrayListWithExpectedSize(32);

        // Iterate over the difference TF list
        for (BaseCellTypeTf cellTypeTf : cellTypeTfsList) {
            // Set the cell type in the graph
            setCellTypeInGraph(sampleId, echartsLinkList, echartsNodeList, regulationGraphVO.getIsCore() ? 3.5 : 5, step, cellTypeList, cellTypeTf.getCellType());

            // Check if the TF is in both the sample and trait TF lists
            if (StringUtil.isContain(cellTypeTf.getTf(), sampleTfList) && StringUtil.isContain(cellTypeTf.getTf(), traitTfList)) {
                // Add a link with weight 2
                addLink(cellTypeTf.getTf(), cellTypeTf.getCellType(), 2D, echartsLinkList, true);
            } else {
                if (!regulationGraphVO.getIsCore()) {
                    addLink(cellTypeTf.getTf(), cellTypeTf.getCellType(), 1D, echartsLinkList, false);
                }
            }
        }

        // Build and return the EchartsGraphData object
        return EchartsGraphData.builder().nodes(echartsNodeList).links(echartsLinkList).categories(categoriesList).build();
    }

    @Override
    public EchartsGraphData getTfGeneGraphData(RegulationGraphVO regulationGraphVO) {
        // Extract parameters from the regulationGraphVO
        String sampleId = regulationGraphVO.getSampleId(); // Sample ID
        String traitId = regulationGraphVO.getTraitId(); // Trait ID
        String cellType = regulationGraphVO.getCellType(); // Cell type

        // TF graph
        AnalysisTfVO analysisTfVO = regulationGraphVO.getAnalysisTf();

        if (!regulationGraphVO.getIsCore() || StringUtil.isNotEqual(analysisTfVO.getTraitStrategy(), "gimme")) {
            log.error("[getTfGeneGraphData]: Parameter error {}", regulationGraphVO);
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }

        // Gene graph
        AnalysisGeneVO analysisGeneVO = regulationGraphVO.getAnalysisGene();

        String scTfStrategy = analysisTfVO.getScStrategy();
        String traitGeneStrategy = analysisGeneVO.getStrategy();

        // Initialize lists for nodes, links, and categories
        List<EchartsNode> echartsNodeList = Lists.newArrayListWithExpectedSize(32);
        List<EchartsLink> echartsLinkList = Lists.newArrayListWithExpectedSize(32);
        List<EchartsCategories> categoriesList = Lists.newArrayListWithCapacity(6);

        // Add categories to the categories list
        categoriesList.add(EchartsCategories.builder().name("Trait").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#506bb2").build()).build());
        categoriesList.add(EchartsCategories.builder().name("SNP").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#8cc372").build()).build());
        categoriesList.add(EchartsCategories.builder().name("Key TF").symbolSize(35D).itemStyle(EchartsItemStyle.builder().color("#72b8d4").build()).build());
        categoriesList.add(EchartsCategories.builder().name("Key gene").symbolSize(35D).itemStyle(EchartsItemStyle.builder().color("#e16563").build()).build());
        categoriesList.add(EchartsCategories.builder().name("Cell type").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#3b9c6f").build()).build());
        categoriesList.add(EchartsCategories.builder().name("scATAC-seq").symbolSize(20D).itemStyle(EchartsItemStyle.builder().color("#ef8152").build()).build());

        // Define the step for positioning nodes
        double step = 100;

        // Position trait node on left side of visualization canvas
        addNode(traitId, "Trait", 40, echartsNodeList, new double[]{-10 * step, 0D}, true, 17, "top");

        // Position sample node on right side of visualization canvas
        addNode(sampleId, "scATAC-seq", 40, echartsNodeList, new double[]{10 * step, 0D}, true, 17, "top");

        // Construct database query for sample metadata retrieval:ml-citation{ref="3,5" data="citationList"}
        LambdaQueryWrapper<Sample> sampleQueryWrapper = new LambdaQueryWrapper<>();
        // Set WHERE clause condition: sample_id column equals provided identifier
        sampleQueryWrapper.eq(Sample::getSampleId, sampleId);
        // Execute SQL SELECT statement returning single matching record
        Sample sample = sampleMapper.selectOne(sampleQueryWrapper);

        Map<String, Object> traitGeneData = getTraitGeneData(sampleId, traitId, sample.getGenome(), analysisGeneVO);

        @SuppressWarnings("unchecked")
        List<? extends BaseSnpGene> baseSnpGeneList = (List<? extends BaseSnpGene>) traitGeneData.get("baseSnpGeneList");
        @SuppressWarnings("unchecked")
        List<String> traitGeneList = (List<String>) traitGeneData.get("traitGeneList");

        // overlap gene-cell type
        List<DifferenceGeneChunk> differenceGeneList = ListUtil.isEmpty(traitGeneList) ? NullUtil.listEmpty()
                : differenceGeneChunkMapper.selectBySampleIdAndCellTypeAndGeneListWithTop(
                sampleId, cellType, traitGeneList, analysisGeneVO.getScore(), analysisGeneVO.getLog2FoldChange(),
                analysisGeneVO.getAdjustedPValue(), analysisGeneVO.getPvalue(), 0);

        // overlap gene
        List<String> overlapGeneList = differenceGeneList.stream().map(DifferenceGene::getGene).distinct().toList();

        // TF-cell type
        List<? extends BaseCellTypeTf> cellTypeTfList;

        if (StringUtil.isEqual(scTfStrategy, "snapatac2")) {
            cellTypeTfList = differenceTfChunkMapper.selectBySampleIdAndCellTypeAndTfList(sampleId, cellType, null,
                    analysisTfVO.getLog2FoldChange(), analysisTfVO.getAdjustedPValue(), analysisTfVO.getPvalue());
        } else if (StringUtil.isEqual(scTfStrategy, "chromvar")) {
            LambdaQueryWrapper<ChromVarDifferenceTf> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ChromVarDifferenceTf::getSampleId, sampleId);
            if (StringUtil.isNotEqual(cellType, ALL_DATA_SYMBOL)) {
                queryWrapper.eq(BaseCellTypeTf::getCellType, cellType);
            }
            if (analysisTfVO.getMean() > 0) {
                queryWrapper.ge(ChromVarDifferenceTf::getMean1, analysisTfVO.getMean());
            }
            if (analysisTfVO.getPvalue() > 0) {
                queryWrapper.le(ChromVarDifferenceTf::getPValue, analysisTfVO.getPvalue());
            }
            if (analysisTfVO.getAdjustedPValue() > 0) {
                queryWrapper.le(ChromVarDifferenceTf::getPValueAdjust, analysisTfVO.getAdjustedPValue());
            }
            cellTypeTfList = chromVarDifferenceTfMapper.selectList(queryWrapper);
        } else {
            log.error("[getTfGeneGraphData]: Parameter error {}", regulationGraphVO);
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }

        // Ensure the difference TF list is not null
        assert cellTypeTfList != null;

        // Extract the distinct TFs from the difference TF list
        List<String> sampleTfList = ListUtil.isEmpty(cellTypeTfList)
                ? NullUtil.listEmpty()
                : cellTypeTfList.stream().map(BaseCellTypeTf::getTf).distinct().toList();

        String signalId = getTraitSignalId(traitId);

        // trait-rsID-overlap TF
        List<GimmeSampleTraitTf> gimmeSampleTraitTfList = ListUtil.isEmpty(sampleTfList) ? NullUtil.listEmpty()
                : gimmeSampleTraitTfMapper.selectByScoreAndMeanScore(sampleId, signalId,
                traitId, analysisTfVO.getCoScore(), analysisTfVO.getMeanScore(), analysisTfVO.getCount(), sampleTfList);

        List<String> overlapTfList = gimmeSampleTraitTfList.stream().map(GimmeSampleTraitTf::getTf).toList();

        // overlap TF-cell type
        cellTypeTfList = cellTypeTfList.stream().filter((cellTypeTf) -> StringUtil.isContain(cellTypeTf.getTf(), overlapTfList)).toList();

        // overlap TF-overlap gene
        List<TfGeneSample> tfGeneSampleList = NullUtil.listEmpty();

        if (ListUtil.isNotEmpty(overlapTfList) && ListUtil.isNotEmpty(overlapGeneList)) {
            tfGeneSampleList = tfGeneSampleMapper.selectByTfListGeneListByScore(sampleId, overlapTfList,
                    overlapGeneList, analysisTfVO.getMeanScore(), analysisTfVO.getCoScore());
        }

        // trait-snp `baseSnpGeneList` `gimmeSampleTraitTfList`
        // snp-gene `baseSnpGeneList`
        // snp-tf `gimmeSampleTraitTfList`
        // tf-gene `tfGeneSampleList`
        // tf-cell type `cellTypeTfList`
        // gene-cell type `differenceGeneList`

        // Initialize a list to store rsIDs, with an expected size of 32 to optimize performance
        List<String> rsIdList = Lists.newArrayListWithExpectedSize(32);
        List<String> geneList = Lists.newArrayListWithExpectedSize(32);

        for (BaseSnpGene baseSnpGene : baseSnpGeneList) {
            if (StringUtil.isContain(baseSnpGene.getGene(), overlapGeneList)) {
                // Check if the rsID of the current Magma annotation is not already in the rsID list to avoid duplicates
                if (StringUtil.isNotContain(baseSnpGene.getRsId(), rsIdList)) {
                    //noinspection DuplicatedCode
                    rsIdList.add(baseSnpGene.getRsId());
                    double[] doubles = generateRandomCoordinates(-9 * step, -4 * step, -3.5 * step, 4 * step);
                    addNode(baseSnpGene.getRsId(), "SNP", 20, echartsNodeList, doubles, false, 8, "inside");
                    addLink(traitId, baseSnpGene.getRsId(), 1D, echartsLinkList, true);
                }
                if (StringUtil.isNotContain(baseSnpGene.getGene(), geneList)) {
                    geneList.add(baseSnpGene.getGene());
                    double[] doubles = generateRandomCoordinates(-2 * step, 0 * step, 3.5 * step, 5 * step);
                    addNode("Gene: " + baseSnpGene.getGene(), baseSnpGene.getGene(), "Key gene", 35, echartsNodeList, doubles, true, 8, "inside");
                }
                // Add a link with a weight of 2D between the rsID and the gene, indicating a stronger connection
                addLink(baseSnpGene.getRsId(), "Gene: " + baseSnpGene.getGene(), 2D, echartsLinkList, true);
            }
        }

        List<String> tfList = Lists.newArrayListWithExpectedSize(32);

        for (GimmeSampleTraitTf gimmeSampleTraitTf : gimmeSampleTraitTfList) {
            if (StringUtil.isContain(gimmeSampleTraitTf.getTf(), overlapTfList)) {
                // Check if the rsID of the current Magma annotation is not already in the rsID list to avoid duplicates
                if (StringUtil.isNotContain(gimmeSampleTraitTf.getRsId(), rsIdList)) {
                    //noinspection DuplicatedCode
                    rsIdList.add(gimmeSampleTraitTf.getRsId());
                    double[] doubles = generateRandomCoordinates(-9 * step, -4 * step, -3.5 * step, 4 * step);
                    addNode(gimmeSampleTraitTf.getRsId(), "SNP", 20, echartsNodeList, doubles, false, 8, "inside");
                    addLink(traitId, gimmeSampleTraitTf.getRsId(), 1D, echartsLinkList, false);
                }
                if (StringUtil.isNotContain(gimmeSampleTraitTf.getTf(), tfList)) {
                    tfList.add(gimmeSampleTraitTf.getTf());
                    double[] doubles = generateRandomCoordinates(-2 * step, -5 * step, 3.5 * step, 0 * step);
                    addNode("TF: " + gimmeSampleTraitTf.getTf(), gimmeSampleTraitTf.getTf(), "Key TF", 35, echartsNodeList, doubles, true, 8, "inside");
                }
                // Add a link with a weight of 2D between the rsID and the gene, indicating a stronger connection
                addLink(gimmeSampleTraitTf.getRsId(), "TF: " + gimmeSampleTraitTf.getTf(), 2D, echartsLinkList, true);
            }
        }

        for (TfGeneSample tfGeneSample : tfGeneSampleList) {
            if (StringUtil.isContain(tfGeneSample.getTf(), overlapTfList) && StringUtil.isContain(tfGeneSample.getGene(), overlapGeneList)) {
                addLink("TF: " + tfGeneSample.getTf(), "Gene: " + tfGeneSample.getGene(), 2D, echartsLinkList, true);
            }
        }

        // Initialize a list for cell types
        List<String> cellTypeList = Lists.newArrayListWithCapacity(16);

        // Iterate over the difference TF list
        for (BaseCellTypeTf cellTypeTf : cellTypeTfList) {
            // Set the cell type in the graph
            setCellTypeInGraph(sampleId, echartsLinkList, echartsNodeList, 4, step, cellTypeList, cellTypeTf.getCellType());

            // Check if the TF is in both the sample and trait TF lists
            if (StringUtil.isContain(cellTypeTf.getTf(), overlapTfList)) {
                // Add a link with weight 2
                addLink("TF: " + cellTypeTf.getTf(), cellTypeTf.getCellType(), 2D, echartsLinkList, true);
            }
        }

        for (DifferenceGeneChunk differenceGeneChunk : differenceGeneList) {
            setCellTypeInGraph(sampleId, echartsLinkList, echartsNodeList, 4, step, cellTypeList, differenceGeneChunk.getCellType());

            if (StringUtil.isContain(differenceGeneChunk.getGene(), overlapGeneList)) {
                addLink("Gene: " + differenceGeneChunk.getGene(), differenceGeneChunk.getCellType(), 2D, echartsLinkList, true);
            }
        }

        return EchartsGraphData.builder().nodes(echartsNodeList).links(echartsLinkList).categories(categoriesList).build();
    }

    /**
     * Retrieves a list of MagmaAnno objects containing variant information data
     * for a specific trait ID and gene. The result is cached to improve performance
     * on subsequent calls with the same parameters.
     *
     * @param traitId the ID of the trait
     * @param genome  the genome version
     * @param gene    the gene name
     * @return a list of MagmaAnno objects with the variant information
     */
    @Cacheable
    @Override
    public List<MagmaAnno> listMagmaVariantInfoDataByTraitIdAndGene(String traitId, String genome, String gene) {
        // Get the signal ID associated with the trait ID
        String signalId = getTraitSignalId(traitId); // Util method to retrieve signal ID based on trait ID

        // Query the database for MagmaAnno records matching the trait ID, signal ID, genome, and gene
        return magmaAnnoMapper.selectByTraitIdAndGene(signalId, traitId, genome, gene); // Mapper method to fetch data
    }

    /**
     * Retrieves a list of all Sample objects. The result is cached to improve performance
     * on subsequent calls.
     *
     * @return a list of Sample objects
     */
    @Cacheable
    @Override
    public List<Sample> listSample() {
        // Query the database for all Sample records
        return sampleMapper.selectList(null); // Mapper method to fetch all samples, no conditions applied
    }

    /**
     * Retrieves the heatmap data for sample traits based on the provided sample ID, method, and list of trait IDs.
     * The result is cached to improve performance on subsequent calls with the same parameters.
     * This method executes a Python script to generate the heatmap data and parses the results.
     *
     * @param sampleId    the ID of the sample
     * @param method      the method used for generating the heatmap
     * @param strategy    the strategy used for generating the heatmap
     * @param traitIdList the list of trait IDs
     * @return a CanvasXpressHeatMapData object containing the heatmap data
     * @throws IOException if an I/O error occurs during the execution of the Python script
     */
    @Cacheable
    @Override
    public CanvasXpressHeatMapData<Double> getSampleTraitsHeatmap(String sampleId, String method, String strategy, List<String> traitIdList) throws IOException {
        // Get the working path for the files
        String workPath = path.getWorkPath();

        // Create a query wrapper to find the sample by sample ID
        LambdaQueryWrapper<Sample> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Sample::getSampleId, sampleId);

        // Retrieve the sample from the database
        Sample sample = sampleMapper.selectOne(queryWrapper);

        // Construct the file path for the TRS file
        String trs_file = workPath + "/trs/" + sample.getLabel() + "/cell_type/" + sample.getLabel() + "_cell_type_trs_" + method + ".h5ad";

        // Construct the file path for the Python script
        String python_file = workPath + "/python/get_sample_heatmap.py";

        // Join the trait IDs into a single string separated by "__"
        String collect = String.join("__", traitIdList);

        // Construct the command to execute the Python script
        String exec = "python3 " + python_file + " " + trs_file + " " + strategy + " " + collect;

        // Execute the Python script and get the results
        List<String> results = execLinux.execCommand(exec).getResultList();

        // Parse the cell type list from the first result
        List<String> cellTypeList = listStringByPythonResult(results.get(0));

        // Parse the trait ID list from the second result
        traitIdList = listStringByPythonResult(results.get(1));

        // Initialize the list to hold the values for the heatmap
        List<List<Double>> values = Lists.newArrayListWithCapacity(traitIdList.size());

        List<String> stringList = Lists.newArrayListWithCapacity(traitIdList.size());

        // Create a query wrapper to find traits by trait IDs
        LambdaQueryWrapper<Trait> traitQueryWrapper = new LambdaQueryWrapper<>();

        for (String traitId : traitIdList) {
            LambdaQueryWrapper<Trait> wrapper = traitQueryWrapper.clone();
            wrapper.eq(Trait::getTraitId, traitId);
            Trait trait = traitMapper.selectOne(wrapper);
            stringList.add(trait.getTraitAbbr() + " (" + trait.getTraitId() + ")");
        }

        // Loop through the cell type list to parse the TRS values
        for (int i = 0; i < cellTypeList.size(); i++) {
            // Parse the TRS values for each cell type
            List<Double> trsValue = listDoubleByPythonResult(results.get(i + 2));
            // Add the TRS values to the list
            values.add(trsValue);
        }

        // Build and return the CanvasXpressHeatMapData object
        return CanvasXpressHeatMapData.<Double>builder().data(values).xLabelList(cellTypeList).yLabelList(stringList).build();
    }

}
