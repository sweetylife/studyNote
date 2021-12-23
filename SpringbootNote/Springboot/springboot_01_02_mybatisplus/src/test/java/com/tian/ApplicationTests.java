package com.tian;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tian.dao.UserMapper;
import com.tian.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //插入数据
    @Test
    void testInsert(){
        User user = new User((long)8, "甜甜", "tiantian", "tian", 18, "123@qq.com","北京");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }


    //根据Id更新
    @Test
    void testUpdateById(){
        User user = new User((long) 8, "甜甜2", "tiantian2", "tian", 19, "123456@qq.com", "北京");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    //根据条件更新
    @Test
    void testUpate(){
        User user = new User((long) 8, "tiantian2", "tiantian2", "tian", 19, "123456@qq.com", "北京");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","甜甜");
        int update = userMapper.update(user, wrapper);
        System.out.println(update);
    }

    //根据条件更新
    @Test
    void testUpate2(){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_name","甜甜2").set("user_name","tiantian3");
        int update = userMapper.update(null, wrapper);
        System.out.println(update);
    }

    //删除操作
    @Test
    void testDeleteById(){
        int i = userMapper.deleteById(8);
        System.out.println(i);
    }

    //根据条件删除(多条件之间是AND的关系）
    @Test
    void testDeleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_name","tiantian2");
        map.put("password","tiantian2");
        int i = userMapper.deleteByMap(map);
        System.out.println(i);
    }

    //根据条件删除（多条件之间是AND的关系）
    @Test
    public void testDelete(){
        //用法1
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","sunqi").eq("password","12345");
        int delete = userMapper.delete(wrapper);
        System.out.println(delete);


        //用法2(推荐）
        User user = new User();
        user.setUserName("tiantian");
        user.setPassword("12345");
        QueryWrapper<User> wrapper1 = new QueryWrapper<>(user);
        int delete1 = userMapper.delete(wrapper1);
        System.out.println(delete1);
    }

    //根据id批量删除
    @Test
    void testDeleteBatchIds(){
        int i = userMapper.deleteBatchIds(Arrays.asList(10L, 11L));
        System.out.println(i);
    }
    //查询所有
    @Test
    void testSelectList() {
        List<User> users = userMapper.selectList(null);
        for (User user:users){
            System.out.println(user);
        }
    }
    //根据ID查询
    @Test
    void testSelectById(){
        User user = userMapper.selectById(6);
        System.out.println(user);
    }

    //批量查询
    @Test
    void testSelectBatch(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L));
        for (User user :users){
            System.out.println(user);
        }
    }

    //查询一条数据（如果不存在返回null，如果存在多条数据会抛出异常）
    @Test
    void testSelectOne(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","lisi");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    void testSelectCount(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",20);//年龄大于20岁
        Integer integer = userMapper.selectCount(wrapper);
        System.out.println(integer);
    }

    @Test
    void testSelectList2(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("user_name","li");
        List<User> users = userMapper.selectList(wrapper);
        for (User user:users){
            System.out.println(user);
        }
    }

    //分页查询
    @Test
    void testSelectPage(){
        Page<User> page = new Page<>(1,2);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("password","123456");
        Page<User> userPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数"+userPage.getTotal());
        System.out.println("数据总页数"+userPage.getPages());
        System.out.println("当前页"+userPage.getCurrent());

        List<User> records = userPage.getRecords();
        for (User user :records){
            System.out.println(user);
        }
    }

    //allEq的用法
    @Test
    void testEq(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","李四");
        map.put("age","20");
        map.put("password",null);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.allEq(map);//name==李四 且 name==20 且 password==null
//        wrapper.allEq(map,false); //name==李四 且 name==20
        wrapper.allEq((k,v)->(k.equals("age")|| k.equals("id")),map);//k为age或者id会作为过滤条件，其他不会
        List<User> users = userMapper.selectList(wrapper);
        for (User user:users){
            System.out.println(user);
        }
    }


}
