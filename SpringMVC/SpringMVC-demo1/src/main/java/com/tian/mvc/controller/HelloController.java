package com.tian.mvc.controller;

import com.alibaba.fastjson.JSONObject;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

@Controller
public class HelloController {
    @RequestMapping("/request")
    public String request(){
        return "request";
    }
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String hello(String name) {
        System.out.println(name);
        return "success";
    }
    @RequestMapping(value = "/helloget", method = RequestMethod.GET)
    @ResponseBody
    public String helloget(String name) {
        System.out.println(name);
        return "success";
    }
}
