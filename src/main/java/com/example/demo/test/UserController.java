package com.example.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.config.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Lazy
    private UserMapper userMapper;

    @ApiOperation("测试")
    @GetMapping
    public ResponseMessage index() {
        return ResponseMessage.success(userMapper.selectList(new QueryWrapper<>()));
    }
}
