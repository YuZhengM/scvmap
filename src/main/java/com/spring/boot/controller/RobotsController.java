package com.spring.boot.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RobotsController {

    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    public String robotsTxt() {
        return """
                User-agent: *
                Allow: /test/
                Disallow: /home/
                Disallow: /data_browse/
                Disallow: /search/
                Disallow: /analysis/
                Disallow: /download/
                Disallow: /detail/
                Disallow: /variant/
                Disallow: /element/
                """;
    }

}
