package com.spring.boot.controller;

import com.spring.boot.pojo.vo.HomeResultVO;
import com.spring.boot.service.HomeService;
import com.spring.boot.util.model.Result;
import com.spring.boot.util.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling home-related requests.
 * This controller provides endpoints for searching and retrieving home-related data.
 *
 * @author Zhengmin Yu
 */
@RequestMapping("/home")
@CrossOrigin
@RestController
public class HomeController {

    private HomeService homeService;

    /**
     * Default constructor.
     */
    public HomeController() {
    }

    /**
     * Constructor with dependency injection.
     *
     * @param homeService Service for handling home-related operations.
     */
    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    /**
     * Retrieves the ID of a home-related entity based on the provided label and content.
     *
     * @param label   The label or category of the content to search for.
     * @param content The content to search for.
     * @return Result containing the home-related data (HomeResultVO) if found.
     */
    @GetMapping("/search/{label}/{content}")
    private Result<HomeResultVO> getIdByContent(@PathVariable String label, @PathVariable String content) {
        HomeResultVO homeResultVO = homeService.getIdByContent(label, content);
        return ResultUtil.success("getIdByContent", homeResultVO);
    }

}