package com.tian.service;

import com.tian.utils.token.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ projectName:    Springboot
 * @ package:        com.tian.service
 * @ className:      JWTTest
 * @ author:     tian
 * @ description:  TODO
 * @ date:    2021/12/23 17:43
 * @ version:    1.0
 */
@SpringBootTest
public class JWTTest {
    @Autowired
    private JWTUtils jwtUtils;
    @Test
    void test1(){
        String sub = JWTUtils.createToken("sub");
        System.out.println(sub);
    }
    @Test
    void test2(){
        String str= "TianeyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0OCIsImV4cCI6MTY0MDI2Mjg4OX0.q27ecnFYH9etZ_k6McbJniMs-icns0TJTDwheGqPFRiWUIEg2XLgrbh37krCfQlVDuI32Tr-SS8BGQTWukx_eQ";
        String s = JWTUtils.validateToken(str);
        System.out.println(s);
    }
}
