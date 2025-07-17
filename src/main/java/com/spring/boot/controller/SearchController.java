package com.spring.boot.controller;

import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.Source;
import com.spring.boot.pojo.Trait;
import com.spring.boot.pojo.vo.SampleSearchVO;
import com.spring.boot.pojo.vo.TraitSearchVO;
import com.spring.boot.service.SearchService;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.Result;
import com.spring.boot.util.model.vo.FieldNumber;
import com.spring.boot.util.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling search-related requests.
 * This controller provides endpoints for searching and retrieving data related to sources, traits, samples, genes, and transcription factors (TFs).
 *
 * @author Zhengmin Yu
 */
@RequestMapping("/search")
@CrossOrigin
@RestController
public class SearchController {

    private SearchService searchService;

    /**
     * Default constructor.
     */
    public SearchController() {
    }

    /**
     * Constructor with dependency injection.
     *
     * @param searchService Service for handling search-related operations.
     */
    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Retrieves a list of all sources.
     *
     * @return Result containing the list of sources.
     */
    @GetMapping("/source/list")
    public Result<List<Source>> listSourceInfo() {
        List<Source> sourceList = searchService.listSourceInfo();
        return ResultUtil.success("Source information", sourceList);
    }

    /**
     * Retrieves a list of sources based on a list of trait IDs.
     *
     * @param traitIdList List of trait IDs to filter sources.
     * @return Result containing the list of sources matching the provided trait IDs.
     */
    @PostMapping("/source/trait_id_list")
    public Result<List<Source>> listSourceInfoByTraitIdList(@RequestBody List<String> traitIdList) {
        List<Source> sourceList = searchService.listSourceInfoByTraitIdList(traitIdList);
        return ResultUtil.success("Source information", sourceList);
    }

    /**
     * Retrieves a list of trait categories with their counts.
     *
     * @return Result containing the list of trait categories and their counts.
     */
    @GetMapping("/trait/category/list")
    public Result<List<FieldNumber>> listCategory() {
        List<FieldNumber> categoryList = searchService.listCategory();
        return ResultUtil.success("Category information", categoryList);
    }

    /**
     * Retrieves a list of subcategories for a given trait category.
     *
     * @param category The category to filter subcategories.
     * @return Result containing the list of subcategories for the specified category.
     */
    @GetMapping("/trait/subcategory/list/{category}")
    public Result<List<FieldNumber>> listSubcategoryByCategory(@PathVariable String category) {
        List<FieldNumber> subcategoryList = searchService.listSubcategoryByCategory(category);
        return ResultUtil.success("Subcategory information", subcategoryList);
    }

    /**
     * Retrieves a list of cell types with their counts.
     *
     * @return Result containing the list of cell types and their counts.
     */
    @GetMapping("/sample/cell_type/list")
    public Result<List<FieldNumber>> listCellType() {
        List<FieldNumber> cellTypeList = searchService.listCellType();
        return ResultUtil.success("CellType information", cellTypeList);
    }

    /**
     * Retrieves a list of tissue types with their counts.
     *
     * @return Result containing the list of tissue types and their counts.
     */
    @GetMapping("/sample/tissue_type/list")
    public Result<List<FieldNumber>> listTissueType() {
        List<FieldNumber> tissueTypeList = searchService.listTissueType();
        return ResultUtil.success("TissueType information", tissueTypeList);
    }

    /**
     * Retrieves a paginated list of traits based on search criteria.
     *
     * @param traitSearchVO The search criteria for traits.
     * @return Result containing the paginated list of traits matching the search criteria.
     */
    @PostMapping("/result/trait")
    public Result<PageResult<Trait>> listTraitBySearchTrait(@RequestBody TraitSearchVO traitSearchVO) {
        PageResult<Trait> traitPageResult = searchService.listTraitByTraitInfo(traitSearchVO);
        return ResultUtil.success("Trait information", traitPageResult);
    }

    /**
     * Retrieves a paginated list of samples based on search criteria.
     *
     * @param sampleSearchVO The search criteria for samples.
     * @return Result containing the paginated list of samples matching the search criteria.
     */
    @PostMapping("/result/sample")
    public Result<PageResult<Sample>> listSampleBySearchSample(@RequestBody SampleSearchVO sampleSearchVO) {
        PageResult<Sample> samplePageResult = searchService.listSampleBySearchSample(sampleSearchVO);
        return ResultUtil.success("Sample information", samplePageResult);
    }

    /**
     * Retrieves a list of all genes.
     *
     * @return Result containing the list of genes.
     */
    @GetMapping("/gene/list")
    public Result<List<String>> listGene() {
        List<String> geneList = searchService.listGene();
        return ResultUtil.success("Gene information", geneList);
    }

    /**
     * Retrieves a list of all transcription factors (TFs).
     *
     * @return Result containing the list of TFs.
     */
    @GetMapping("/tf/list")
    public Result<List<String>> listTf() {
        List<String> tfList = searchService.listTf();
        return ResultUtil.success("TF information", tfList);
    }

}
