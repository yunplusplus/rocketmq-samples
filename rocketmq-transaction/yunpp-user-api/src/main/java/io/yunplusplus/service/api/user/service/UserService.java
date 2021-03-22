package io.yunplusplus.service.api.user.service;

import io.yunplusplus.common.ResponseResult;
import io.yunplusplus.service.api.user.dto.UserDto;

/**
 * @author icefox
 */
public interface UserService {

    /**
     * 注册用户
     *
     * @param userDto UserDto
     * @return ResponseResult<?>
     */
    ResponseResult<?> register(UserDto userDto);

    /**
     * 获取用户信息
     *
     * @param userDto UserDto
     * @return ResponseResult<?>
     */
    ResponseResult<?> getUserInfo(UserDto userDto);

}
