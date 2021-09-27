package com.example.springboottest.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

/**
 * @author wulei
 * @date 2018-11-26 16:10
 */
@Component
@Slf4j
public class KafkaProducer {
    private Logger logger = LoggerFactory.getLogger(getClass());

  /*  @Value("${topic}")
    private String topic;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String message){
        logger.info("message:"+message);
        ListenableFuture<SendResult<String, Object>> test = kafkaTemplate.send(topic,message);
        SuccessCallback success = new SuccessCallback() {
            @Override
            public void onSuccess(Object o) {
                System.out.println(o + "成功了");
            }
        };
        FailureCallback fail = new FailureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("失败原因:" + throwable);
            }
        };
        test.addCallback(success,fail);
    }*/

}
