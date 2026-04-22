package com.zyh.shortlink.project.mq.consumer;

import com.zyh.shortlink.project.dao.mapper.LinkAccessStatsMapper;
import com.zyh.shortlink.project.dao.mapper.ShortLinkGotoMapper;
import com.zyh.shortlink.project.dao.mapper.ShortLinkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

/**
 * author 邹宇航  @vision 1.0
 */
@Component
@RequiredArgsConstructor
public class ShortLinkStatsSaveConsumer implements StreamListener<String, MapRecord<String, String, String>> {

    private final ShortLinkMapper shortLinkMapper;
    private final ShortLinkGotoMapper shortLinkGotoMapper;
    private final LinkAccessStatsMapper linkAccessStatsMapper;


    @Override
    public void onMessage(MapRecord<String, String, String> message) {

    }
}
