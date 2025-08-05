package com.spring.boot.controller;

import com.spring.boot.pojo.vo.HomeResultVO;
import com.spring.boot.service.HomeService;
import com.spring.boot.util.model.Result;
import com.spring.boot.util.util.result.Page;
import com.spring.boot.util.util.result.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Home-API", description = "Controller for handling home-related requests")
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
    @Operation(
            summary = "Retrieve the ID of a home-related entity",
            description = "Retrieves the ID of a home-related entity based on the provided label and content.",
            parameters = {
                    @Parameter(
                            name = "label",
                            in = ParameterIn.PATH,
                            description = "The label or category of the content to search for.",
                            required = true,
                            example = "trait"
                    ),
                    @Parameter(
                            name = "content",
                            in = ParameterIn.PATH,
                            description = "The content to search for.",
                            required = true,
                            example = "Alzheimer's disease"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Pagination information for the search.",
                    required = true
            )
    )
    @PostMapping("/search/{label}/{content}")
    private Result<HomeResultVO> getSearchResultByContent(@PathVariable String label, @PathVariable String content, @RequestBody Page page) {
        HomeResultVO homeResultVO = homeService.getSearchResultByContent(label, content, page);
        return ResultUtil.success("[getSearchResultByContent]: Retrieve the ID of a home-related entity.", homeResultVO);
    }

}
