package com.spring.boot.controller;

import com.spring.boot.util.model.Result;
import com.spring.boot.util.util.result.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Test-API", description = "Test API Controller")
public class TestController {

    /**
     * Test connection endpoint
     *
     * @return Result containing a success message
     */
    @GetMapping("/test")
    @Operation(summary = "Test Connection API", description = "Used to test if the system connection is normal and returns a success message")
    public Result<String> test() {
        return ResultUtil.success("Test", "Connection test successful!");
    }

}
