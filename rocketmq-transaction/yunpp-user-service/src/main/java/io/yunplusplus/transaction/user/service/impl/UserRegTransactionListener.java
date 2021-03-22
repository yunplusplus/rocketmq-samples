package io.yunplusplus.transaction.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.yunplusplus.service.api.user.dto.UserDto;
import io.yunplusplus.transaction.user.dao.UserDao;
import io.yunplusplus.transaction.user.model.User;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserRegTransactionListener implements TransactionListener {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // 从消息体中解析出数据
        UserDto userDto = JSON.parseObject(msg.getBody(), UserDto.class);
        User user = new User();
        copy(userDto, user);
        user.setId(IdWorker.getIdStr());
        try {
            userDao.insert(user);
        } catch (Throwable e) {
            e.printStackTrace();
            // 表示系统异常，向客户端返回失败，可通过明确的 ROLLBACK 指令进行判断
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        // 返回 UNKNOW 因为此时事务还没有提交
        return LocalTransactionState.COMMIT_MESSAGE;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        UserDto userDto = JSON.parseObject(msg.getBody(), UserDto.class);
        User queryModel = new User();
        queryModel.setUsername(userDto.getUsername());
        // 根据 用户名（假设唯一，在其他业务场景下可能是订单编号或业务流水号），如果存在，说明本地事务执行成功，可以提交
        if (userDao.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, userDto.getUsername())) != null) {
            return LocalTransactionState.COMMIT_MESSAGE;
        } else {
            return LocalTransactionState.UNKNOW;
        }
    }

    private static void copy(UserDto userDto, User user) {
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setSex(userDto.getSex());
        user.setUsername(userDto.getUsername());
    }
}
