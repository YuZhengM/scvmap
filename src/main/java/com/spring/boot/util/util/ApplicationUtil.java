package com.spring.boot.util.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.spring.boot.pojo.SampleCellType;
import com.spring.boot.util.constant.SystemException;
import com.spring.boot.util.exception.RunException;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import com.spring.boot.util.model.LinuxResult;
import com.spring.boot.util.model.vo.FieldNumber;
import com.spring.boot.util.model.vo.echarts.EchartsPieData;
import com.spring.boot.util.model.vo.echarts.SeriesPieData;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * A utility class specific to this project
 *
 * @author Zhengmin Yu
 */
public class ApplicationUtil {

    private static final Log log = LogFactory.getLog(ApplicationUtil.class);

    public static LinuxResult execLinuxCommand(String host, int port, String username, String password, String keyFile, String command) throws IOException {
        // Create a new SSH connection
        Connection connection = new Connection(host, port);
        // Create a list to hold the command execution results
        List<String> result = new ArrayList<>();
        boolean isSuccess = true;
        // Connect to the host
        connection.connect();
        // Authenticate with password
        boolean authenticated;
        if (StringUtil.isEmpty(password)) {
            authenticated = connection.authenticateWithPublicKey(username, new File(keyFile), password);
        } else {
            authenticated = connection.authenticateWithPassword(username, password);
        }
        if (!authenticated) {
            throw new IOException("Authentication failed.");
        } else {
            // Open a session for command execution
            Session session = connection.openSession();
            // Execute the command with UTF-8 encoding
            session.execCommand(command, "utf-8");
            // StreamGobbler for stdout
            StreamGobbler stdout = new StreamGobbler(session.getStdout());
            // StreamGobbler for stderr
            StreamGobbler stderr = new StreamGobbler(session.getStderr());
            // BufferedReader for stdout
            BufferedReader stdoutBuffer = new BufferedReader(new InputStreamReader(stdout));
            // BufferedReader for stderr
            BufferedReader stderrBuffer = new BufferedReader(new InputStreamReader(stderr));
            log.info("[execLinuxCommand]: Executing Linux command >>>> {}", command);
            // Read stdout line by line
            for (String line = stdoutBuffer.readLine(); line != null; line = stdoutBuffer.readLine()) {
                result.add(line + "\n");
            }
            // Read stderr line by line
            for (String line = stderrBuffer.readLine(); line != null; line = stderrBuffer.readLine()) {
                isSuccess = false;
                log.error("[execLinuxCommand]: Linux error line >>>> {}", line);
                result.add(line);
            }
            // Close the BufferedReader instances
            stdoutBuffer.close();
            stderrBuffer.close();
            // Close the session
            session.close();
        }
        // Close the connection
        connection.close();
        return LinuxResult.builder().resultList(result).status(isSuccess).build();
    }

    public static String getUserIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");

        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        // 对于通过多个代理的情况，第一个IP才是客户端真实IP
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        }
        return ipAddress;
    }

    public static EchartsPieData<String, String> getCellTypeCountEchartsData(List<SampleCellType> sampleCellTypeList) {
        if (ListUtil.isEmpty(sampleCellTypeList)) {
            log.warn("[getCellTypeCountEchartsData] clusterCellTypeCountList: Data is empty.");
            return EchartsPieData.<String, String>builder().build();
        }
        int size = sampleCellTypeList.size();
        // Create containers
        List<String> legendList = Lists.newArrayListWithCapacity(size);
        List<SeriesPieData> seriesPieDataList = Lists.newArrayListWithCapacity(size);
        Map<String, String> description = Maps.newHashMapWithExpectedSize(size);
        // Add labels and data
        for (SampleCellType sampleCellType : sampleCellTypeList) {
            String cellType = sampleCellType.getCellType();
            Integer number = sampleCellType.getCellCount();
            legendList.add(cellType);
            seriesPieDataList.add(SeriesPieData.builder().name(cellType).value(number).build());
        }
        return EchartsPieData.<String, String>builder().legend(legendList).data(seriesPieDataList).description(description).build();
    }

    public static EchartsPieData<String, String> getChrCountEchartsData(List<FieldNumber> fieldNumberList) {
        if (ListUtil.isEmpty(fieldNumberList)) {
            log.warn("[getChrCountEchartsData] fieldNumberList: Data is empty.");
            return EchartsPieData.<String, String>builder().build();
        }
        int size = fieldNumberList.size();
        // 建立容器
        List<String> legendList = Lists.newArrayListWithCapacity(size);
        List<SeriesPieData> seriesPieDataList = Lists.newArrayListWithCapacity(size);
        Map<String, String> description = Maps.newHashMapWithExpectedSize(size);
        // 添加标签和数据
        for (FieldNumber fieldNumber : fieldNumberList) {
            String field = fieldNumber.getField();
            Integer number = fieldNumber.getNumber();
            legendList.add(field);
            seriesPieDataList.add(SeriesPieData.builder().name(field).value(number).build());
        }
        return EchartsPieData.<String, String>builder().legend(legendList).data(seriesPieDataList).description(description).build();
    }

    /**
     * Calculate the random cell number based on the given size and cell ratio.
     *
     * @param size     The size
     * @param cellRate The cell ratio
     * @return The random cell number
     */
    public static int getRandomCellNumber(Integer size, Double cellRate) {
        return (int) Math.ceil(size * cellRate);
    }

    /**
     * Extract the trait signal ID from the trait ID.
     *
     * @param traitId    The trait ID
     * @param groupCount The group count
     * @return The trait signal ID
     */
    public static String getTraitSignalId(String traitId, int groupCount) {
        int idValue = Integer.parseInt(traitId.split("_")[traitId.split("_").length - 1]);
        return String.valueOf(idValue % groupCount);
    }

    public static String getTraitSignalId(String traitId) {
        return getTraitSignalId(traitId, 100);
    }

    public static String getTrait20SignalId(String traitId) {
        return getTraitSignalId(traitId, 20);
    }

    /**
     * Convert the string result returned by Python into a list of strings.
     *
     * @param result The string result returned by Python
     * @return The converted string list
     */
    public static List<String> listStringByPythonResult(String result) {
        String[] data = result.strip().replaceAll(" ", "").strip().split("','");
        data[0] = data[0].split("'")[1];
        data[data.length - 1] = data[data.length - 1].split("'")[0];
        return Arrays.asList(data);
    }

    /**
     * Convert the numeric result returned by Python into a list of strings.
     *
     * @param result The numeric result returned by Python
     * @return The converted string list
     */
    public static List<String> listNumberByPythonResult(String result) {
        String[] data = result.strip().replaceAll(" ", "").split(",");
        data[0] = data[0].split("\\[")[1];
        data[data.length - 1] = data[data.length - 1].split("]")[0];
        return Arrays.asList(data);
    }

    /**
     * Convert the numeric result returned by Python into a list of doubles.
     *
     * @param result The numeric result returned by Python
     * @return The converted double list
     */
    public static List<Double> listDoubleByPythonResult(String result) {
        List<String> strings = listNumberByPythonResult(result);
        return strings.stream().map(Double::parseDouble).toList();
    }

    /**
     * Check if the sample ID is valid.
     *
     * @param sampleId The sample ID
     * @throws RunException Thrown when the sample ID is invalid
     */
    public static void checkSampleId(String sampleId) {
        if (StringUtil.isEmpty(sampleId) || !sampleId.startsWith("sample_id")) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }
    }

    /**
     * Check if the source ID is valid.
     *
     * @param sourceId The source ID
     * @throws RunException Thrown when the source ID is invalid
     */
    public static void checkSourceId(String sourceId) {
        if (StringUtil.isEmpty(sourceId) || !sourceId.startsWith("source_id")) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }
    }

    /**
     * Check if the trait ID is valid.
     *
     * @param traitId The trait ID
     * @throws RunException Thrown when the trait ID is invalid
     */
    public static void checkTraitId(String traitId) {
        if (StringUtil.isEmpty(traitId) || !traitId.startsWith("trait_id")) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }
    }

    /**
     * Check if the genome version is valid.
     *
     * @param genome The genome version
     * @throws RunException Thrown when the genome version is invalid
     */
    public static void checkGenome(String genome) {
        if (StringUtil.isEmpty(genome) || (StringUtil.isNotEqual(genome, "hg38") && StringUtil.isNotEqual(genome, "hg19"))) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }
    }

    /**
     * Check if the method is valid.
     *
     * @param method The method name
     * @throws RunException Thrown when the method name is invalid
     */
    public static void checkMethod(String method) {
        if (StringUtil.isEmpty(method) || (StringUtil.isNotEqual(method, "gchromvar") && StringUtil.isNotEqual(method, "scavenge"))) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }
    }

    /**
     * Check if the strategy is valid.
     *
     * @param strategy The strategy name
     * @throws RunException Thrown when the strategy name is invalid
     */
    public static void checkStrategy(String strategy) {
        if (StringUtil.isEmpty(strategy) || (StringUtil.isNotEqual(strategy, "mean") && StringUtil.isNotEqual(strategy, "median") && StringUtil.isNotEqual(strategy, "sum"))) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }
    }

    /**
     * Convert the transcription factor name by replacing specific placeholders.
     * Note: This function can be ignored.
     *
     * @param tf The original transcription factor name
     * @return The converted transcription factor name
     */
    public static String getTfName(String tf) {
        return tf.replaceAll("_____", "/").replaceAll("-----", "?");
    }

    /**
     * Get the element signal ID based on the gene name.
     *
     * @param geneName The gene name
     * @return The element signal ID
     */
    public static int getElementSignalId(String geneName) {
        return StringUtil.wordToNumberByAscii(geneName) % 100;
    }
}
