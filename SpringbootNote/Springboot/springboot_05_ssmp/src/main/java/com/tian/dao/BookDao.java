package com.tian.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tian.domain.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookDao extends BaseMapper<Book> {
}
