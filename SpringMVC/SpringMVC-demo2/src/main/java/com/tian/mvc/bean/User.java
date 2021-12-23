package com.tian.mvc.bean;

/**
 * @projectName: SpringMVC
 * @package: com.tian.mvc.bean
 * @className: User
 * @author: tian
 * @description: TODO
 * @date: 2021/12/17 10:46
 * @version: 1.0
 */
public class User {
    private int id;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
