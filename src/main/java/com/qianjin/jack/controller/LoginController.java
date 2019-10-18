package com.qianjin.jack.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianjin.jack.domain.Result;
import com.qianjin.jack.domain.ResultException;
import com.qianjin.jack.domain.dao.UserInfo;
import com.qianjin.jack.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author volume
 * @date 2019/10/18 11:36
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Value("${yard}")
    private String yard;

    private final UserInfoMapper userInfoMapper;

    @Autowired
    public LoginController(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @PostMapping("/login")
    public Result login(String username, String password) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("password", DigestUtils.md5DigestAsHex(password.getBytes()));
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);

        if (userInfo == null) {
            throw new ResultException(ResultException.exceptionEnum.LoginError);
        }


    }

    @PostMapping("/register")
    public Result register(String username, String password) {
        UserInfo userInfo = new UserInfo(username,DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfoMapper.insert(userInfo);
        return Result.of("注册成功");
    }
}
