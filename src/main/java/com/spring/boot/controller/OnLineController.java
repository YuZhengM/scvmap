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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.spring.boot.util.constant.ApplicationConstant.JOB_ID_EXAMPLE;
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

    /**
     * Controller method to execute a user analysis function.
     *
     * @param request       HttpServletRequest object.
     * @param scFileId      The ID of the SC file.
     * @param variantFileId The ID of the variant file.
     * @param onLineVO      OnLineVO object containing analysis parameters.
     * @return A Result object containing the ID of the analysis job.
     */
    @Operation(
            summary = "Execute user analysis function",
            description = "Executes a user analysis function based on the provided SC file ID, variant file ID, and analysis parameters.",
            parameters = {
                    @Parameter(name = "sc_file_id", in = ParameterIn.PATH, description = "The ID of the SC file to analyze.", required = true),
                    @Parameter(name = "variant_file_id", in = ParameterIn.PATH, description = "The ID of the variant file to analyze.", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Analysis parameters for the user analysis function.", required = true
            )
    )
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

    /**
     * Controller method to get the status of a user analysis job.
     *
     * @param scFileId      The ID of the SC file.
     * @param variantFileId The ID of the variant file.
     * @param onLineVO      OnLineVO object containing job ID.
     * @return A Result object containing the status of the analysis job.
     */
    @Operation(
            summary = "Get user analysis job status",
            description = "Retrieves the status of a user analysis job based on the provided SC file ID, variant file ID, and job ID.",
            parameters = {
                    @Parameter(name = "sc_file_id", in = ParameterIn.PATH, description = "The ID of the SC file to analyze.", required = true),
                    @Parameter(name = "variant_file_id", in = ParameterIn.PATH, description = "The ID of the variant file to analyze.", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Analysis parameters for the user analysis function.", required = true
            )
    )
    @PostMapping("/user/job/status/{sc_file_id}/{variant_file_id}")
    private Result<UserOnLine> getJobStatusInfo(@PathVariable("sc_file_id") String scFileId,
                                                @PathVariable("variant_file_id") String variantFileId,
                                                @RequestBody OnLineVO onLineVO) {
        UserOnLine userOnLine = onLineService.getJobStatusInfo(scFileId, variantFileId, onLineVO);
        return ResultUtil.success("[getJobStatusInfo]: Retrieve the status of a home-related entity.", userOnLine);
    }

    /**
     * Controller method to list user analysis jobs.
     *
     * @param page Page object containing pagination information.
     * @return A Result object containing a page of user analysis jobs.
     */
    @Operation(
            summary = "List user analysis jobs",
            description = "Retrieves a paginated list of user analysis jobs based on the provided pagination information.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Analysis parameters for the user analysis function.", required = true
            )
    )
    @PostMapping("/user/job/list")
    private Result<PageResult<UserOnLine>> listUserJob(@RequestBody Page page) {
        PageResult<UserOnLine> userOnLinePageResult = onLineService.listUserJob(page);
        return ResultUtil.success("[listUserJob]: listUserJob.", userOnLinePageResult);
    }

    /**
     * Controller method to get user analysis job details.
     *
     * @param job_id The ID of the analysis job.
     * @return A Result object containing the details of the analysis job.
     */
    @Operation(
            summary = "Get user analysis job details",
            description = "Retrieves the details of a user analysis job based on the provided job ID.",
            parameters = {
                    @Parameter(name = "job_id", in = ParameterIn.PATH, description = "The ID of the analysis job to retrieve details for.", required = true, example = JOB_ID_EXAMPLE)
            }
    )
    @GetMapping("/user/job/{job_id}")
    private Result<UserOnLine> getUserJobInfo(@PathVariable("job_id") String job_id) {
        UserOnLine userOnLine = onLineService.getUserJobInfo(job_id);
        return ResultUtil.success("[getUserJobInfo]: getUserJobInfo.", userOnLine);
    }

    /**
     * Controller method to get the analysis results of a user analysis job.
     *
     * @param job_id The ID of the analysis job.
     * @return A Result object containing a list of maps with analysis results.
     */
    @Operation(
            summary = "Get user analysis job results",
            description = "Retrieves the analysis results of a user analysis job based on the provided job ID.",
            parameters = {
                    @Parameter(name = "job_id", in = ParameterIn.PATH, description = "The ID of the analysis job to retrieve results for.", required = true, example = JOB_ID_EXAMPLE)
            }
    )
    @GetMapping("/job/result/analysis/{job_id}")
    private Result<List<Map<String, String>>> jobResultAnalysis(@PathVariable("job_id") String job_id) {
        List<Map<String, String>> mapList = onLineService.jobResultAnalysis(job_id);
        return ResultUtil.success("[jobResultAnalysis]: jobResultAnalysis.", mapList);
    }

    /**
     * Controller method to get the column names of the analysis results of a user analysis job.
     *
     * @param job_id The ID of the analysis job.
     * @return A Result object containing a list of column names.
     */
    @Operation(
            summary = "Get user analysis job result columns",
            description = "Retrieves the column names of the analysis results of a user analysis job based on the provided job ID.",
            parameters = {
                    @Parameter(name = "job_id", in = ParameterIn.PATH, description = "The ID of the analysis job to retrieve result columns for.", required = true, example = JOB_ID_EXAMPLE)
            }
    )
    @GetMapping("/job/result/column/{job_id}")
    private Result<List<String>> getJobResultColumns(@PathVariable("job_id") String job_id) {
        List<String> columnList = onLineService.getJobResultColumns(job_id);
        return ResultUtil.success("[getJobResultColumns]: getJobResultColumns.", columnList);
    }

    /**
     * Controller method to get the cluster coordinates of a user analysis job.
     *
     * @param jobId    The ID of the analysis job.
     * @param axis1    The name of the first axis.
     * @param axis2    The name of the second axis.
     * @param cellType The type of cell to filter.
     * @return A Result object containing the cluster coordinates.
     */
    @Operation(
            summary = "Get user analysis job cluster coordinates",
            description = "Retrieves the cluster coordinates of a user analysis job based on the provided job ID, axis names, and cell type.",
            parameters = {
                    @Parameter(name = "jobId", in = ParameterIn.PATH, description = "The ID of the analysis job to retrieve cluster coordinates for.", required = true, example = JOB_ID_EXAMPLE),
                    @Parameter(name = "axis1", in = ParameterIn.PATH, description = "The name of the first axis.", required = true, example = "PC1"),
                    @Parameter(name = "axis2", in = ParameterIn.PATH, description = "The name of the second axis.", required = true, example = "PC2"),
                    @Parameter(name = "cellType", in = ParameterIn.PATH, description = "The type of cell to filter.", required = true, example = "cell_type")
            }
    )
    @GetMapping("/cluster_coordinate/cluster/{job_id}/{axis1}/{axis2}/{cell_type}")
    public Result<PlotlyClusterData<Double, Double>> listClusterCoordinate(@PathVariable("job_id") String jobId,
                                                                           @PathVariable("axis1") String axis1,
                                                                           @PathVariable("axis2") String axis2,
                                                                           @PathVariable("cell_type") String cellType) {
        PlotlyClusterData<Double, Double> plotlyClusterData = onLineService.listClusterCoordinate(jobId, axis1, axis2, cellType);
        return ResultUtil.success("[listClusterCoordinate]: Query result", plotlyClusterData);
    }

    /**
     * Controller method to get the cluster coordinates of a user analysis job based on a specified method.
     *
     * @param jobId    The ID of the analysis job.
     * @param method   The method to use for clustering.
     * @param axis1    The name of the first axis.
     * @param axis2    The name of the second axis.
     * @param cellType The type of cell to filter.
     * @return A Result object containing the cluster coordinates.
     */
    @Operation(
            summary = "Get user analysis job cluster coordinates by method",
            description = "Retrieves the cluster coordinates of a user analysis job based on the provided job ID, method, axis names, and cell type.",
            parameters = {
                    @Parameter(name = "jobId", in = ParameterIn.PATH, description = "The ID of the analysis job to retrieve cluster coordinates for.", required = true, example = JOB_ID_EXAMPLE),
                    @Parameter(name = "method", in = ParameterIn.PATH, description = "The method to use for clustering.", required = true, example = "kmeans"),
                    @Parameter(name = "axis1", in = ParameterIn.PATH, description = "The name of the first axis.", required = true, example = "PC1"),
                    @Parameter(name = "axis2", in = ParameterIn.PATH, description = "The name of the second axis.", required = true, example = "PC2"),
                    @Parameter(name = "cellType", in = ParameterIn.PATH, description = "The type of cell to filter.", required = true, example = "cell_type")
            }
    )
    @GetMapping("/cluster_coordinate/cell_score/{job_id}/{method}/{axis1}/{axis2}/{cell_type}")
    public Result<PlotlyClusterData<Double, Double>> listJobClusterCoordinate(@PathVariable("job_id") String jobId,
                                                                              @PathVariable("method") String method,
                                                                              @PathVariable("axis1") String axis1,
                                                                              @PathVariable("axis2") String axis2,
                                                                              @PathVariable("cell_type") String cellType) {
        PlotlyClusterData<Double, Double> plotlyClusterData = onLineService.listJobClusterCoordinate(jobId, method, axis1, axis2, cellType);
        return ResultUtil.success("[listJobClusterCoordinate]: Query result", plotlyClusterData);
    }

}
