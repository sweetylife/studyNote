package com.tian;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tian.dao.UserDao;
import com.tian.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        List<User> users = userDao.selectList(wrapper);
        for(User user:users){
            System.out.println(user);
        }
    }

}
