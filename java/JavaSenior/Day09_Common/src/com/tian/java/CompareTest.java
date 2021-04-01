package com.tian.java;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class CompareTest {
    @Test
    public void test1(){
        Goods[] arr = new Goods[4];
        arr[0]=new Goods("candy",34);
        arr[1]=new Goods("candy3",37);
        arr[2]=new Goods("candy2",36);
        arr[3]=new Goods("candy1",35);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

    }

    @Test
    public void test2(){

        String[] arr=new String[]{"AA","BB","CC"};
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -o1.compareTo(o2);
            }
        });
        System.out.println(Arrays.toString(arr));

        System.out.println(System.getProperties());
    }
}