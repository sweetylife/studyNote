package com.tian.java;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapTest {
    //向TreeMap中添加Key-value,要求key必须是由同一个类创建的对象
    //因为要按照key进行排序：自然排序、定制排序


    //自然排序
    //需要User实现comparable，重写compareTo方法
    @Test
    public void test1(){
        TreeMap treeMap = new TreeMap();
        User tom = new User("Tom", 23);
        User jerry = new User("Jerry", 26);
        User jack = new User("Jack", 21);
        User rose = new User("Rose", 19);
        treeMap.put(tom,98);
        treeMap.put(jerry,89);
        treeMap.put(jack,76);
        treeMap.put(rose,100);

        Set set = treeMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //定制排序
    //根据key的CompareTo排序
    @Test
    public void test2(){
        TreeMap treeMap = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(),u2.getAge());
                }
                throw new RuntimeException("输入的类型不匹配");
            }
        });

        User tom = new User("Tom", 23);
        User jerry = new User("Jerry", 26);
        User jack = new User("Jack", 21);
        User rose = new User("Rose", 19);
        treeMap.put(tom,98);
        treeMap.put(jerry,89);
        treeMap.put(jack,76);
        treeMap.put(rose,100);

        Set set = treeMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
