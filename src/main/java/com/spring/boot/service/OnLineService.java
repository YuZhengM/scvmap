package com.spring.boot.service;

import com.spring.boot.pojo.UserOnLine;
import com.spring.boot.pojo.vo.OnLineVO;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.vo.plotly.PlotlyClusterData;
import com.spring.boot.util.util.result.Page;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface OnLineService {

    void execUserAnalysisFunction(String scFileId, String variantFileId, OnLineVO onLineVO) throws IOException;

    PageResult<UserOnLine> listUserJob(Page page);

    UserOnLine getUserJobInfo(String jobId);

    UserOnLine getJobStatusInfo(String scFileId, String variantFileId, OnLineVO onLineVO);

    List<Map<String, String>> jobResultAnalysis(String jobId);

    PlotlyClusterData<Double, Double> listJobClusterCoordinate(String jobId, String method, String axis1, String axis2, String cellType);

    PlotlyClusterData<Double, Double> listClusterCoordinate(String jobId, String axis1, String axis2, String cellType);

    List<String> getJobResultColumns(String jobId);
}
