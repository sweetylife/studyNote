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

        // 4.int indexOf(Object obj):返回obj在集合中首次出现的位置
        System.out.println(list.indexOf(456));
        // 5.int lastIndexOf(Object obj):返回obj在集合中最后一次出现的位置
        System.out.println(list.lastIndexOf(456));
        // 6.Object remove(int index,Object obj):移除指定index位置的元素，并返回此元素,默认是index
        Object remove = list.remove(0);
        System.out.println(remove);
        // 7.Object set(int index,Object ele):设置指定index位置的元素ele
        list.set(1,"cc");
        System.out.println(list);
        // 8.List subList(int fromIndex,int toIndex):返回左闭右开区间的子集合,原本的list不改变
        List list1 = list.subList(2, 4);
        System.out.println(list1);
        System.out.println(list);

    }
}
