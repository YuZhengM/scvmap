package com.spring.boot.config.redis;

import com.google.common.hash.Hashing;
import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import com.spring.boot.util.util.result.Page;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.nio.charset.Charset;

/**
 * Cache key generator component for generating unique cache keys based on method and parameters.
 * This class implements the KeyGenerator interface to provide custom cache key generation logic.
 *
 * @author Zhengmin Yu
 */
@Component
public class CacheKeyGenerator implements KeyGenerator {

    private static final Log log = LogFactory.getLog(CacheKeyGenerator.class);

    public static final String NO_PARAM_KEY = "NO";
    public static final String NULL_PARAM_KEY = "NULL";
    public static final String CONNECTION_SYMBOL = "_";

    /**
     * Generates a cache key based on the target object, method, and parameters.
     *
     * @param target the target object
     * @param method the method being called
     * @param params the method parameters
     * @return the generated cache key
     */
    @Override
    public String generate(@NonNull Object target, @NonNull Method method, @NonNull Object... params) {
        // Initialize the key with class simple name, method name, and project code
        StringBuilder key = new StringBuilder();
        key.append(target.getClass().getSimpleName()).append(CONNECTION_SYMBOL)
                .append(method.getName()).append(CONNECTION_SYMBOL)
                .append(CommonCode.PROJECT_EN_L_NAME).append(CONNECTION_SYMBOL);

        // Check if no parameters are provided
        if (params.length == 0) {
            return key.append(NO_PARAM_KEY).toString();
        }

        // Iterate over the parameters
        for (Object param : params) {
            // Check for null parameters
            if (param == null) {
                log.warn("Input null param for Spring cache, use default key={}", NULL_PARAM_KEY);
                key.append(NULL_PARAM_KEY);
            } else if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param instanceof String || param instanceof Page) {
                // Append parameter value if it's a primitive, wrapper, String, or Page
                key.append(param);
            } else {
                // Warn and append hash code for complex objects
                log.warn("Using an object as a cache key may lead to unexpected results. Either use @Cacheable(key=..) or implement CacheKey. Method is " + target.getClass() + "#" + method.getName());
                key.append(param.hashCode());
            }
            // Append connection symbol after each parameter
            key.append(CONNECTION_SYMBOL);
        }

        // Generate the final key string
        String finalKey = key.toString();

        // Apply hash function to the final key
        long cacheKeyHash = Hashing.murmur3_128().hashString(finalKey, Charset.defaultCharset()).asLong();
        log.debug("Using cache key=[{}], hashCode=[{}]", finalKey, cacheKeyHash);

        // Return the final key
        return finalKey;
    }
}
