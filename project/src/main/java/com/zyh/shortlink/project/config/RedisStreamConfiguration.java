package com.zyh.shortlink.project.config;

import com.zyh.shortlink.project.mq.consumer.ShortLinkStatsSaveConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.zyh.shortlink.project.common.constant.RedisKeyConstant.SHORT_LINK_STATS_STREAM_GROUP_KEY;
import static com.zyh.shortlink.project.common.constant.RedisKeyConstant.SHORT_LINK_STATS_STREAM_TOPIC_KEY;

/**
 * author 邹宇航  @vision 1.0
 */
@Configuration
@RequiredArgsConstructor
public class RedisStreamConfiguration {

    private final RedisConnectionFactory redisConnectionFactory;
    private final ShortLinkStatsSaveConsumer shortLinkStatsSaveConsumer;

    @Bean
    public ExecutorService asyncStreamConsumer(){
        AtomicInteger index = new AtomicInteger();
        return new ThreadPoolExecutor(1,
                      1,
                      60,
                      TimeUnit.SECONDS,
                      new SynchronousQueue<>(),
                      runnable->{
                          Thread thread=new Thread(runnable);
                          thread.setName("stream_consumer_short-link_stats_"+index.incrementAndGet());
                          thread.setDaemon(true);
                          return thread;
                      },
                      new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    @Bean
    public Subscription shortLinkStatsSaveConsumerSubscription(ExecutorService ayncStreamConsumer){
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> options = StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder()
                .batchSize(10)
                .executor(ayncStreamConsumer)
                .pollTimeout(Duration.ofSeconds(3))
                .build();
        StreamMessageListenerContainer.ConsumerStreamReadRequest<String> stringConsumerStreamReadRequest = StreamMessageListenerContainer.StreamReadRequest.builder(StreamOffset.create(SHORT_LINK_STATS_STREAM_TOPIC_KEY, ReadOffset.lastConsumed()))
                .cancelOnError(throwable -> false)
                .consumer(Consumer.from(SHORT_LINK_STATS_STREAM_GROUP_KEY, "stats-consumer"))
                .autoAcknowledge(true)
                .build();
        StreamMessageListenerContainer<String, MapRecord<String, String, String>> listenerContainer = StreamMessageListenerContainer.create(redisConnectionFactory, options);
        Subscription subscription = listenerContainer.register(stringConsumerStreamReadRequest, shortLinkStatsSaveConsumer);
        listenerContainer.start();
        return subscription;
    }
}
