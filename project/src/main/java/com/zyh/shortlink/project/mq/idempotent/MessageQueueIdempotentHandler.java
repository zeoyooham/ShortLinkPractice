package com.zyh.shortlink.project.mq.idempotent;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * author 邹宇航  @vision 1.0
 */
@Component
@RequiredArgsConstructor
public class MessageQueueIdempotentHandler {

    private final StringRedisTemplate stringRedisTemplate;

    private static final String IDEMPOTENT_KEY_PREFIX = "short-link:idempotent:";

    private Boolean isMessageBeingConsumed(String id){
        String key= IDEMPOTENT_KEY_PREFIX + id;
        return Boolean.FALSE.equals(stringRedisTemplate.opsForValue().setIfAbsent(key, "0",2, TimeUnit.MINUTES));
    }

    private Boolean isAccomplish(String id){
        String key= IDEMPOTENT_KEY_PREFIX + id;
        return Objects.equals(stringRedisTemplate.opsForValue().get(key), "1");
    }

    private void setAccomplish(String id){
        String key= IDEMPOTENT_KEY_PREFIX + id;
        stringRedisTemplate.opsForValue().set(key, "1");
    }

    private void delMessageProcessed(String id){
        String key= IDEMPOTENT_KEY_PREFIX + id;
        stringRedisTemplate.delete(key);
    }
}
