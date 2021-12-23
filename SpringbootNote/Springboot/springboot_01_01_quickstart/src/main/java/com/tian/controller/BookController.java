package com.tian.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: Springboot
 * @package: com.tian.controller
 * @className: BookController
 * @author: tian
 * @description:
 * @date: 2021/12/17 11:44
 * @version: 1.0
 */
//@RestController是@Controller 和 @ResponseBody 两个注解的结合体
@RestController
@RequestMapping("/books")
public class BookController {
    @GetMapping
    public String getById() {
        System.out.println("Springboot is running");
        return "Springboot is running";
    }
}
