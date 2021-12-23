package com.tian;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tian.dao.BookDao;
import com.tian.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SsmpApplicationTests {

    @Autowired
    private BookDao bookDao;

    @Test
    void contextLoads() {
        List<Book> books = bookDao.selectList(null);
        for (Book book:books){
            System.out.println(book);
        }
    }

    @Test
    void testSave(){
        Book book = new Book();
        book.setName("甜甜的书");
        book.setType("甜甜类型");
        book.setDes("甜甜的描述");
        int insert = bookDao.insert(book);
        System.out.println(insert);
    }

    //分页查询
    @Test
    void testPageSelect(){
        Page<Book> page = new Page<>(2,4);
        Page<Book> bookPage = bookDao.selectPage(page, null);
        for (Book book:bookPage.getRecords()){
            System.out.println(book);
        }
    }

    //条件查询
    @Test
    void testGet(){
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Book::getName, "沟通指南");
        List<Book> books = bookDao.selectList(wrapper);
        for (Book book:books){
            System.out.println(book);
        }
    }
}
