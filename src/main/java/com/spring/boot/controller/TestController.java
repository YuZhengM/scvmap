package com.spring.boot.controller;

import com.spring.boot.util.model.Result;
import com.spring.boot.util.util.result.ResultUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test API Controller
 *
 * @author Zhengmin Yu
 */
@CrossOrigin
@RestController
public class TestController {

    /**
     * Test connection endpoint
     *
     * @return Result containing a success message
     */
    @GetMapping("/test")
    public Result<String> test() {
        return ResultUtil.success("Test", "Connection test successful!");
    }

}
