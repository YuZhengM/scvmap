package com.spring.boot.service;

import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.SampleCellType;
import com.spring.boot.pojo.Trait;
import com.spring.boot.pojo.vo.SampleDataBrowseResultVO;
import com.spring.boot.pojo.vo.SampleDataBrowseVO;
import com.spring.boot.pojo.vo.TraitDataBrowseResultVO;
import com.spring.boot.pojo.vo.TraitDataBrowseVO;

import java.util.List;

public interface DataBrowseService {

    List<Trait> listTraitAllData();

    List<Sample> listSampleAllData();

    List<SampleCellType> listSampleCellTypeAllData();

    TraitDataBrowseResultVO traitDataBrowseData(TraitDataBrowseVO traitDataBrowseVO);

    SampleDataBrowseResultVO<Sample> sampleDataBrowseData(SampleDataBrowseVO sampleDataBrowseVO);

    SampleDataBrowseResultVO<SampleCellType> sampleCellTypeDataBrowseData(SampleDataBrowseVO sampleDataBrowseVO);
}
