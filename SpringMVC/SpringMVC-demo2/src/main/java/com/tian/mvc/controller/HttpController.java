package com.tian.mvc.controller;

import com.tian.mvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @projectName: SpringMVC
 * @package: com.tian.mvc.controller
 * @className: HttpController
 * @author: tian
 * @description: TODO
 * @date: 2021/12/16 19:47
 * @version: 1.0
 */
@Controller
public class HttpController {
    @RequestMapping("/testRequestBody")
    @ResponseBody
    public User testRequestBody(String requestBody){
        System.out.println("requestBody"+requestBody);
        return new User(1001,"甜甜",18);
    }
}
