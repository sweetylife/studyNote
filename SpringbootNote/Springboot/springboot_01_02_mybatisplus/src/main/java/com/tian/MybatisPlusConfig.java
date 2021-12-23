package com.tian;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import com.tian.plugins.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * @projectName: Springboot
 * @package: com.tian
 * @className: MybatisPlusConfig
 * @author: tian
 * @description: TODO
 * @date: 2021/12/20 14:59
 * @version: 1.0
 */

//标记为配置类
@Configuration
public class MybatisPlusConfig {

    @Bean //配置分页插件
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    @Bean //SQL分析插件(不建议在生产环境使用）
    public SqlExplainInterceptor sqlExplainInterceptor(){
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        ArrayList<ISqlParser> list = new ArrayList<>();
        list.add(new BlockAttackSqlParser()); //全表更新、删除的阻断器

        sqlExplainInterceptor.setSqlParserList(list);
        return sqlExplainInterceptor;
    }

    @Bean //注入自定义的拦截器
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    @Bean //乐观锁插件（还要在实体类中增减  @Version private Integer version;数据库中也需要加version字段)
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
