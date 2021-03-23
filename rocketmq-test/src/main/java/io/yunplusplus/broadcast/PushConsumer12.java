/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.yunplusplus.broadcast;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class PushConsumer12 {

    public static void main(String[] args) throws InterruptedException, MQClientException {
        String consumerGroup = "ypp-consumer-producer11";
        for (int i = 4; i < 6; i++
        ) {
            int finalI = i;
            new Thread(() -> {
                DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
                consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
                consumer.setInstanceName("" + finalI);
                consumer.setMessageModel(MessageModel.BROADCASTING);
                consumer.setNamesrvAddr("127.0.0.1:9876");
                try {
                    consumer.subscribe("user_reg_topic", "*");
                } catch (MQClientException e) {
                    e.printStackTrace();
                }
                consumer.registerMessageListener(new MessageListenerConcurrently() {
                    @Override
                    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                    ConsumeConcurrentlyContext context) {
                        // System.out.printf("consumerGroup: %s ,instanceName :%s  , %s Receive New Messages: %s %n", consumerGroup, consumer.getInstanceName(), Thread.currentThread().getName(), msgs);
                        for (MessageExt msg : msgs) {
                            System.out.printf("consumerGroup: %s ,instanceName :%s  , %s Receive New Message: %s %n", consumerGroup, consumer.getInstanceName(), Thread.currentThread().getName(), new String(msg.getBody()));
                        }
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                });

                try {
                    consumer.start();
                } catch (MQClientException e) {
                    e.printStackTrace();
                }
                System.out.printf("Broadcast Consumer Started.%n");
            }).start();
        }

    }
}