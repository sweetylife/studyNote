package com.tian.java;

import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionsTest {
    @Test
    public void test1(){
        List list = new ArrayList();
        list.add(123);
        list.add(45);
        list.add(789);
        list.add(-29);
        list.add(0);
        System.out.println(list);
        Collections.shuffle(list);
        Collections.sort(list);
        Collections.swap(list,1,3);
        System.out.println(list);

        List dest = Arrays.asList(new Object[list.size()]);
        Collections.copy(dest,list);
        dest.set(1,345);
        System.out.println(list);
        System.out.println(dest);

        //返回的list1是线程安全的
        List list1 = Collections.synchronizedList(list);
    }
}
