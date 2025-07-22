package com.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.mapper.SampleCellTypeMapper;
import com.spring.boot.mapper.SampleMapper;
import com.spring.boot.mapper.TraitMapper;
import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.SampleCellType;
import com.spring.boot.pojo.Trait;
import com.spring.boot.pojo.BaseSample;
import com.spring.boot.pojo.vo.SampleDataBrowseResultVO;
import com.spring.boot.pojo.vo.SampleDataBrowseVO;
import com.spring.boot.pojo.vo.TraitDataBrowseResultVO;
import com.spring.boot.pojo.vo.TraitDataBrowseVO;
import com.spring.boot.service.DataBrowseService;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.vo.FieldNumber;
import com.spring.boot.util.util.StringUtil;
import com.spring.boot.util.util.result.Page;
import com.spring.boot.util.util.result.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.spring.boot.util.util.result.PageUtil.setQueryWrapperByApply;

/**
 * Service implementation for browsing data related to samples and traits.
 * This class uses caching to improve performance for data retrieval operations.
 *
 * @author Zhengmin Yu
 */
@CacheConfig(cacheNames = "data_browse", keyGenerator = "cacheKeyGenerator")
@Service
public class DataBrowseServiceImpl implements DataBrowseService {

    private TraitMapper traitMapper;
    private SampleMapper sampleMapper;
    private SampleCellTypeMapper sampleCellTypeMapper;

    /**
     * Default constructor.
     */
    public DataBrowseServiceImpl() {
    }

    /**
     * Constructor with dependencies injection.
     *
     * @param traitMapper          the mapper for trait data
     * @param sampleMapper         the mapper for sample data
     * @param sampleCellTypeMapper the mapper for sample cell type data
     */
    @Autowired
    public DataBrowseServiceImpl(TraitMapper traitMapper, SampleMapper sampleMapper, SampleCellTypeMapper sampleCellTypeMapper) {
        this.traitMapper = traitMapper;
        this.sampleMapper = sampleMapper;
        this.sampleCellTypeMapper = sampleCellTypeMapper;
    }

    /**
     * Retrieves a list of FieldNumber objects representing the count of samples for each tissue type.
     * This method uses a generic approach to work with any mapper and entity that extends BaseSample.
     *
     * @param queryWrapperTmp The LambdaQueryWrapper to specify the query conditions.
     * @param t               The mapper used to execute the database query.
     * @param k               The entity class representing the sample data.
     * @return A list of FieldNumber objects with tissue type and corresponding count.
     */
    private <T extends BaseMapper<K>, K extends BaseSample> List<FieldNumber> getTissueTypeList(LambdaQueryWrapper<K> queryWrapperTmp, T t, K k) {
        // Set the entity class for the query wrapper to the class of the provided entity object
        //noinspection unchecked
        queryWrapperTmp.setEntityClass((Class<K>) k.getClass());

        // Select the tissueType and count fields from the database and group the results by tissueType
        queryWrapperTmp.select(K::getTissueType, K::getCount).groupBy(K::getTissueType);

        // Execute the query and retrieve a list of maps, where each map represents a row of the result set
        List<Map<String, Object>> mapList = t.selectMaps(queryWrapperTmp);

        // Stream the list of maps and transform each map into a FieldNumber object
        return mapList
                .stream()
                .map(stringObjectMap -> FieldNumber.builder()
                        // Set the field to the value of the tissueType key in the map
                        .field(String.valueOf(stringObjectMap.get("tissueType")))
                        // Set the number to the integer value of the count key in the map
                        .number(Math.toIntExact((Long) stringObjectMap.get("count")))
                        .build())
                // Collect the results into a list and return
                .toList();
    }

    /**
     * Retrieves all trait data.
     *
     * @return a list of Trait objects
     */
    @Cacheable
    @Override
    public List<Trait> listTraitAllData() {
        // Select all records from the trait table
        return traitMapper.selectList(null);
    }

    /**
     * Retrieves all sample data.
     *
     * @return a list of Sample objects
     */
    @Cacheable
    @Override
    public List<Sample> listSampleAllData() {
        // Select all records from the sample table
        return sampleMapper.selectList(null);
    }

    /**
     * Retrieves all sample cell type data.
     *
     * @return a list of SampleCellType objects
     */
    @Cacheable
    @Override
    public List<SampleCellType> listSampleCellTypeAllData() {
        // Select all records from the sample cell type table
        return sampleCellTypeMapper.selectList(null);
    }

    /**
     * Retrieves and organizes trait data for browsing based on the provided filter criteria.
     * This method is cached to improve performance on subsequent calls with the same parameters.
     *
     * @param traitDataBrowseVO The VO containing the filter criteria for browsing trait data.
     * @return A TraitDataBrowseResultVO object containing the organized trait data.
     */
    @Cacheable
    @Override
    public TraitDataBrowseResultVO traitDataBrowseData(TraitDataBrowseVO traitDataBrowseVO) {
        // Initialize the query wrapper forTrait entity
        QueryWrapper<Trait> queryWrapper = new QueryWrapper<>();

        // Extract parameters from the input VO
        String type = traitDataBrowseVO.getType();
        String category = traitDataBrowseVO.getCategory();
        String subcategory = traitDataBrowseVO.getSubcategory();
        String cohort = traitDataBrowseVO.getCohort();
        Page page = traitDataBrowseVO.getPage();

        // Set the base filter condition
        // queryWrapper.eq("f_filter", 1);

        // Add category condition if not empty
        if (StringUtil.isNotEmpty(category)) {
            queryWrapper.lambda().eq(Trait::getCategory, category);
        }

        // Add type condition if not empty
        if (StringUtil.isNotEmpty(type)) {
            queryWrapper.lambda().eq(Trait::getType, type);
        }

        // Add subcategory condition if not empty
        if (StringUtil.isNotEmpty(subcategory)) {
            queryWrapper.lambda().eq(Trait::getSubcategory, subcategory);
        }

        // Add cohort condition if not empty
        if (StringUtil.isNotEmpty(cohort)) {
            queryWrapper.lambda().eq(Trait::getCohort, cohort);
        }

        // Clone the query wrapper for subsequent queries
        QueryWrapper<Trait> queryWrapperTmpPage = queryWrapper.clone();

        setQueryWrapperByApply(page, queryWrapperTmpPage);
        // Order by trait index and retrieve the list of traits
        queryWrapperTmpPage.lambda().orderByAsc(Trait::getTraitIndex);
        PageResult<Trait> traitList = PageResultUtil.format(page, () -> traitMapper.selectList(queryWrapperTmpPage));

        // Clone the original query wrapper for the next query
        QueryWrapper<Trait> queryWrapperTmp = queryWrapper.clone();

        // Select and group by type to get the count of each type
        queryWrapperTmp.lambda().select(Trait::getType, Trait::getCount).groupBy(Trait::getType);
        List<FieldNumber> typeList = traitMapper.selectMaps(queryWrapperTmp)
                .stream().map(stringObjectMap -> FieldNumber.builder()
                        .field(String.valueOf(stringObjectMap.get("type")))
                        .number(Math.toIntExact((Long) stringObjectMap.get("count"))).build())
                .toList();

        // Clone the original query wrapper for the next query
        queryWrapperTmp = queryWrapper.clone();

        // Select and group by category to get the count of each category
        queryWrapperTmp.lambda().select(Trait::getCategory, Trait::getCount).groupBy(Trait::getCategory);
        List<FieldNumber> categoryList = traitMapper.selectMaps(queryWrapperTmp)
                .stream().map(stringObjectMap -> FieldNumber.builder()
                        .field(String.valueOf(stringObjectMap.get("category")))
                        .number(Math.toIntExact((Long) stringObjectMap.get("count"))).build())
                .toList();

        // Clone the original query wrapper for the next query
        queryWrapperTmp = queryWrapper.clone();
        // Set the select clause to retrieve subcategory and count, and group the results by subcategory
        queryWrapperTmp.lambda().select(Trait::getSubcategory, Trait::getCount).groupBy(Trait::getSubcategory);
        // Execute the query and map the results to a list of FieldNumber objects for subcategories
        List<FieldNumber> subCategoryList = traitMapper.selectMaps(queryWrapperTmp)
                .stream().map(stringObjectMap -> FieldNumber.builder()
                        .field(String.valueOf(stringObjectMap.get("subcategory"))) // Get the subcategory field from the result map
                        .number(Math.toIntExact((Long) stringObjectMap.get("count"))) // Get the count field from the result map and convert to int
                        .build())
                .toList();

        // Clone the original query wrapper for the cohort query
        queryWrapperTmp = queryWrapper.clone();
        // Set the select clause to retrieve cohort and count, and group the results by cohort
        queryWrapperTmp.lambda().select(Trait::getCohort, Trait::getCount).groupBy(Trait::getCohort);
        // Execute the query and map the results to a list of FieldNumber objects for cohorts
        List<FieldNumber> cohortList = traitMapper.selectMaps(queryWrapperTmp)
                .stream().map(stringObjectMap -> FieldNumber.builder()
                        .field(String.valueOf(stringObjectMap.get("cohort"))) // Get the cohort field from the result map
                        .number(Math.toIntExact((Long) stringObjectMap.get("count"))) // Get the count field from the result map and convert to int
                        .build())
                .toList();

        // Build and return the TraitDataBrowseResultVO with the retrieved and processed data
        return TraitDataBrowseResultVO.builder()
                .typeList(typeList) // Set the type list
                .categoryList(categoryList) // Set the category list
                .subcategoryList(subCategoryList) // Set the subcategory list
                .cohortList(cohortList) // Set the cohort list
                .dataBrowseDataList(traitList) // Set the data browse data list
                .build();

    }

    /**
     * This method is used to browse sample data based on given criteria.
     * It uses caching to improve performance.
     * The method filters samples by tissue type and health type,
     * orders them by index, and retrieves counts of different tissue and health types.
     *
     * @param sampleDataBrowseVO the input criteria for browsing sample data
     * @return a result object containing the list of samples and their counts by tissue and health types
     */
    @Cacheable
    @Override
    public SampleDataBrowseResultVO<Sample> sampleDataBrowseData(SampleDataBrowseVO sampleDataBrowseVO) {
        // Create a new query wrapper for the Sample entity
        LambdaQueryWrapper<Sample> queryWrapper = new LambdaQueryWrapper<>();

        // Extract tissueType and healthType from the input object
        String tissueType = sampleDataBrowseVO.getTissueType();
        String healthType = sampleDataBrowseVO.getHealthType();

        // Add tissueType condition to the query if it is not empty
        if (StringUtil.isNotEmpty(tissueType)) {
            queryWrapper.eq(Sample::getTissueType, tissueType);
        }

        // Add healthType condition to the query if it is not empty
        if (StringUtil.isNotEmpty(healthType)) {
            queryWrapper.eq(Sample::getHealthType, healthType);
        }

        // Clone the original query wrapper for subsequent queries
        LambdaQueryWrapper<Sample> queryWrapperTmp = queryWrapper.clone();

        // Order the results by fIndex in ascending order
        queryWrapperTmp.orderByAsc(Sample::getFIndex);
        // Execute the query to retrieve the list of samples
        List<Sample> sampleList = sampleMapper.selectList(queryWrapperTmp);

        // Clone the original query wrapper for the next query
        queryWrapperTmp = queryWrapper.clone();

        // Retrieve the list of tissue types and their counts
        List<FieldNumber> tissueTypeList = getTissueTypeList(queryWrapperTmp, sampleMapper, new Sample());

        // Clone the original query wrapper for the next query
        queryWrapperTmp = queryWrapper.clone();
        // Select healthType and count, and group the results by healthType
        queryWrapperTmp.select(Sample::getHealthType, Sample::getCount).groupBy(Sample::getHealthType);
        // Execute the query and map the results to a list of FieldNumber objects
        List<FieldNumber> healthTypeList = sampleMapper.selectMaps(queryWrapperTmp)
                .stream().map(stringObjectMap -> FieldNumber.builder()
                        .field(String.valueOf(stringObjectMap.get("healthType")))
                        .number(Math.toIntExact((Long) stringObjectMap.get("count"))).build())
                .toList();

        // Build and return the result object containing the lists and data
        return SampleDataBrowseResultVO.<Sample>builder()
                .tissueTypeList(tissueTypeList)
                .healthTypeList(healthTypeList)
                .dataBrowseDataList(sampleList).build();
    }

    /**
     * Retrieves and organizes sample cell type data for browsing based on the provided filter criteria.
     * This method is cached to improve performance on subsequent calls with the same parameters.
     *
     * @param sampleDataBrowseVO The VO containing the filter criteria for browsing sample cell type data.
     * @return A SampleDataBrowseResultVO containing the organized data.
     */
    @Cacheable
    @Override
    public SampleDataBrowseResultVO<SampleCellType> sampleCellTypeDataBrowseData(SampleDataBrowseVO sampleDataBrowseVO) {
        // Initialize the query wrapper for SampleCellType
        LambdaQueryWrapper<SampleCellType> queryWrapper = new LambdaQueryWrapper<>();

        // Extract tissueType and cellType from the input VO
        String tissueType = sampleDataBrowseVO.getTissueType();
        String cellType = sampleDataBrowseVO.getCellType();

        // Add tissueType condition to the query if it is not empty
        if (StringUtil.isNotEmpty(tissueType)) {
            queryWrapper.eq(SampleCellType::getTissueType, tissueType);
        }

        // Add cellType condition to the query if it is not empty
        if (StringUtil.isNotEmpty(cellType)) {
            queryWrapper.eq(SampleCellType::getCellType, cellType);
        }

        // Execute the query to retrieve the list of SampleCellType
        List<SampleCellType> sampleCellTypeList = sampleCellTypeMapper.selectList(queryWrapper);

        // Clone the original query wrapper for the next query
        LambdaQueryWrapper<SampleCellType> queryWrapperTmp = queryWrapper.clone();

        // Retrieve and build the tissue type list with counts
        List<FieldNumber> tissueTypeList = getTissueTypeList(queryWrapperTmp, sampleCellTypeMapper, new SampleCellType());

        // Clone the original query wrapper for the next query
        queryWrapperTmp = queryWrapper.clone();
        // Set the select clause to retrieve cellType and count, and group the results by cellType
        queryWrapperTmp.select(SampleCellType::getCellType, SampleCellType::getCount).groupBy(SampleCellType::getCellType);
        // Execute the query and map the results to a list of FieldNumber objects for cell types
        List<FieldNumber> cellTypeList = sampleCellTypeMapper.selectMaps(queryWrapperTmp)
                .stream().map(stringObjectMap -> FieldNumber.builder()
                        .field(String.valueOf(stringObjectMap.get("cellType"))) // Map the cellType field
                        .number(Math.toIntExact((Long) stringObjectMap.get("count"))) // Map the count field
                        .build())
                .toList();

        // Build and return the result VO with the retrieved and processed data
        return SampleDataBrowseResultVO.<SampleCellType>builder()
                .tissueTypeList(tissueTypeList)
                .cellTypeList(cellTypeList)
                .dataBrowseDataList(sampleCellTypeList)
                .build();
    }

}
