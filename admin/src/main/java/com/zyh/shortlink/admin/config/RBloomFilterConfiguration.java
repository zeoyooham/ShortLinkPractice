package com.zyh.shortlink.admin.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.context.annotation.Configuration;

/**
 * author 邹宇航  @vision 1.0
 */
@Configuration(value = "rBloomFilterConfigurationByAdmin")
public class RBloomFilterConfiguration {

    /**
     * 用户注册缓存穿透布隆过滤器
     * @param redissonClient
     * @param redisClient
     * @return
     */
    public RBloomFilter<String> userRegisterCachePenetrationBloomFilter(RedissonClient redissonClient, RedisClient redisClient){
        RBloomFilter<String> cachePenetrationBloomFilter = redissonClient.getBloomFilter("userRegisterCachePenetrationBloomFilter");
        cachePenetrationBloomFilter.tryInit(1000000,0.01);
        return cachePenetrationBloomFilter;
    }

    /**
     * 用户创建分组标识缓存穿透布隆过滤器
     * @param redissonClient
     * @param redisClient
     * @return
     */
    public RBloomFilter<String> gidRegisterCachePenetrationBloomFilter(RedissonClient redissonClient, RedisClient redisClient){
        RBloomFilter<String> cachePenetrationBloomFilter = redissonClient.getBloomFilter("gidRegisterCachePenetrationBloomFilter");
        cachePenetrationBloomFilter.tryInit(1000000,0.01);
        return cachePenetrationBloomFilter;
    }
}
