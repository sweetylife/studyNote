package com.tian.java;

import org.junit.jupiter.api.Test;

import java.util.*;

public class Java9Test1 {
    //java9新特性：集合工厂方法：创建只读集合
    @Test
    public void test(){
        //jdk8中创建只读集合1
        List<String> nameList = new ArrayList<>();
        nameList.add("Tom");
        nameList.add("Jerry");
        nameList.add("tian");
        List<String> list = Collections.unmodifiableList(nameList);
//        list.add("yyy"); 返回的list是只读的集合

        //jdk8中创建只读集合2
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        //integers.add(6); integers是只读集合，添加数据会报错

        //jdk9中创建只读集合
        List<Integer> integers1 = List.of(1, 2, 3, 4, 5);
        Set<Integer> integers2 = Set.of(1, 2, 3, 4, 5);
        Map<String, Integer> tom = Map.of("Tom", 12, "jerry", 45);
        Map<String, Integer> stringIntegerMap = Map.ofEntries(Map.entry("Tom", 12), Map.entry("Jerry", 12));
    }
}
