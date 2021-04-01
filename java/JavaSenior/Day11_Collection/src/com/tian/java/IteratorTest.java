package com.tian.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class IteratorTest {
    @Test
    public void test() {
        // 10.iterator():返回Iterator接口的实例，用于遍历集合元素
        // 10.1 内部的方法：hasNext()和next()
        // 10.2 集合对象每次调用iterator()方法都会得到一个全新的迭代器对象
        // 10.3 内部定义了remove(),可以在遍历的时候，删除集合中的元素。此方法不同于集合直接调用remove()
        //       如果还未调用next()或在上一次调用next方法之后已经调用了remove方法

        Collection col = new ArrayList();
        col.add(123);
        col.add(456);
        col.add(new String("Tom"));
        col.add(false);
        Iterator iterator = col.iterator();
        //方式一
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        //123
        //456
        //Tom
        //false
        //
        //java.util.NoSuchElementException

        //方式二
        for (int i = 0; i < col.size(); i++) {
            System.out.println(iterator.next());
        }

        //方式三（推荐）
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test2(){
        // remove()的使用
        Collection col = new ArrayList();
        col.add(123);
        col.add(456);
        col.add(new String("Tom"));
        col.add(false);

        //删除集合中“Tom”
        Iterator iterator = col.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            if ("Tom".equals(obj)){
                iterator.remove();
            }
        }

        //遍历集合
        Iterator iterator1 = col.iterator();
        while (iterator1.hasNext()){
            Object obj = iterator1.next();
            System.out.println(obj);
        }

    }
}
