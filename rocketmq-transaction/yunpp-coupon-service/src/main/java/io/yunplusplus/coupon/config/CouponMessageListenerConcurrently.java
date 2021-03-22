package io.yunplusplus.coupon.config;

import com.alibaba.fastjson.JSON;
import io.yunplusplus.coupon.dao.CouponDao;
import io.yunplusplus.coupon.model.Coupon;
import io.yunplusplus.service.api.coupon.dto.CouponDto;
import io.yunplusplus.service.api.coupon.service.CouponService;
import io.yunplusplus.service.api.user.dto.UserDto;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CouponMessageListenerConcurrently implements MessageListenerConcurrently {

    @Autowired
    private CouponService couponService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
        for (MessageExt msg : msgs) {
            UserDto userDto = JSON.parseObject(msg.getBody(), UserDto.class);
            System.out.println(JSON.toJSONString(userDto, true));
            String userName = userDto.getUsername();
            CouponDto couponDto = new CouponDto();
            couponDto.setUsername(userName);
            couponDto.setCouponId("1111111");
            couponDto.setCouponName("10元消费券");
            couponDto.setDenomination(10);
            couponService.saveUserCoupon(couponDto);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
