package com.tian.pojo;

/**
 * @projectName: Springboot
 * @package: com.tian.pojo
 * @className: User
 * @author: tian
 * @description: TODO
 * @date: 2021/12/20 13:38
 * @version: 1.0
 */
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.extension.activerecord.Model;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User extends Model<User> {
    @TableId(type = IdType.AUTO)  //设置id是自增长的
    private Long id;
    private String userName;
    @TableField(select = false) //设置查询时不返回
    private String password;
    private String name;
    private Integer age;

    @TableField(value = "email") //设置对应数据库中字段名
    private String mail;

    @TableField(exist = false) //设置在数据库表中是不存在的
    private String address;

//    @Version //乐观锁的版本字段
//    private Integer version;
 }


