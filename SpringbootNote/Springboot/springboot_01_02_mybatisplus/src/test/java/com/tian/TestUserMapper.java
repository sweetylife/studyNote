package com.tian;

import com.tian.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @projectName: Springboot
 * @package: com.tian
 * @className: TestUserMapper
 * @author: tian
 * @description: TODO
 * @date: 2021/12/20 16:09
 * @version: 1.0
 */
@SpringBootTest
public class TestUserMapper {

    @Test
    void testSelectById(){
        User user = new User();
        user.setId(4L);
        User user1 = user.selectById();
        System.out.println(user1);

    }

    @Test
    public void testUpdateAll(){
        User user = new User();
        user.setAge(31);
        boolean update = user.update(null);
        System.out.println(update);
    }

}
