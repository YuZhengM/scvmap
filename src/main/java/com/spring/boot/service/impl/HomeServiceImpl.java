package com.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.boot.mapper.SampleMapper;
import com.spring.boot.mapper.TraitMapper;
import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.Trait;
import com.spring.boot.pojo.vo.HomeResultVO;
import com.spring.boot.service.HomeService;
import com.spring.boot.util.constant.SystemException;
import com.spring.boot.util.exception.RunException;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.util.StringUtil;
import com.spring.boot.util.util.result.Page;
import com.spring.boot.util.util.result.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.spring.boot.util.constant.ApplicationConstant.HOME_DEFAULT_SAMPLE;
import static com.spring.boot.util.constant.ApplicationConstant.HOME_DEFAULT_TRAIT;
import static com.spring.boot.util.util.result.PageUtil.setQueryWrapperByApply;

/**
 * Homepage interface information
 *
 * @author Zhengmin Yu
 */
@CacheConfig(cacheNames = "home", keyGenerator = "cacheKeyGenerator")
@Service
public class HomeServiceImpl implements HomeService {

    private TraitMapper traitMapper;
    private SampleMapper sampleMapper;

    public HomeServiceImpl() {
    }

    @Autowired
    public HomeServiceImpl(TraitMapper traitMapper, SampleMapper sampleMapper) {
        this.traitMapper = traitMapper;
        this.sampleMapper = sampleMapper;
    }

    /**
     * Retrieves and caches a HomeResultVO based on the provided label and content.
     * The method handles different cases for 'trait' and 'sample' labels.
     *
     * @param label   the label to determine the type of content ('trait' or 'sample')
     * @param content the content to search for
     * @return a HomeResultVO containing the search result
     */
    @Cacheable
    @Override
    public HomeResultVO getIdByContent(String label, String content, Page page) {
        String searchContent = content.replaceAll("( disease.*| Disease.*|'s|’s)", "");

        switch (label) {
            case "trait":
                // Directly return if content starts with 'trait_id'
                if (content.startsWith("trait_id")) {
                    return HomeResultVO.builder().name("trait").content(content).build();
                }

                // Create a query wrapper forTrait
                QueryWrapper<Trait> queryWrapper = new QueryWrapper<>();

                // Add conditions to search for traits by name or abbreviation
                if (StringUtil.isNotEqual(HOME_DEFAULT_TRAIT, content)) {
                    queryWrapper.lambda().like(Trait::getTrait, searchContent).or().like(Trait::getTraitAbbr, searchContent);
                }

                setQueryWrapperByApply(page, queryWrapper);
                queryWrapper.lambda().orderByAsc(Trait::getTraitIndex);
                // Execute the query and retrieve the list of traits
                PageResult<Trait> traitList = PageResultUtil.format(page, () -> traitMapper.selectList(queryWrapper));
                // Build and return the result VO with the trait list
                return HomeResultVO.builder().name("traitList").content(content).dataList(traitList).build();
            case "sample":
                // Directly return if content starts with 'sample_id'
                if (content.startsWith("sample_id")) {
                    return HomeResultVO.builder().name("sample").content(content).build();
                }

                // Create a query wrapper for Sample
                QueryWrapper<Sample> sampleQueryWrapper = new QueryWrapper<>();

                // Add conditions to search for samples by label, tissue type, health type, or health type description
                if (StringUtil.isNotEqual(HOME_DEFAULT_SAMPLE, content)) {
                    sampleQueryWrapper.lambda().like(Sample::getLabel, searchContent)
                            .or().like(Sample::getTissueType, searchContent)
                            .or().like(Sample::getHealthTypeDescription, searchContent);
                }

                setQueryWrapperByApply(page, sampleQueryWrapper);
                sampleQueryWrapper.lambda().orderByAsc(Sample::getFIndex);
                // Execute the query and retrieve the list of samples
                PageResult<Sample> sampleList = PageResultUtil.format(page, () -> sampleMapper.selectList(sampleQueryWrapper));
                // Build and return the result VO with the sample list
                return HomeResultVO.builder().name("sampleList").content(content).dataList(sampleList).build();
            default:
                // Throw an exception if the label is neither 'trait' nor 'sample'
                throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }
    }
}
