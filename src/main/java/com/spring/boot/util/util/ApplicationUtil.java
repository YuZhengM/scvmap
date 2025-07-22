package com.spring.boot.util.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.spring.boot.pojo.SampleCellType;
import com.spring.boot.util.constant.SystemException;
import com.spring.boot.util.exception.RunException;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import com.spring.boot.util.model.vo.FieldNumber;
import com.spring.boot.util.model.vo.echarts.EchartsPieData;
import com.spring.boot.util.model.vo.echarts.SeriesPieData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 为该项目特有的公共类
 *
 * @author Zhengmin Yu
 */
public class ApplicationUtil {

    private static final Log log = LogFactory.getLog(ApplicationUtil.class);

    private static final List<String> chrList = Arrays.asList(
            "chr1", "chr2", "chr3", "chr4", "chr5", "chr6", "chr7", "chr8", "chr9", "chr10", "chr11", "chr12", "chr13",
            "chr14", "chr15", "chr16", "chr17", "chr18", "chr19", "chr20", "chr21", "chr22", "chrX", "chrY"
    );

    public static EchartsPieData<String, String> getCellTypeCountEchartsData(List<SampleCellType> sampleCellTypeList) {
        if (ListUtil.isEmpty(sampleCellTypeList)) {
            log.warn("[getCellTypeCountEchartsData] clusterCellTypeCountList: Data is empty.");
            return EchartsPieData.<String, String>builder().build();
        }
        int size = sampleCellTypeList.size();
        // 建立容器
        List<String> legendList = Lists.newArrayListWithCapacity(size);
        List<SeriesPieData> seriesPieDataList = Lists.newArrayListWithCapacity(size);
        Map<String, String> description = Maps.newHashMapWithExpectedSize(size);
        // 添加标签和数据
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

    public static int getRandomCellNumber(Integer size, Double cellRate) {
        return (int) Math.ceil(size * cellRate);
    }

    public static String getTraitSignalId(String traitId) {
        int idValue = Integer.parseInt(traitId.split("_")[traitId.split("_").length - 1]);
        return String.valueOf(idValue % 100);
    }

    public static List<String> listStringByPythonResult(String result) {
        String[] data = result.strip().replaceAll(" ", "").strip().split("','");
        data[0] = data[0].split("'")[1];
        data[data.length - 1] = data[data.length - 1].split("'")[0];
        return Arrays.asList(data);
    }

    public static List<String> listNumberByPythonResult(String result) {
        String[] data = result.strip().replaceAll(" ", "").split(",");
        data[0] = data[0].split("\\[")[1];
        data[data.length - 1] = data[data.length - 1].split("]")[0];
        return Arrays.asList(data);
    }

    public static List<Integer> listIntegerByPythonResult(String result) {
        List<String> strings = listNumberByPythonResult(result);
        return strings.stream().map(Integer::parseInt).toList();
    }

    public static List<Double> listDoubleByPythonResult(String result) {
        List<String> strings = listNumberByPythonResult(result);
        return strings.stream().map(Double::parseDouble).toList();
    }

    public static void checkSampleId(String sampleId) {
        if (!sampleId.startsWith("sample_id")) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }
    }

    public static void checkSourceId(String sourceId) {
        if (!sourceId.startsWith("source_id")) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }
    }

    public static void checkTraitId(String traitId) {
        if (!traitId.startsWith("trait_id")) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }
    }

    public static void checkGenome(String genome) {
        if (StringUtil.isNotEqual(genome, "hg38") && StringUtil.isNotEqual(genome, "hg19")) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }
    }

    public static void checkMethod(String method) {
        if (StringUtil.isNotEqual(method, "gchromvar") && StringUtil.isNotEqual(method, "scavenge")) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }
    }

    public static void checkStrategy(String strategy) {
        if (StringUtil.isNotEqual(strategy, "mean") && StringUtil.isNotEqual(strategy, "median") && StringUtil.isNotEqual(strategy, "sum")) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }
    }

    public static void checkChr(String chr) {
        if (StringUtil.isNotContain(chr, chrList)) {
            throw new RunException(SystemException.ILLEGAL_PARAMETER);
        }
    }

    public static String getTfName(String tf) {
        return tf.replaceAll("_____", "/").replaceAll("-----", "?");
    }

    public static int getElementSignalId(String geneName) {
        return StringUtil.wordToNumberByAscii(geneName) % 100;
    }

}
