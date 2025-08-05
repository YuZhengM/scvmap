package com.spring.boot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for robots.txt file
 *
 * @author Zhengmin Yu
 */
@RequestMapping
@CrossOrigin
@RestController
@Tag(name = "Robots-API", description = "Controller for handling robots.txt file requests")
public class RobotsController {

    /**
     * Default constructor.
     */
    public RobotsController() {
    }

    /**
     * Get the content of the robots.txt file
     *
     * @return Returns the text content of the robots.txt file
     */
    @Operation(summary = "Get robots.txt content", description = "Retrieves the content of the robots.txt file.")
    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    public String robotsTxt() {
        return """
                User-agent: *
                Allow: /test/
                Allow: /home/
                Allow: /data_browse/
                Allow: /search/
                Allow: /analysis/
                Allow: /download/
                Allow: /detail/
                Allow: /variant/
                Allow: /element/
                """;
    }

}
