package io.yunplusplus.coupon.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "apache.rocketmq.consumer")
public class MqConsumerProperties {

    private String consumerGroup;
    private String namesrvAddr;
    private String consumerGroupPrefix;


    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getConsumerGroupPrefix() {
        return consumerGroupPrefix;
    }

    public void setConsumerGroupPrefix(String consumerGroupPrefix) {
        this.consumerGroupPrefix = consumerGroupPrefix;
    }
}
