package io.yunplusplus.coupon.config;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@EnableConfigurationProperties(MqConsumerProperties.class)
public class MQConfig {

    /**
     * 消费者的组名
     */
    @Value("${apache.rocketmq.consumer.consumerGroup}")
    private String consumerGroup;

    @Value("${apache.rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;

    @Autowired
    private CouponMessageListenerConcurrently couponMessageListenerConcurrently;

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup, true);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeTimestamp(DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHmmss"));
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.registerMessageListener(couponMessageListenerConcurrently);
        try {
            consumer.subscribe("user_reg_topic", "*");
            consumer.start();
            System.out.printf("Consumer Started.%n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consumer;
    }

}
