package com.spring.boot.controller;

import com.spring.boot.pojo.UserOnLine;
import com.spring.boot.pojo.vo.OnLineVO;
import com.spring.boot.service.OnLineService;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.Result;
import com.spring.boot.util.model.vo.plotly.PlotlyClusterData;
import com.spring.boot.util.util.StringUtil;
import com.spring.boot.util.util.result.Page;
import com.spring.boot.util.util.result.ResultUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.spring.boot.util.util.ApplicationUtil.*;

@RequestMapping("/on_line")
@CrossOrigin
@RestController
@Tag(name = "OnLine-API", description = "Controller for handling home-related requests")
public class OnLineController {


    private OnLineService onLineService;

    /**
     * Default constructor.
     */
    public OnLineController() {
    }

    /**
     * Constructor with dependency injection.
     *
     * @param onLineService Service for handling on-line-related operations.
     */
    @Autowired
    public OnLineController(OnLineService onLineService) {
        this.onLineService = onLineService;
    }


    @PostMapping("/user/analysis/{sc_file_id}/{variant_file_id}")
    private Result<String> execUserAnalysisFunction(HttpServletRequest request,
                                                    @PathVariable("sc_file_id") String scFileId,
                                                    @PathVariable("variant_file_id") String variantFileId,
                                                    @RequestBody OnLineVO onLineVO) throws IOException {
        String jobId = StringUtil.getUniqueId10();
        onLineVO.setJobId(jobId);
        onLineVO.setUserIp(getUserIp(request));
        onLineService.execUserAnalysisFunction(scFileId, variantFileId, onLineVO);
        return ResultUtil.success("[execUserAnalysisFunction]: Retrieve the ID of a home-related entity.", jobId);
    }

    @PostMapping("/user/job/status/{sc_file_id}/{variant_file_id}")
    private Result<UserOnLine> getJobStatusInfo(@PathVariable("sc_file_id") String scFileId,
                                                @PathVariable("variant_file_id") String variantFileId,
                                                @RequestBody OnLineVO onLineVO) {
        UserOnLine userOnLine = onLineService.getJobStatusInfo(scFileId, variantFileId, onLineVO);
        return ResultUtil.success("[getJobStatusInfo]: Retrieve the status of a home-related entity.", userOnLine);
    }

    @PostMapping("/user/job/list")
    private Result<PageResult<UserOnLine>> listUserJob(@RequestBody Page page) {
        PageResult<UserOnLine> userOnLinePageResult = onLineService.listUserJob(page);
        return ResultUtil.success("[listUserJob]: listUserJob.", userOnLinePageResult);
    }

    @GetMapping("/user/job/{job_id}")
    private Result<UserOnLine> getUserJobInfo(@PathVariable("job_id") String job_id) {
        UserOnLine userOnLine = onLineService.getUserJobInfo(job_id);
        return ResultUtil.success("[getUserJobInfo]: getUserJobInfo.", userOnLine);
    }

    @GetMapping("/job/result/analysis/{job_id}")
    private Result<List<Map<String, String>>> jobResultAnalysis(@PathVariable("job_id") String job_id) {
        List<Map<String, String>> mapList = onLineService.jobResultAnalysis(job_id);
        return ResultUtil.success("[jobResultAnalysis]: jobResultAnalysis.", mapList);
    }

    @GetMapping("/job/result/column/{job_id}")
    private Result<List<String>> getJobResultColumns(@PathVariable("job_id") String job_id) {
        List<String> columnList = onLineService.getJobResultColumns(job_id);
        return ResultUtil.success("[getJobResultColumns]: getJobResultColumns.", columnList);
    }

    @GetMapping("/cluster_coordinate/cluster/{job_id}/{axis1}/{axis2}/{cell_type}")
    public Result<PlotlyClusterData<Double, Double>> listClusterCoordinate(@PathVariable("job_id") String jobId,
                                                                           @PathVariable("axis1") String axis1,
                                                                           @PathVariable("axis2") String axis2,
                                                                           @PathVariable("cell_type") String cellType) {
        PlotlyClusterData<Double, Double> plotlyClusterData = onLineService.listClusterCoordinate(jobId, axis1, axis2, cellType);
        return ResultUtil.success("[listClusterCoordinate]: Query result", plotlyClusterData);
    }

    @GetMapping("/cluster_coordinate/cell_score/{job_id}/{method}/{axis1}/{axis2}/{cell_type}")
    public Result<PlotlyClusterData<Double, Double>> listJobClusterCoordinate(@PathVariable("job_id") String jobId,
                                                                              @PathVariable("method") String method,
                                                                              @PathVariable("axis1") String axis1,
                                                                              @PathVariable("axis2") String axis2,
                                                                              @PathVariable("cell_type") String cellType) throws IOException {
        PlotlyClusterData<Double, Double> plotlyClusterData = onLineService.listJobClusterCoordinate(jobId, method, axis1, axis2, cellType);
        return ResultUtil.success("[listJobClusterCoordinate]: Query result", plotlyClusterData);
    }

}
