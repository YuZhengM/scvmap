package com.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.boot.mapper.*;
import com.spring.boot.pojo.*;
import com.spring.boot.pojo.vo.SampleSearchVO;
import com.spring.boot.pojo.vo.TraitSearchVO;
import com.spring.boot.service.SearchService;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.vo.FieldNumber;
import com.spring.boot.util.util.ListUtil;
import com.spring.boot.util.util.StringUtil;
import com.spring.boot.util.util.result.Page;
import com.spring.boot.util.util.result.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.spring.boot.util.constant.ApplicationConstant.ALL_DATA_SYMBOL;
import static com.spring.boot.util.util.ApplicationUtil.checkSourceId;
import static com.spring.boot.util.util.result.PageUtil.setQueryWrapperByApply;

/**
 * @author Administrator
 */
@CacheConfig(cacheNames = "search", keyGenerator = "cacheKeyGenerator")
@Service
public class SearchServiceImpl implements SearchService {

    private TraitMapper traitMapper;
    private SourceMapper sourceMapper;
    private SampleMapper sampleMapper;
    private SampleCellTypeMapper sampleCellTypeMapper;
    private GeneTraitCountMapper geneTraitCountMapper;
    private TfTraitCountMapper tfTraitCountMapper;
    private TfMapper tfMapper;

    public SearchServiceImpl() {
    }

    @Autowired
    public SearchServiceImpl(TraitMapper traitMapper, SourceMapper sourceMapper, SampleMapper sampleMapper, SampleCellTypeMapper sampleCellTypeMapper, GeneTraitCountMapper geneTraitCountMapper, TfTraitCountMapper tfTraitCountMapper, TfMapper tfMapper) {
        this.traitMapper = traitMapper;
        this.sourceMapper = sourceMapper;
        this.sampleMapper = sampleMapper;
        this.sampleCellTypeMapper = sampleCellTypeMapper;
        this.geneTraitCountMapper = geneTraitCountMapper;
        this.tfTraitCountMapper = tfTraitCountMapper;
        this.tfMapper = tfMapper;
    }

    @Cacheable
    @Override
    public List<Source> listSourceInfo() {
        return sourceMapper.selectList(null);
    }

    @Cacheable
    @Override
    public List<Source> listSourceInfoByTraitIdList(List<String> traitIdList) {
        // get traits
        LambdaQueryWrapper<Trait> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Trait::getSourceId).in(Trait::getTraitId, traitIdList);
        List<Trait> traitList = traitMapper.selectList(queryWrapper);
        // get source ID set
        List<String> sourceIdList = traitList.stream().map(Trait::getSourceId).distinct().toList();
        // get source information
        LambdaQueryWrapper<Source> sourceQueryWrapper = new LambdaQueryWrapper<>();
        sourceQueryWrapper.in(Source::getId, sourceIdList);
        return sourceMapper.selectList(sourceQueryWrapper);
    }

    @Cacheable
    @Override
    public List<Trait> listTraitBySourceId(String sourceId) {
        // get traits
        LambdaQueryWrapper<Trait> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtil.isNotEqual(sourceId, ALL_DATA_SYMBOL) && StringUtil.isNotEmpty(sourceId)) {
            queryWrapper.eq(Trait::getSourceId, sourceId);
        }
        return traitMapper.selectList(queryWrapper);
    }

    @Cacheable
    @Override
    public List<Source> listSourceInfoBySourceIdList(List<String> sourceIdList) {
        LambdaQueryWrapper<Source> sourceQueryWrapper = new LambdaQueryWrapper<>();
        sourceQueryWrapper.in(Source::getId, sourceIdList);
        return sourceMapper.selectList(sourceQueryWrapper);
    }

    @Cacheable
    @Override
    public List<Sample> listSample() {
        return sampleMapper.selectList(null);
    }

    @Cacheable
    @Override
    public List<FieldNumber> listCategory() {
        LambdaQueryWrapper<Trait> queryWrapper = new LambdaQueryWrapper<>();

        // select field count - Category
        queryWrapper.select(Trait::getCategory, Trait::getCount).groupBy(Trait::getCategory);
        List<Map<String, Object>> mapList = traitMapper.selectMaps(queryWrapper);

        return mapList.stream()
                .map(stringObjectMap -> FieldNumber.builder()
                        .field(String.valueOf(stringObjectMap.get("category")))
                        .number(Math.toIntExact((Long) stringObjectMap.get("count"))).build())
                .toList();
    }

    @Cacheable
    @Override
    public List<FieldNumber> listSubcategoryByCategory(String category) {
        LambdaQueryWrapper<Trait> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Trait::getCategory, category);
        // select field count - Category
        queryWrapper.select(Trait::getSubcategory, Trait::getCount).groupBy(Trait::getSubcategory);
        List<Map<String, Object>> mapList = traitMapper.selectMaps(queryWrapper);

        return mapList.stream()
                .map(stringObjectMap -> FieldNumber.builder()
                        .field(String.valueOf(stringObjectMap.get("subcategory")))
                        .number(Math.toIntExact((Long) stringObjectMap.get("count"))).build())
                .toList();
    }

    @Cacheable
    @Override
    public List<FieldNumber> listCellType() {
        LambdaQueryWrapper<SampleCellType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SampleCellType::getCellType, SampleCellType::getCount).groupBy(SampleCellType::getCellType);
        return sampleCellTypeMapper.selectMaps(queryWrapper)
                .stream().map(stringObjectMap -> FieldNumber.builder()
                        .field(String.valueOf(stringObjectMap.get("cellType")))
                        .number(Math.toIntExact((Long) stringObjectMap.get("count"))).build())
                .toList();
    }

    @Cacheable
    @Override
    public List<FieldNumber> listTissueType() {
        LambdaQueryWrapper<Sample> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Sample::getTissueType, Sample::getCount).groupBy(Sample::getTissueType);
        return sampleMapper.selectMaps(queryWrapper)
                .stream().map(stringObjectMap -> FieldNumber.builder()
                        .field(String.valueOf(stringObjectMap.get("tissueType")))
                        .number(Math.toIntExact((Long) stringObjectMap.get("count"))).build())
                .toList();
    }

    @Cacheable
    @Override
    public PageResult<Trait> listTraitByTraitInfo(TraitSearchVO traitSearchVO) {
        QueryWrapper<Trait> queryWrapper = new QueryWrapper<>();
        String category = traitSearchVO.getCategory();
        String subcategory = traitSearchVO.getSubcategory();
        String sourceId = traitSearchVO.getSourceId();
        Page page = traitSearchVO.getPage();

        if (StringUtil.isNotEmpty(category) && StringUtil.isNotEqual(category.toLowerCase(), ALL_DATA_SYMBOL.toLowerCase())) {
            queryWrapper.lambda().eq(Trait::getCategory, category);
        }

        if (StringUtil.isNotEmpty(subcategory) && StringUtil.isNotEqual(subcategory.toLowerCase(), ALL_DATA_SYMBOL.toLowerCase())) {
            queryWrapper.lambda().eq(Trait::getSubcategory, subcategory);
        }

        if (StringUtil.isNotEmpty(sourceId) && StringUtil.isNotEqual(sourceId.toLowerCase(), ALL_DATA_SYMBOL.toLowerCase())) {
            checkSourceId(sourceId);
            queryWrapper.lambda().eq(Trait::getSourceId, sourceId);
        }

        setQueryWrapperByApply(page, queryWrapper);

        return PageResultUtil.format(page, () -> traitMapper.selectList(queryWrapper));
    }

    @Cacheable
    @Override
    public PageResult<Sample> listSampleBySearchSample(SampleSearchVO sampleSearchVO) {
        QueryWrapper<Sample> queryWrapper = new QueryWrapper<>();
        String tissueType = sampleSearchVO.getTissueType();
        String cellType = sampleSearchVO.getCellType();
        Page page = sampleSearchVO.getPage();

        if (StringUtil.isNotEmpty(tissueType) && StringUtil.isNotEqual(tissueType.toLowerCase(), ALL_DATA_SYMBOL.toLowerCase())) {
            queryWrapper.lambda().like(Sample::getTissueType, tissueType);
        }

        if (StringUtil.isNotEmpty(cellType) && StringUtil.isNotEqual(cellType.toLowerCase(), ALL_DATA_SYMBOL.toLowerCase())) {
            QueryWrapper<SampleCellType> sampleCellTypeQueryWrapper = new QueryWrapper<>();
            sampleCellTypeQueryWrapper.select("DISTINCT f_sample_id as sampleId");
            sampleCellTypeQueryWrapper.lambda().like(SampleCellType::getCellType, cellType);
            List<String> sampleIdList = sampleCellTypeMapper.selectList(sampleCellTypeQueryWrapper).stream().map(SampleCellType::getSampleId).toList();

            if (ListUtil.isEmpty(sampleIdList)) {
                setQueryWrapperByApply(page, queryWrapper);
                return PageResultUtil.format(page, () -> sampleMapper.selectList(queryWrapper));
            }

            queryWrapper.lambda().in(Sample::getSampleId, sampleIdList);
        }

        setQueryWrapperByApply(page, queryWrapper);

        return PageResultUtil.format(page, () -> sampleMapper.selectList(queryWrapper));
    }

    @Cacheable
    @Override
    public List<String> listGene() {
        LambdaQueryWrapper<GeneTraitCount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(GeneTraitCount::getGene);
        queryWrapper.orderByDesc(BaseTraitCount::getCount);
        List<GeneTraitCount> geneTraitCountList = geneTraitCountMapper.selectList(queryWrapper);
        return geneTraitCountList.stream().map(GeneTraitCount::getGene).distinct().toList();
    }

    @Cacheable
    @Override
    public List<String> listTf() {
        LambdaQueryWrapper<TfTraitCount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(TfTraitCount::getTf);
        queryWrapper.orderByDesc(BaseTraitCount::getCount);
        List<TfTraitCount> tfTraitCountList = tfTraitCountMapper.selectList(queryWrapper);
        List<String> tfList = tfTraitCountList.stream().map(TfTraitCount::getTf).distinct().toList();
        
        LambdaQueryWrapper<Tf> tfLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tfLambdaQueryWrapper.select(Tf::getTfName);
        tfLambdaQueryWrapper.in(Tf::getTfName, tfList);
        List<Tf> doubleTfList = tfMapper.selectList(tfLambdaQueryWrapper);
        return doubleTfList.stream().map(Tf::getTfName).toList();
    }

}
