package com.spring.boot.controller;

import com.spring.boot.pojo.Sample;
import com.spring.boot.pojo.Trait;
import com.spring.boot.service.DownloadService;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.Result;
import com.spring.boot.util.util.result.Page;
import com.spring.boot.util.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling download-related requests.
 *
 * @author Zhengmin Yu
 */
@RequestMapping("/download")
@CrossOrigin
@RestController
public class DownloadController {

    private DownloadService downloadService;

    /**
     * Default constructor for DownloadController.
     */
    public DownloadController() {
    }

    /**
     * Constructor with dependency injection for DownloadService.
     *
     * @param downloadService the service to be injected
     */
    @Autowired
    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    /**
     * Endpoint to retrieve a list of all traits.
     *
     * @return a Result object containing a list of Trait objects
     */
    @PostMapping("/trait")
    public Result<PageResult<Trait>> listTrait(Page page) {
        PageResult<Trait> traitList = downloadService.listTrait(page);
        return ResultUtil.success("Traits retrieved successfully", traitList);
    }

    /**
     * Endpoint to retrieve a list of all samples.
     *
     * @return a Result object containing a list of Sample objects
     */
    @GetMapping("/sample")
    public Result<List<Sample>> listSample() {
        List<Sample> sampleList = downloadService.listSample();
        return ResultUtil.success("Samples retrieved successfully", sampleList);
    }

}
