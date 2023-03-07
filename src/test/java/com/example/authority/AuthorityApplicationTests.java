package com.example.authority;

import cn.hutool.json.JSONUtil;
import com.example.authority.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class AuthorityApplicationTests {
    @Resource
    UserService userService;

    @Test
    void contextLoads() {
    }
    @Test
    void TestAuth(){
       log.info( "测试枚举");

    }

}
