package org.feather.bz.utils;

import org.springframework.stereotype.Component;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.utils
 * @className: CacheUtil
 * @author: feather
 * @description:
 * @since: 2025-11-03 9:51
 * @version: 1.0
 */
@Component
public class CacheUtil {


    private static final String PROJECT_PREFIX = "feather-bz-server";
    private static final String SPLIT_ITEM = ":";


    /**
     * 生成缓存key
     * @param business 业务模块
     * @param value 具体值
     * @return 完整的缓存key
     */
    public static String buildKey(String business, Object value) {
        return PROJECT_PREFIX + SPLIT_ITEM + business + SPLIT_ITEM+ String.valueOf(value);
    }

    /**
     * 生成缓存key（多个值）
     * @param business 业务模块
     * @param values 多个具体值
     * @return 完整的缓存key
     */
    public static String buildKey(String business, Object... values) {
        StringBuilder key = new StringBuilder(PROJECT_PREFIX + SPLIT_ITEM + business);
        for (Object value : values) {
            key.append(SPLIT_ITEM).append(String.valueOf(value));
        }
        return key.toString();
    }
}
