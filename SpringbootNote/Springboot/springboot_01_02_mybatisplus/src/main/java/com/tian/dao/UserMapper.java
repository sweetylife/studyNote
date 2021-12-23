package com.tian.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tian.pojo.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {
    void deleteByMap();
}
