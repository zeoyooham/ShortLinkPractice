package com.zyh.shortlink.project.mq.consumer;

import com.alibaba.fastjson2.JSON;
import com.zyh.shortlink.project.common.convention.exception.ServiceException;
import com.zyh.shortlink.project.dao.mapper.*;
import com.zyh.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import com.zyh.shortlink.project.mq.idempotent.MessageQueueIdempotentHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import java.rmi.ServerException;
import java.util.Map;
import java.util.Objects;

/**
 * author 邹宇航  @vision 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ShortLinkStatsSaveConsumer implements StreamListener<String, MapRecord<String, String, String>> {

    private final ShortLinkMapper shortLinkMapper;
    private final ShortLinkGotoMapper shortLinkGotoMapper;
    private final RedissonClient redissonClient;
    private final LinkAccessStatsMapper linkAccessStatsMapper;
    private final LinkLocaleStatsMapper linkLocaleStatsMapper;
    private final LinkOsStatsMapper linkOsStatsMapper;
    private final LinkBrowserStatsMapper linkBrowserStatsMapper;
    private final LinkAccessLogsMapper linkAccessLogsMapper;
    private final LinkDeviceStatsMapper linkDeviceStatsMapper;
    private final LinkNetworkStatsMapper linkNetworkStatsMapper;
    private final LinkStatsTodayMapper linkStatsTodayMapper;
    private final StringRedisTemplate stringRedisTemplate;
    private final MessageQueueIdempotentHandler messageQueueIdempotentHandler;


    @Value("${short-link.stats.locale.amap-key}")
    private String statsLocaleAmapKey;

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        RecordId id = message.getId();
        String stream = message.getStream();
        if(messageQueueIdempotentHandler.isMessageBeingConsumed(id.toString())){
           if(messageQueueIdempotentHandler.isAccomplish(id.toString())){
               return;
           }
           throw new ServiceException("消息未完成流程，需要消息队列重试");
        }
        try{
            Map<String, String> producerMap = message.getValue();
            ShortLinkStatsRecordDTO statsRecord = JSON.parseObject(producerMap.get("statsRecord"), ShortLinkStatsRecordDTO.class);
            actualSaveShortLinkStats(statsRecord);
            messageQueueIdempotentHandler.setAccomplish(id.toString());
            stringRedisTemplate.opsForStream().delete(Objects.requireNonNull(stream),id.getValue());
        } catch (Throwable ex) {
            messageQueueIdempotentHandler.delMessageProcessed(id.toString());
            log.error("记录短链接监控消费异常", ex);
            throw ex;
        }
        messageQueueIdempotentHandler.isAccomplish(id.toString());
    }

    public void actualSaveShortLinkStats(ShortLinkStatsRecordDTO statsRecord){
    }
}
