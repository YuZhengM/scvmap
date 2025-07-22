package com.spring.boot.util.util;

import com.google.common.collect.Lists;
import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.constant.SystemException;
import com.spring.boot.util.exception.RunException;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.WillClose;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ykenan
 */
public class FileUtil {

    private static final Log log = LogFactory.getLog(FileUtil.class);

    /**
     * 形成文件夹和文件的过程
     *
     * @param path      路径
     * @param inputFile 文件
     * @return 是否创建成功
     */
    private static boolean before(String path, File inputFile) {
        File inputPath = new File(path);
        try {
            return !inputPath.exists() && inputPath.mkdirs() && !inputFile.exists() && inputFile.createNewFile() || !inputFile.exists() && inputFile.createNewFile() || inputFile.exists();
        } catch (IOException e) {
            log.error("[before]: Failed to form file or folder. {}, {}", e.getMessage(), e.getStackTrace());
        }
        return false;
    }

    /**
     * 形成文件的过程
     *
     * @param inputFile 文件
     * @return 是否创建成功
     */
    private static boolean before(File inputFile) {
        try {
            return !inputFile.exists() && inputFile.createNewFile() || inputFile.exists();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isExist(String path) {
        File inputPath = new File(path);
        return inputPath.exists();
    }

    /**
     * 形成文件夹
     *
     * @param path 路径
     * @return 是否创建成功
     */
    public static boolean formation(String path) {
        File inputPath = new File(path);
        return !inputPath.exists() && inputPath.mkdirs() || inputPath.exists();
    }

    /**
     * 文件形成
     *
     * @param path     形成路径
     * @param name     文件名称
     * @param suffix   文件后缀名
     * @param textarea 文件的内容
     * @return 文件名称
     */
    public static String formation(String path, String name, String suffix, String textarea) {
        String fileName = StringUtil.getUniqueId() + name + "." + suffix;
        try {
            File inputFile = new File(path, fileName);
            if (before(path, inputFile)) {
                FileOutputStream fileOutputStream = new FileOutputStream(inputFile);
                byte[] contentInBytes = textarea.getBytes();
                fileOutputStream.write(contentInBytes);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 文件形成
     *
     * @param path     形成路径文件名
     * @param textarea 文件的内容
     */
    public static void formation(String path, String textarea) {
        try {
            File inputFile = new File(path);
            if (before(inputFile)) {
                FileOutputStream fileOutputStream = new FileOutputStream(inputFile);
                byte[] contentInBytes = textarea.getBytes();
                fileOutputStream.write(contentInBytes);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件形成
     *
     * @param path        形成路径文件名
     * @param inputStream 文件的内容
     */
    public static void formation(String path, @WillClose InputStream inputStream) {
        try {
            File inputFile = new File(path);
            if (before(inputFile)) {
                FileOutputStream fileOutputStream = new FileOutputStream(inputFile);
                fileOutputStream.write(inputStream.readAllBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件内容形成字节流
     *
     * @param textarea 文件的内容
     * @return InputStream
     */
    public static InputStream formationStream(String textarea) {
        byte[] contentInBytes = textarea.getBytes();
        return new ByteArrayInputStream(contentInBytes);
    }

    /**
     * 文件形成
     *
     * @param path     形成路径
     * @param name     文件名称
     * @param suffix   文件后缀名
     * @param fileByte 文件的内容
     * @return 文件名称
     */
    public static String formationByByte(String path, String name, String suffix, byte[] fileByte) {
        String fileName = name + "." + suffix;
        try {
            File inputFile = new File(path, fileName);
            if (before(path, inputFile)) {
                FileOutputStream fileOutputStream = new FileOutputStream(inputFile);
                fileOutputStream.write(fileByte);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 文件读取
     *
     * @param filePath 文件的绝对路径
     * @param encoding 读取文件的编码
     * @return 文件的内容
     */
    public static List<String> read(String filePath, String encoding) {
        List<String> result = Lists.newArrayListWithExpectedSize(128);
        try {
            @WillClose BufferedReader br = getReader(filePath, encoding);
            String s;
            while (StringUtil.isNotEmpty(s = br.readLine())) {
                result.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 文件读取
     *
     * @param inputStream 文件流
     * @param encoding    读取文件的编码
     * @return 文件的内容
     */
    public static List<String> read(@WillClose InputStream inputStream, String encoding) {
        List<String> result = Lists.newArrayListWithExpectedSize(128);
        @WillClose InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, encoding);
            @WillClose BufferedReader br = new BufferedReader(inputStreamReader);
            String str;
            while (StringUtil.isNotEmpty(str = br.readLine())) {
                result.add(str);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 文件读取
     *
     * @param filePath 文件的绝对路径
     * @return 文件的内容
     */
    public static List<String> read(String filePath) {
        return read(filePath, CommonCode.UTF_8);
    }

    /**
     * 文件读取
     *
     * @param inputStream 文件流
     * @return 文件的内容
     */
    public static List<String> read(@WillClose InputStream inputStream) {
        return read(inputStream, CommonCode.UTF_8);
    }

    /**
     * 文件读取
     *
     * @param filePath 文件的绝对路径
     * @param encoding 文件编码
     * @return 文件的内容
     */
    public static String readToString(String filePath, String encoding) {
        StringBuilder stringBuilder = new StringBuilder();
        @WillClose InputStream inputStream = null;
        @WillClose InputStreamReader inputStreamReader = null;
        try {
            inputStream = new FileInputStream(filePath);
            inputStreamReader = new InputStreamReader(inputStream, encoding);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String s;
            while (StringUtil.isNotEmpty(s = br.readLine())) {
                stringBuilder.append(s).append("\n");
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 文件读取
     *
     * @param filePath 文件的绝对路径
     * @return 文件的内容
     */
    public static String readToString(String filePath) {
        return readToString(filePath, CommonCode.UTF_8);
    }

    /**
     * 文件读取
     *
     * @param inputStream 文件流
     * @param encoding    读取文件的编码
     * @return 文件的内容
     */
    public static String readToString(@WillClose InputStream inputStream, String encoding) {
        StringBuilder stringBuilder = new StringBuilder();
        @WillClose InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, encoding);
            @WillClose BufferedReader br = new BufferedReader(inputStreamReader);
            String str;
            while (StringUtil.isNotEmpty(str = br.readLine())) {
                stringBuilder.append(str).append("\n");
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 文件读取
     *
     * @param inputStream 文件流
     * @return 文件的内容
     */
    public static String readToString(@WillClose InputStream inputStream) {
        return readToString(inputStream, CommonCode.UTF_8);
    }

    /**
     * 得到 BufferedReader
     *
     * @param filePath 文件路径
     * @param encoding 文件编码
     */
    private static BufferedReader getReader(String filePath, String encoding) {
        InputStreamReader inputStreamReader = null;
        try {
            // 这个流此处不能关闭, BufferedReader 关闭时, 此处流便关闭
            FileInputStream fileInputStream = new FileInputStream(filePath);
            inputStreamReader = new InputStreamReader(fileInputStream, encoding);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (inputStreamReader == null) {
            throw new RunException(SystemException.INVALID_RESOURCE);
        }
        return new BufferedReader(inputStreamReader);
    }

    /**
     * 获取某个文件夹下的所有文件
     *
     * @param path 文件夹的路径
     * @return List<String>
     */
    public static List<String> listFileAndDirectory(String path) {
        File file = new File(path);
        String[] list = file.list();
        if (list == null) {
            return NullUtil.listEmpty();
        }
        return Arrays.asList(list);
    }

    /**
     * 获取某个文件夹下的所有文件
     *
     * @param path 文件夹的路径
     * @return List<String>
     */
    public static List<String> listFilename(String path) {
        File file = new File(path);
        String[] list = file.list();
        if (list == null) {
            return NullUtil.listEmpty();
        }
        return Arrays.stream(list).filter(
                filename -> new File(path + "/" + filename).isFile()
        ).collect(Collectors.toList());
    }

    /**
     * 获取某个文件夹下的所有文件夹
     *
     * @param path 文件夹的路径
     * @return List<String>
     */
    public static List<String> listDirectory(String path) {
        File file = new File(path);
        String[] list = file.list();
        if (list == null) {
            return NullUtil.listEmpty();
        }
        return Arrays.stream(list).filter(
                filename -> new File(path + "/" + filename).isDirectory()
        ).collect(Collectors.toList());
    }

    /**
     * 文件上传
     *
     * @param path   path
     * @param name   name
     * @param suffix suffix
     * @param file   file
     * @return String
     */
    public static String upload(String path, String name, String suffix, MultipartFile file) {
        String newFileName = StringUtil.getUniqueId() + name + "." + suffix;
        try {
            File uploadFile = new File(path, newFileName);
            file.transferTo(uploadFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFileName;
    }

    /**
     * 得到文件大小
     *
     * @param filePath 文件
     * @return double
     */
    public static double getSize(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.length();
        }
        return 0D;
    }

    public static void delete(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
