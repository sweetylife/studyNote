package com.tian.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {

    @Test
    public void test(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(false);

        System.out.println(list);

        //1.void add(int index,Object ele):在index的位置插入ele元素
        list.add(1,"bb");
        System.out.println(list);

        //2.boolean addAll(int index,Collection eles):从index位置开始将eles中的所有元素添加到list中
        List list1 = Arrays.asList(1,2,3);
        list.addAll(list1);
        list.addAll(2,list1);
        System.out.println(list);

        //3.Object get(int index):获取指定index位置的元素
        System.out.println(list.get(2));

    }

    @Test
    public void test2(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(false);
        list.add(456);
        list.indexOf(456);
    }
}
