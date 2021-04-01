package com.tian.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

public class ForTest {
    @Test
    public void test(){
        Collection col=new ArrayList();
        col.add(123);
        col.add(456);
        col.add(new String("Tom"));
        col.add(false);

        // for(集合元素的类型  局部变量：集合的对象
        // 内部仍然调用的是迭代器
        for (Object obj: col){
            System.out.println(obj);
        }
    }

    @Test
    public void test2(){
        int[] arr= new int[]{1,2,3,4,5,6};
        for (int i:arr){
            System.out.println(i);
        }
    }

    @Test
    public void test3(){
        String[] arr = new String[]{"MM","MM"};

        // 方式一：普通for赋值(arr的值改变）
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "GG";
        }
        // 方式二：增强for循环(arr的值不变）
        for (String s:arr){
            s="NN";
        }
    }
}
