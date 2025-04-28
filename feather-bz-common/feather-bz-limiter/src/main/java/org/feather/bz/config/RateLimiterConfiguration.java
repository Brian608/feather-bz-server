package org.feather.bz.config;

import org.feather.bz.SlidingWindowRateLimiter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.config
 * @className: RateLimiterConfiguration
 * @author: feather
 * @description:
 * @since: 2025-04-28 10:06
 * @version: 1.0
 */
@Configuration
public class RateLimiterConfiguration {

    @Bean
    public SlidingWindowRateLimiter slidingWindowRateLimiter(RedissonClient redisson) {
        return new SlidingWindowRateLimiter(redisson);
    }
}
