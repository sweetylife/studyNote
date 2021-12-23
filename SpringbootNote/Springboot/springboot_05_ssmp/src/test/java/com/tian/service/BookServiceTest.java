package com.tian.service;

import com.tian.domain.Book;
import com.tian.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ projectName: Springboot
 * @ package: com.tian.service
 * @ className: BookServiceTest
 * @ author: tian
 * @ description: TODO
 * @ date: 2021/12/21 16:42
 * @ version: 1.0
 */
@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookServiceImpl bookServiceImpl;
    @Test
    void test1(){
        List<Book> list = bookServiceImpl.list();
        for (Book book:list){
            System.out.println(book);
        }
    }
    @Test
    void test2(){
        boolean delete = bookServiceImpl.delete(3);
        System.out.println(delete);
    }
}
