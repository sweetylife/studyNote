package com.tian.dao.impl;

import com.tian.dao.BookDao;
import org.springframework.stereotype.Repository;

/**
 * @projectName: Springboot
 * @package: com.tian.dao.impl
 * @className: BookDaoImpl
 * @author: tian
 * @description: TODO
 * @date: 2021/12/17 17:08
 * @version: 1.0
 */
@Repository
public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("book dao is running----");
    }
}
