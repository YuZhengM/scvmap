package com.spring.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.spring.boot.config.bean.ExecLinux;
import com.spring.boot.config.bean.ExecLinux2;
import com.spring.boot.config.bean.Path;
import com.spring.boot.mapper.UserOnLineMapper;
import com.spring.boot.pojo.UserOnLine;
import com.spring.boot.pojo.vo.OnLineVO;
import com.spring.boot.service.OnLineService;
import com.spring.boot.util.constant.SystemException;
import com.spring.boot.util.exception.RunException;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import com.spring.boot.util.model.LinuxResult;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.model.vo.plotly.PlotlyClusterData;
import com.spring.boot.util.model.vo.plotly.PlotlyData;
import com.spring.boot.util.util.EmailUtil;
import com.spring.boot.util.util.FileUtil;
import com.spring.boot.util.util.StringUtil;
import com.spring.boot.util.util.result.Page;
import com.spring.boot.util.util.result.PageResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.spring.boot.util.constant.ApplicationConstant.CHECK_EMAIL;
import static com.spring.boot.util.util.result.PageUtil.setQueryWrapperByApply;

@Service
public class OnLineServiceImpl implements OnLineService {

    private static final Log log = LogFactory.getLog(OnLineServiceImpl.class);

    private static final String START_TEMPLATE_HTML = "/html/startRun.html";
    private static final String END_TEMPLATE_HTML = "/html/result.html";
    private static final String ERROR_TEMPLATE_HTML = "/html/error.html";
    private static final String CHECK_TEMPLATE_HTML = "/html/check.html";

    private ExecLinux execLinux;
    private ExecLinux2 execLinux2;
    private Path path;
    private UserOnLineMapper userOnLineMapper;
    private JavaMailSenderImpl javaMailSender;

    public OnLineServiceImpl() {
    }

    @Autowired
    public OnLineServiceImpl(ExecLinux execLinux, ExecLinux2 execLinux2, Path path, UserOnLineMapper userOnLineMapper, JavaMailSenderImpl javaMailSender) {
        this.execLinux = execLinux;
        this.execLinux2 = execLinux2;
        this.path = path;
        this.userOnLineMapper = userOnLineMapper;
        this.javaMailSender = javaMailSender;
    }

    private String connectHost2Core() {
        return execLinux2.getUsername() + "@" + execLinux2.getHost();
    }

    private String startTransferFile(String sourceFile, String targetFile) {
        return "scp -P " + execLinux2.getPort() + " " + sourceFile + " " + connectHost2Core() + ":" + targetFile + "\n";
    }

    private String getExecResultFile(String resultFile, String targetFile) {
        return "scp -P " + execLinux2.getPort() + " " + connectHost2Core() + ":" + resultFile + " " + targetFile + "\n";
    }

    private String execCoreCode(String execStr) {
        return "ssh -p " + execLinux2.getPort() + " " + connectHost2Core() + " \"" + execStr + "\"" + "\n";
    }

    @Override
    public void execUserAnalysisFunction(String scFileId, String variantFileId, OnLineVO onLineVO) throws IOException {

        String jobId = onLineVO.getJobId();
        String email = onLineVO.getEmail();

        String workPath = path.getWorkPath();
        String userPath = workPath + "/user/";

        String execFilePath = path.getExecFilePath();
        String execCodeFilePath = execFilePath + "/code/";
        String execScATACFilePath = execFilePath + "/scATAC/";
        String execVariantFilePath = execFilePath + "/variant/";
        String execResultFilePath = execFilePath + "/result/" + jobId + "/";

        EmailUtil.sendEmail(javaMailSender, CHECK_EMAIL, jobId, CHECK_TEMPLATE_HTML);

        UserOnLine userOnLine = UserOnLine.builder()
                .jobId(jobId)
                .userEmail(email)
                .jobStatus("In progress (The first stage)")
                .scFileId(scFileId)
                .variantFileId(variantFileId)
                .genome(onLineVO.getGenome())
                .layer(onLineVO.getLayer())
                .userIp(onLineVO.getUserIp())
                .createTime(new Date())
                .updateTime(new Date()).build();
        log.info("[execUserAnalysisFunction]: Add task information for user execution. [{}]", userOnLine.toString());
        userOnLineMapper.insert(userOnLine);

        EmailUtil.sendEmail(javaMailSender, email, jobId, START_TEMPLATE_HTML);

        // 传输文件到指定运行代码的服务器
        String scpFileExec = startTransferFile(userPath + scFileId, execScATACFilePath + scFileId)
                + startTransferFile(userPath + variantFileId, execVariantFilePath + variantFileId);

        LinuxResult linuxResult = execLinux.execCommand(scpFileExec);

        String urlStr = "https://bio.liclab.net/scvmap/one_line_exec?genome=" + onLineVO.getGenome()
                + "&layer=" + onLineVO.getLayer() + "&scFileId=" + scFileId
                + "&variantFileId=" + variantFileId + "&email=" + onLineVO.getEmail();

        if (!linuxResult.isStatus()) {
            log.error("[execUserAnalysisFunction]: (start) scp error. [{}]", userOnLine.toString());
            userOnLine.setJobStatus("Error");
            userOnLine.setUpdateTime(new Date());
            userOnLineMapper.updateById(userOnLine);
            EmailUtil.sendEmailWithUrl(javaMailSender, CHECK_EMAIL, jobId, urlStr, ERROR_TEMPLATE_HTML);
            throw new RunException(SystemException.TIMEOUT_ERROR);
        }

        userOnLine.setJobStatus("In progress (The second stage)");
        userOnLine.setUpdateTime(new Date());
        log.info("[execUserAnalysisFunction]: Add user's task status. [{}]", userOnLine.toString());
        userOnLineMapper.updateById(userOnLine);

        String genome = onLineVO.getGenome();
        String layer = onLineVO.getLayer();

        String execStr = "mkdir -p " + execResultFilePath + "; bash " + execCodeFilePath + "exec_scavenge.sh " + execFilePath + " " + jobId + " "
                + scFileId + " " + variantFileId + " " + genome + " " + layer
                + " > " + execResultFilePath + jobId + ".log 2>&1";

        String execResultLogFile = getExecResultFile(execResultFilePath + jobId + ".log", userPath + jobId + ".log");

        // 执行 SCAVENGE
        linuxResult = execLinux.execCommand(execCoreCode(execStr));

        if (!linuxResult.isStatus()) {
            log.error("[execUserAnalysisFunction]: Add user's task status. [{}]", userOnLine.toString());
            execLinux.execCommand(execResultLogFile);
//            EmailUtil.sendEmail(javaMailSender, email, jobId, END_TEMPLATE_HTML);
//            userOnLine.setJobStatus("Error");
//            userOnLine.setUpdateTime(new Date());
//            userOnLineMapper.updateById(userOnLine);
            EmailUtil.sendEmail(javaMailSender, CHECK_EMAIL, jobId, CHECK_TEMPLATE_HTML);
            throw new RunException(SystemException.INVALID_RESOURCE);
        }

        String resultFileExec = execResultLogFile + getExecResultFile(execResultFilePath + jobId + "_intermediate_data.rda", userPath + jobId + "_intermediate_data.rda")
                + getExecResultFile(execResultFilePath + jobId + "_result.txt", userPath + jobId + "_result.txt");

        linuxResult = execLinux.execCommand(resultFileExec);

        if (!linuxResult.isStatus()) {
            log.error("[execUserAnalysisFunction]: (result) scp error. [{}]", userOnLine.toString());
            userOnLine.setJobStatus("Error");
            userOnLine.setUpdateTime(new Date());
            userOnLineMapper.updateById(userOnLine);
            EmailUtil.sendEmailWithUrl(javaMailSender, CHECK_EMAIL, jobId, urlStr, ERROR_TEMPLATE_HTML);
            throw new RunException(SystemException.TIMEOUT_ERROR);
        }

        userOnLine.setJobStatus("Finish");
        userOnLine.setUpdateTime(new Date());
        userOnLineMapper.updateById(userOnLine);
        log.info("[execUserAnalysisFunction]: Add user's task status. [{}]", userOnLine.toString());
        EmailUtil.sendEmail(javaMailSender, email, jobId, END_TEMPLATE_HTML);
    }

    @Override
    public PageResult<UserOnLine> listUserJob(Page page) {
        QueryWrapper<UserOnLine> queryWrapper = new QueryWrapper<>();

        setQueryWrapperByApply(page, queryWrapper);

        queryWrapper.lambda().orderByDesc(UserOnLine::getUpdateTime);
        return PageResultUtil.format(page, () -> userOnLineMapper.selectList(queryWrapper));
    }

    @Override
    public UserOnLine getUserJobInfo(String jobId) {
        return userOnLineMapper.selectById(jobId);
    }

    @Override
    public UserOnLine getJobStatusInfo(String scFileId, String variantFileId, OnLineVO onLineVO) {
        LambdaQueryWrapper<UserOnLine> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserOnLine::getScFileId, scFileId);
        queryWrapper.eq(UserOnLine::getVariantFileId, variantFileId);
        queryWrapper.eq(UserOnLine::getLayer, onLineVO.getLayer());
        queryWrapper.eq(UserOnLine::getGenome, onLineVO.getGenome());
        queryWrapper.eq(UserOnLine::getUserEmail, onLineVO.getEmail());
        return userOnLineMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Map<String, String>> jobResultAnalysis(String jobId) {
        String workPath = path.getWorkPath();
        String resultFile = workPath + "/user/" + jobId + "_result.txt";
        return FileUtil.readTabSeparatedFile(resultFile);
    }

    @Override
    public PlotlyClusterData<Double, Double> listJobClusterCoordinate(String jobId, String method, String axis1, String axis2, String cellType) {
        List<Map<String, String>> resultList = jobResultAnalysis(jobId);
        // Initialize a list to hold the Plotly data for the trait
        List<PlotlyData<Double, Double>> plotlyDataList = Lists.newArrayListWithCapacity(1);
        // Create text labels by combining cell types and barcodes
        List<String> textList = resultList.stream().map(stringStringMap -> stringStringMap.get(cellType)).collect(Collectors.toList());
        List<Double> umap1s = resultList.stream().map(stringStringMap -> Double.parseDouble(stringStringMap.get(axis1))).collect(Collectors.toList());
        List<Double> umap2s = resultList.stream().map(stringStringMap -> Double.parseDouble(stringStringMap.get(axis2))).collect(Collectors.toList());
        String valueColumn = StringUtil.isEqual(method, "scavenge") ? "TRS" : "z_score";
        List<Double> values = resultList.stream().map(stringStringMap -> Double.parseDouble(stringStringMap.get(valueColumn))).collect(Collectors.toList());

        // Build the Plotly data object for the trait and add it to the list
        plotlyDataList.add(PlotlyData.<Double, Double>builder()
                .x(umap1s)
                .y(umap2s)
                .color(values)
                .name(jobId)
                .text(textList)
                .extraText(textList)
                .build());
        // Build and return the PlotlyClusterData object containing the Plotly data for the trait
        return PlotlyClusterData.<Double, Double>builder()
                .plotlyDataList(plotlyDataList)
                .build();
    }

    @Override
    public PlotlyClusterData<Double, Double> listClusterCoordinate(String jobId, String axis1, String axis2, String cellType) {
        List<Map<String, String>> resultList = jobResultAnalysis(jobId);

        Map<String, List<Map<String, String>>> groupedByCellType = resultList.stream()
                .collect(Collectors.groupingBy(map -> map.get(cellType)));

        // Initialize a list to hold the Plotly data for each cell type
        List<PlotlyData<Double, Double>> plotlyDataList = Lists.newArrayListWithCapacity(groupedByCellType.size());

        groupedByCellType.forEach((cellTypeKey, cellTypeData) -> {
            List<Double> umap1s = cellTypeData.stream().map(stringStringMap -> Double.parseDouble(stringStringMap.get(axis1))).collect(Collectors.toList());
            List<Double> umap2s = cellTypeData.stream().map(stringStringMap -> Double.parseDouble(stringStringMap.get(axis2))).collect(Collectors.toList());
            // Build the Plotly data object for the current cell type and add it to the list
            plotlyDataList.add(PlotlyData.<Double, Double>builder()
                    .x(umap1s)
                    .y(umap2s)
                    .name(cellTypeKey)
                    .build());
        });

        // Build and return the PlotlyClusterData object containing all the Plotly data
        return PlotlyClusterData.<Double, Double>builder()
                .plotlyDataList(plotlyDataList)
                .build();

    }

    @Override
    public List<String> getJobResultColumns(String jobId) {
        String workPath = path.getWorkPath();
        String resultFile = workPath + "/user/" + jobId + "_result.txt";
        List<String> columnList = FileUtil.readOneLine(resultFile);
        if (StringUtil.isContain("trait_file", columnList)) {
            columnList.remove("trait_file");
        }
        return columnList;
    }
}
