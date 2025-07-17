package com.spring.boot.util.util.config;

import com.spring.boot.util.util.ClassUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取 Config 文件信息
 *
 * @author Zhengmin Yu
 */
public class ReadConfig {

    /**
     * resources 更路径符号
     */
    private static final String FILE_PATH_PREFIX = "/";

    private final Class<?> clazz;
    private final String filename;

    /**
     * 初始化类和文件名
     *
     * @param clazz    类
     * @param filename 配置文件名
     */
    public ReadConfig(Class<?> clazz, String filename) {
        this.clazz = clazz;
        this.filename = filename;
    }

    /**
     * 读取配置文件中的参数
     * <p>
     * 这里的 contextConfig 文件流在 properties.load(contextConfig); 加载完之后已经被关闭了.
     * 所以这就意味着每次读取配置文件的一个参数的时候, 都需要从新对文件进行 load, contextConfig 不能作为宏
     * </p>
     *
     * @param props 参数
     * @return 参数值
     */
    public String getConfiguration(String props) {
        // 加载文件
        InputStream contextConfig = clazz.getResourceAsStream(FILE_PATH_PREFIX + filename);
        // 初始化读取的配置文件
        Properties properties = new Properties();
        try {
            properties.load(contextConfig);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ClassUtil.isNotEmpty(contextConfig)) {
                    assert contextConfig != null;
                    contextConfig.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties.getProperty(props);
    }


}
