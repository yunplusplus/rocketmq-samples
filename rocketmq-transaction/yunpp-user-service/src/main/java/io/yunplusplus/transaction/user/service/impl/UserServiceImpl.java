package io.yunplusplus.transaction.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.yunplusplus.transaction.config.TransactionMQProducerContainer;
import io.yunplusplus.service.api.user.dto.UserDto;
import io.yunplusplus.service.api.user.service.UserService;
import io.yunplusplus.transaction.user.dao.UserDao;
import io.yunplusplus.transaction.user.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.yunplusplus.common.ResponseResult;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private TransactionMQProducerContainer transactionMQProducerContainer;

    @Autowired
    private UserRegTransactionListener userLoginTransactionListener;

    @Override
    public ResponseResult<?> register(UserDto userDto) {
        try {
            // 校验参数
            checkParam(userDto);
            // 事务消息发送，为了方便对消息进行查找，建议对消息加上 key
            TransactionSendResult sendResult = transactionMQProducerContainer.getTransactionMQProducer("user_reg_topic", userLoginTransactionListener)
                    .sendMessageInTransaction(new Message("user_reg_topic", null, userDto.getUsername(), JSON.toJSONString(userDto).getBytes()), null);

            // 事务消息发送成功，返回注册成功，因为是事务消息发送接口是同步调用纺纱，该方法调用结束后，可以根据对状态进行判断业务是否成功
            // userRegTransactionListener 的 executeLocalTransaction 执行本地方法已经结束
            if (sendResult.getSendStatus().equals(SendStatus.SEND_OK) &&
                    !sendResult.getLocalTransactionState().equals(LocalTransactionState.ROLLBACK_MESSAGE)) {
                return ResponseResult.ok();
            } else {
                return ResponseResult.error(500, sendResult.getLocalTransactionState().equals(LocalTransactionState.ROLLBACK_MESSAGE) ? "业务异常" : "系统繁忙");
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return ResponseResult.error(500, e.getMessage());
        }
    }

    @Override
    public ResponseResult<?> getUserInfo(UserDto userDto) {
        UserDto result = new UserDto();
        User user = userDao.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, userDto.getUsername()));
        if (user == null) {
            return ResponseResult.error(404, "无此用户");
        }
        copy(user, result);
        return ResponseResult.ok(result);
    }

    private static void checkParam(UserDto userDto) {
        if (StringUtils.isEmpty(userDto.getUsername()) ||
                StringUtils.isEmpty(userDto.getPassword())) {
            throw new RuntimeException("非法参数");
        }
    }

    private static void copy(User user, UserDto userDto) {
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setSex(user.getSex());
        userDto.setUsername(user.getUsername());
    }
}
