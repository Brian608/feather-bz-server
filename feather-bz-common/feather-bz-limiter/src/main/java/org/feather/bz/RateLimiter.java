package org.feather.bz;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.config
 * @className: RateLimiter
 * @author: feather
 * @description:
 * @since: 2025-04-28 9:59
 * @version: 1.0
 */
public interface RateLimiter {

    /**
     * description: 判断一个key是否可以通过
     * @param key 限流的key
     * @param limit 限流的数量
     * @param windowSize 窗口大小，单位为秒
     * @return {@link Boolean}
     * @author: feather
     * @since: 2025-04-28 10:00
     **/
    Boolean tryAcquire(String key, int limit, int windowSize);
}
