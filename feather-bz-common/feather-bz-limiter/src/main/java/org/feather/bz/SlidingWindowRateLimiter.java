package org.feather.bz;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz
 * @className: SlidingWindowRateLimiter
 * @author: feather
 * @description:
 * @since: 2025-04-28 10:00
 * @version: 1.0
 */

public class SlidingWindowRateLimiter implements RateLimiter {

    private final RedissonClient redissonClient;

    private static final String LIMIT_KEY_PREFIX = "feather:limit:";


    public SlidingWindowRateLimiter(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public Boolean tryAcquire(String key, int limit, int windowSize) {
        RRateLimiter rRateLimiter = redissonClient.getRateLimiter(LIMIT_KEY_PREFIX + key);

        if (!rRateLimiter.isExists()) {
            rRateLimiter.trySetRate(RateType.OVERALL, limit, windowSize, RateIntervalUnit.SECONDS);
        }

        return rRateLimiter.tryAcquire();
    }
}
