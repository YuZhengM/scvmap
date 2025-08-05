package com.spring.boot.controller;

import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.SampleCellType;
import com.spring.boot.pojo.vo.SampleDataBrowseResultVO;
import com.spring.boot.pojo.vo.SampleDataBrowseVO;
import com.spring.boot.pojo.vo.TraitDataBrowseResultVO;
import com.spring.boot.pojo.vo.TraitDataBrowseVO;
import com.spring.boot.service.DataBrowseService;
import com.spring.boot.util.model.Result;
import com.spring.boot.util.util.result.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for data browsing operations.
 *
 * @author Zhengmin Yu
 */
@RequestMapping("/data_browse")
@CrossOrigin
@RestController
@Tag(name = "Data-Browse-API", description = "Controller for handling data browsing-related requests")
public class DataBrowseController {

    private DataBrowseService dataBrowseService;

    /**
     * Default constructor.
     */
    public DataBrowseController() {
    }

    /**
     * Constructor with DataBrowseService dependency injection.
     *
     * @param dataBrowseService the service to be injected
     */
    @Autowired
    public DataBrowseController(DataBrowseService dataBrowseService) {
        this.dataBrowseService = dataBrowseService;
    }

    /**
     * Endpoint for browsing trait data.
     *
     * @param traitDataBrowseVO the view object containing the parameters for trait data browsing
     * @return a result object containing the browsing result
     */
    @Operation(
            summary = "Browse trait data",
            description = "Retrieves trait data based on the provided parameters.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "View object containing the parameters for trait data browsing.",
                    required = true
            )
    )
    @PostMapping("/trait")
    public Result<TraitDataBrowseResultVO> traitDataBrowseData(@RequestBody TraitDataBrowseVO traitDataBrowseVO) {
        TraitDataBrowseResultVO traitDataBrowseData = dataBrowseService.traitDataBrowseData(traitDataBrowseVO);
        return ResultUtil.success("Trait select success", traitDataBrowseData);
    }

    /**
     * Endpoint for browsing sample data.
     *
     * @param sampleDataBrowseVO the view object containing the parameters for sample data browsing
     * @return a result object containing the browsing result
     */
    @Operation(
            summary = "Browse sample data",
            description = "Retrieves sample data based on the provided parameters.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "View object containing the parameters for sample data browsing.",
                    required = true
            )
    )
    @PostMapping("/sample")
    public Result<SampleDataBrowseResultVO<Sample>> sampleDataBrowseData(@RequestBody SampleDataBrowseVO sampleDataBrowseVO) {
        SampleDataBrowseResultVO<Sample> sampleDataBrowseResultVO = dataBrowseService.sampleDataBrowseData(sampleDataBrowseVO);
        return ResultUtil.success("Sample select success", sampleDataBrowseResultVO);
    }

    /**
     * Endpoint for browsing sample cell type data.
     *
     * @param sampleDataBrowseVO the view object containing the parameters for sample cell type data browsing
     * @return a result object containing the browsing result
     */
    @Operation(
            summary = "Browse sample cell type data",
            description = "Retrieves sample cell type data based on the provided parameters.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "View object containing the parameters for sample cell type data browsing.",
                    required = true
            )
    )
    @PostMapping("/sample_cell_type")
    public Result<SampleDataBrowseResultVO<SampleCellType>> sampleCellTypeDataBrowseData(@RequestBody SampleDataBrowseVO sampleDataBrowseVO) {
        SampleDataBrowseResultVO<SampleCellType> sampleDataBrowseResultVO = dataBrowseService.sampleCellTypeDataBrowseData(sampleDataBrowseVO);
        return ResultUtil.success("Sample cell type select success", sampleDataBrowseResultVO);
    }

}
