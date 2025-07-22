package com.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.boot.mapper.SampleMapper;
import com.spring.boot.mapper.TraitMapper;
import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.Trait;
import com.spring.boot.service.DownloadService;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.util.StringUtil;
import com.spring.boot.util.util.result.Page;
import com.spring.boot.util.util.result.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Service implementation for downloading data.
 * This class provides methods to fetch trait and sample data, with results cached for performance.
 *
 * @author Zhengmin Yu
 */
@CacheConfig(cacheNames = "download", keyGenerator = "cacheKeyGenerator")
@Service
public class DownloadServiceImpl implements DownloadService {

    private SampleMapper sampleMapper;
    private TraitMapper traitMapper;

    /**
     * Default constructor for DownloadServiceImpl.
     */
    public DownloadServiceImpl() {
    }

    /**
     * Constructor with SampleMapper and TraitMapper injection.
     *
     * @param sampleMapper the mapper for Sample data
     * @param traitMapper  the mapper for Trait data
     */
    @Autowired
    public DownloadServiceImpl(SampleMapper sampleMapper, TraitMapper traitMapper) {
        this.sampleMapper = sampleMapper;
        this.traitMapper = traitMapper;
    }

    /**
     * Retrieves and caches a list of Trait objects.
     * This method fetches trait data with specific fields: traitId, traitCode, traitAbbr, trait, and type.
     *
     * @return a list of Trait objects
     */
    @Cacheable
    @Override
    public PageResult<Trait> listTrait(Page page) {
        // Create a new LambdaQueryWrapper for Trait
        QueryWrapper<Trait> queryWrapper = new QueryWrapper<>();
        // Specify the fields to select: traitId, traitCode, traitAbbr, trait, and type
        queryWrapper.select("f_trait_id as traitId", "f_trait_code as traitCode",
                "f_trait_abbr as traitAbbr", "f_trait as trait", "f_type as type");

        if (StringUtil.isNotEmpty(page.getSearchField()) && !Objects.isNull(page.getContent()) && !page.getContent().isEmpty()) {
            queryWrapper.eq(page.getSearchField(), page.getContent());
        }

        queryWrapper.orderByAsc("f_trait_index");
        return PageResultUtil.format(page, () -> traitMapper.selectList(queryWrapper));
    }

    /**
     * Retrieves and caches a list of Sample objects.
     * This method fetches sample data with specific fields: sampleId, label, tissueType, healthTypeDescription, and gseId.
     *
     * @return a list of Sample objects
     */
    @Cacheable
    @Override
    public List<Sample> listSample() {
        // Create a new LambdaQueryWrapper for Sample
        LambdaQueryWrapper<Sample> queryWrapper = new LambdaQueryWrapper<>();
        // Specify the fields to select: sampleId, label, tissueType, healthTypeDescription, and gseId
        queryWrapper.select(Sample::getSampleId, Sample::getLabel, Sample::getTissueType, Sample::getHealthTypeDescription, Sample::getGseId);
        queryWrapper.orderByAsc(Sample::getFIndex);
        // Execute the select query and return the list of Sample objects
        return sampleMapper.selectList(queryWrapper);
    }
}
