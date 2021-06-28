package com.tian.java;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class SetTest {
    @Test
    public void test1(){
        new HashMap<>();
        TreeSet treeSet = new TreeSet();
        treeSet.add(new User("Tome",12));
        treeSet.add(new User("Jerry",32));
        treeSet.add(new User("Mike",15));
        treeSet.add(new User("Jack",19));
        treeSet.add(new User("Jack",20));

    }

    @Test
    public void test2(){
        Comparator com = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(),u2.getAge());
                }else {
                    throw new RuntimeException("输入的数据类型不匹配");
                }
            }
        };
        TreeSet treeSet = new TreeSet(com);

    }
}
class User implements Comparable{
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof User){
            User user = (User) o;
            int compare =  this.name.compareTo(user.name);
            if(compare!=0){
                return compare;
            }else {
                return Integer.compare(this.age,user.age);
            }
        }else {
            throw new RuntimeException("输入的类型不匹配");
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
