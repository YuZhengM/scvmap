package com.spring.boot.service;

import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.Source;
import com.spring.boot.pojo.Trait;
import com.spring.boot.pojo.vo.SampleSearchVO;
import com.spring.boot.pojo.vo.TraitSearchVO;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.vo.FieldNumber;

import java.util.List;

/**
 * @author Administrator
 */
public interface SearchService {

    List<Source> listSourceInfo();

    List<Source> listSourceInfoByTraitIdList(List<String> traitIdList);

    List<Trait> listTraitBySourceId(String sourceId);

    List<Source> listSourceInfoBySourceIdList(List<String> sourceIdList);

    List<Sample> listSample();

    List<FieldNumber> listCategory();

    List<FieldNumber> listSubcategoryByCategory(String category);

    List<FieldNumber> listCellType();

    List<FieldNumber> listTissueType();

    PageResult<Trait> listTraitByTraitInfo(TraitSearchVO traitSearchVO);

    PageResult<Sample> listSampleBySearchSample(SampleSearchVO sampleSearchVO);

    List<String> listGene();

    List<String> listTf();
}
