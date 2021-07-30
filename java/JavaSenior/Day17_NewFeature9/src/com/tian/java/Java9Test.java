package com.tian.java;


import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Java9Test {
    public static void main(String[] args) {
        // jdk8之前的写法
//        InputStreamReader inputStreamReader = null;
//        try {
//            inputStreamReader = new InputStreamReader(System.in);
//            char[] chars = new char[20];
//            int len;
//            if((len= inputStreamReader.read(chars))!=-1){
//                String s = new String(chars, 0, len);
//                System.out.println(s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                inputStreamReader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        //jdk8中资源关闭操作
//        try(InputStreamReader inputStreamReader = new InputStreamReader(System.in)){
//            char[] chars = new char[20];
//            int len;
//            if((len= inputStreamReader.read(chars))!=-1){
//                String s = new String(chars, 0, len);
//                System.out.println(s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //jdk9中资源关闭操作
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        try(inputStreamReader){
            char[] chars = new char[20];
            int len;
            if((len= inputStreamReader.read(chars))!=-1){
                String s = new String(chars, 0, len);
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        try(InputStream is = classLoader.getResourceAsStream("hello.txt")){
            FileOutputStream os = new FileOutputStream("src\\hello1.txt");
            is.transferTo(os);//把输入流中的所有数据直接自动地复制到输出流中
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        //takeWhile返回从开头开始的按照指定规则的尽量多的元素
        List<Integer> integers = Arrays.asList(23, 12, 10, 32, 11, 17, 25, 67, 98);
        integers.stream().takeWhile(x->x>10).forEach(System.out::println);//23, 12
        //dropWhile 返回takeWhile剩余的元素
        integers.stream().dropWhile(x->x>10).forEach(System.out::println);//10, 32, 11, 17, 25, 67, 98
    }
    @Test
    public void test3(){
        //of可以包含null，但是不能只是null
        Stream.of(1,2,3,4,null);//可以
        //Stream.of(null);//报错

        Stream.ofNullable(null).forEach(System.out::println);//可以,且什么都不打印，count=0
    }
    @Test
    public void test4(){
        //jdk8中iterate
        Stream.iterate(0,x->x+1).limit(10).forEach(System.out::println);
        //jdk9中iterate的重载方法（可以增加自定义终止条件）
        Stream.iterate(0,x->x<100,x->x+1).forEach(System.out::println);
    }
    //java9新特性11：Optional类新方法stream()
    @Test
    public void test5(){
        List<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("Jerry");
        list.add("Tian");

        Optional<List<String>> optional = Optional.of(list);
        Stream<List<String>> stream = optional.stream();//count=1
        stream.flatMap(x->x.stream()).forEach(System.out::println);
    }
}
