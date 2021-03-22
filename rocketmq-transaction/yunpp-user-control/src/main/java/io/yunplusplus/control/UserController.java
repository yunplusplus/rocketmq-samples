package io.yunplusplus.control;

import io.yunplusplus.common.ResponseResult;
import io.yunplusplus.service.api.user.dto.UserDto;
import io.yunplusplus.service.api.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author icefox
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public ResponseResult<?> getUserInfo(@RequestBody UserDto userDto) {
        return userService.getUserInfo(userDto);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseResult<?> register(@RequestBody UserDto userDto) {
        if (StringUtils.isEmpty(userDto.getUsername()) || StringUtils.isEmpty(userDto.getPassword())) {

            return ResponseResult.error(500, "用户名与密码不能为空");
        }
        try {
            return userService.register(userDto);
        } catch (Throwable e) {
            e.printStackTrace();

            return ResponseResult.error(500, e.getMessage());
        }

    }
}
