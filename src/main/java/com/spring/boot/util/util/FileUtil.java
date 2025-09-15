package com.spring.boot.util.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.constant.SystemException;
import com.spring.boot.util.exception.RunException;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.WillClose;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class provides utility methods for file operations,
 * including file creation, reading, writing, and deletion.
 *
 * @author Ykenan
 */
public class FileUtil {

    private static final Log log = LogFactory.getLog(FileUtil.class);

    /**
     * Process of creating folders and files.
     *
     * @param path      Path
     * @param inputFile File
     * @return Whether the creation is successful
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
     * Process of creating a file.
     *
     * @param inputFile File
     * @return Whether the creation is successful
     */
    private static boolean before(File inputFile) {
        try {
            return !inputFile.exists() && inputFile.createNewFile() || inputFile.exists();
        } catch (IOException e) {
            log.error("[before]: Failed to form file or folder. {}, {}", e.getMessage(), e.getStackTrace());
        }
        return false;
    }

    public static boolean isExist(String path) {
        File inputPath = new File(path);
        return inputPath.exists();
    }

    /**
     * Create a folder.
     *
     * @param path Path
     * @return Whether the creation is successful
     */
    public static boolean formation(String path) {
        File inputPath = new File(path);
        return !inputPath.exists() && inputPath.mkdirs() || inputPath.exists();
    }

    /**
     * Create a file.
     *
     * @param path     Creation path
     * @param name     File name
     * @param suffix   File suffix
     * @param textarea File content
     * @return File name
     */
    public static String formation(String path, String name, String suffix, String textarea) {
        String fileName = StringUtil.getUniqueId10() + name + "." + suffix;
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
            log.error("[formation]: Failed to create file. {}, {}", e.getMessage(), e.getStackTrace());
        }
        return fileName;
    }

    /**
     * Create a file.
     *
     * @param path     Creation path and file name
     * @param textarea File content
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
            log.error("[formation]: Failed to create file. {}, {}", e.getMessage(), e.getStackTrace());
        }
    }

    /**
     * Create a file.
     *
     * @param path        Creation path and file name
     * @param inputStream File content
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
            log.error("[formation]: Failed to create file. {}, {}", e.getMessage(), e.getStackTrace());
        }
    }

    /**
     * Convert file content to a byte stream.
     *
     * @param textarea File content
     * @return InputStream
     */
    public static InputStream formationStream(String textarea) {
        byte[] contentInBytes = textarea.getBytes();
        return new ByteArrayInputStream(contentInBytes);
    }

    /**
     * Create a file.
     *
     * @param path     Creation path
     * @param name     File name
     * @param suffix   File suffix
     * @param fileByte File content in bytes
     * @return File name
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
            log.error("[formationByByte]: Failed to create file. {}, {}", e.getMessage(), e.getStackTrace());
        }
        return fileName;
    }

    /**
     * Read a file.
     *
     * @param filePath File absolute path
     * @param encoding File encoding for reading
     * @return File content
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
            log.error("[read]: Failed to read file. {}, {}", e.getMessage(), e.getStackTrace());
        }
        return result;
    }

    /**
     * Read a file from an input stream.
     *
     * @param inputStream File input stream
     * @param encoding    File encoding for reading
     * @return File content
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
            log.error("[read]: Failed to read file from input stream. {}, {}", e.getMessage(), e.getStackTrace());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                log.error("[read]: Failed to close stream. {}, {}", e.getMessage(), e.getStackTrace());
            }
        }
        return result;
    }

    /**
     * Read a file.
     *
     * @param filePath File absolute path
     * @return File content
     */
    public static List<String> read(String filePath) {
        return read(filePath, CommonCode.UTF_8);
    }

    /**
     * Read a file from an input stream.
     *
     * @param inputStream File input stream
     * @return File content
     */
    public static List<String> read(@WillClose InputStream inputStream) {
        return read(inputStream, CommonCode.UTF_8);
    }

    /**
     * 文件读取
     *
     * @param filePath 文件的 resources 下的路径
     * @return 文件的内容
     */
    public static String readResourceToString(String filePath, String encoding) {
        // 获取容器资源解析器
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 获取所有匹配的文件
        Resource resource = resolver.getResource(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), encoding));
            String s;
            while ((s = br.readLine()) != null) {
                stringBuilder.append(s);
            }
            br.close();
        } catch (Exception e) {
            log.error("[readResourceToString]: Failed to read file to string. {}, {}", e.getMessage(), e.getStackTrace());
        }
        return stringBuilder.toString();
    }

    /**
     * Read a file and return its content as a string.
     *
     * @param filePath File absolute path
     * @param encoding File encoding
     * @return File content
     */
    public static String readToString(String filePath, String encoding) {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = new FileInputStream(filePath); InputStreamReader inputStreamReader = new InputStreamReader(inputStream, encoding)) {
            try {
                BufferedReader br = new BufferedReader(inputStreamReader);
                String s;
                while (StringUtil.isNotEmpty(s = br.readLine())) {
                    stringBuilder.append(s).append("\n");
                }
                br.close();
            } catch (Exception e) {
                log.error("[readToString]: Failed to read file to string. {}, {}", e.getMessage(), e.getStackTrace());
            }
        } catch (IOException e) {
            log.error("[readToString]: Failed to close stream. {}, {}", e.getMessage(), e.getStackTrace());
        }
        return stringBuilder.toString();
    }

    /**
     * Read a file and return its content as a string.
     *
     * @param filePath File absolute path
     * @return File content
     */
    public static String readToString(String filePath) {
        return readToString(filePath, CommonCode.UTF_8);
    }

    public static List<String> readOneLine(String filePath, String splitStr) {
        List<String> firstLineList = Lists.newArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String firstLine = br.readLine();
            if (firstLine != null) {
                String[] columns = firstLine.split(splitStr);
                firstLineList.addAll(Arrays.asList(columns));
            }
        } catch (IOException e) {
            log.error("[readToString]: Failed to read file to string. {}, {}", e.getMessage(), e.getStackTrace());
        }
        return firstLineList;
    }

    public static List<String> readOneLine(String filePath) {
        return readOneLine(filePath, "\t");
    }

    /**
     * Read a file from an input stream and return its content as a string.
     *
     * @param inputStream File input stream
     * @param encoding    File encoding for reading
     * @return File content
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
            log.error("[readToString]: Failed to read input stream to string. {}, {}", e.getMessage(), e.getStackTrace());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                log.error("[readToString]: Failed to close stream. {}, {}", e.getMessage(), e.getStackTrace());
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Read a file from an input stream and return its content as a string.
     *
     * @param inputStream File input stream
     * @return File content
     */
    public static String readToString(@WillClose InputStream inputStream) {
        return readToString(inputStream, CommonCode.UTF_8);
    }

    /**
     * Get a BufferedReader.
     *
     * @param filePath File path
     * @param encoding File encoding
     */
    private static BufferedReader getReader(String filePath, String encoding) {
        InputStreamReader inputStreamReader = null;
        try {
            // This stream cannot be closed here. It will be closed when the BufferedReader is closed.
            FileInputStream fileInputStream = new FileInputStream(filePath);
            inputStreamReader = new InputStreamReader(fileInputStream, encoding);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            log.error("[getReader]: Failed to get BufferedReader. {}, {}", e.getMessage(), e.getStackTrace());
        }
        if (inputStreamReader == null) {
            throw new RunException(SystemException.INVALID_RESOURCE);
        }
        return new BufferedReader(inputStreamReader);
    }

    /**
     * Get all files and directories under a folder.
     *
     * @param path Folder path
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
     * Get all files under a folder.
     *
     * @param path Folder path
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
     * Get all directories under a folder.
     *
     * @param path Folder path
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
     * Upload a file.
     *
     * @param path   Path
     * @param name   File name
     * @param suffix File suffix
     * @param file   MultipartFile
     * @return New file name
     */
    public static String upload(String path, String name, String suffix, MultipartFile file) {
        String newFileName = StringUtil.getUniqueId10() + name + "." + suffix;
        try {
            File uploadFile = new File(path, newFileName);
            file.transferTo(uploadFile);
        } catch (Exception e) {
            log.error("[upload]: Failed to upload file. {}, {}", e.getMessage(), e.getStackTrace());
        }
        return newFileName;
    }

    /**
     * Get the size of a file.
     *
     * @param filePath File path
     * @return File size in double
     */
    public static double getSize(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.length();
        }
        return 0D;
    }

    public static boolean delete(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static List<Map<String, String>> readTabSeparatedFile(String filePath) {
        List<Map<String, String>> dataList = Lists.newArrayList();
        String[] headers = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");

                if (isFirstLine) {
                    headers = values;
                    isFirstLine = false;
                    continue;
                }

                Map<String, String> rowMap = Maps.newHashMap();
                for (int i = 0; i < headers.length && i < values.length; i++) {
                    rowMap.put(headers[i], values[i]);
                }

                dataList.add(rowMap);
            }
        } catch (IOException e) {
            log.error("[upload]: Failed to upload file. {}, {}", e.getMessage(), e.getStackTrace());
        }

        return dataList;
    }

}
