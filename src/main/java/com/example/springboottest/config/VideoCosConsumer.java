package com.example.springboottest.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author wulei
 * @date 2018-11-26 16:13
 */
@Component
public class VideoCosConsumer {
    /*Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = {"test-topic"})
    public void consumerMessage(ConsumerRecord<?, ?> record){
        String s = record.value().toString();
        logger.info("kafka发送消息的内容:"+s);
    }*/
}
