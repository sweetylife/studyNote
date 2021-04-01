package com.tian.java;

import org.junit.jupiter.api.Test;

public class StringTest {

    @Test
    public  void test5(){
        String a=new String("A");
        String b=new String("B");
        String s2=a+b;//又new了一个 常量池3个，堆空间3个
        System.out.println("AB"==s2);
    }

    @Test
    public void test4(){
//        String b="edf";
//        String a=new String("abc");
//        a="edf";
//        System.out.println(a==b);
          String str2 = new String("ABC");
//        System.out.println(str2.hashCode());
          str2=str2+"ABC";
//        System.out.println(str2=="ABCABC");
//        System.out.println(str2.hashCode());

        String s1="A"+"B";//常量池（A B AB）堆空间无   s1="AB"
        String s3=new String("A"+"B");//堆（一个指向AB）-->常量池（A B AB)

        String a="A";
        String b="B";
        String s5=a+b;//先相加再new（相当于s3）
        System.out.println(StringTest.inPool(s5));
//        String s2=new String("A")+new String("B");//常量池（A B AB），堆中三个，指向A B的对象被回收
//        String s4=new String("A")+"B";//常量池（A，B，AB）堆中两个（指向A，指向AB），指向A的对象被回收
    }

    @Test
    public  void test3(){
        String a=new String("abc");
        System.out.println(a.hashCode());

        String b= a;
        System.out.println(a==b);

        b="edf";
        System.out.println(a==b);
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());


    }

    @Test
    public void test2() {
        String s1 = "abc";
        String s2 = "def";

        String s3 = "abcdef";
        String s4 = "abc" + "def";
        String s5 = s1 + "def";
        String s6 = "abc" + s2;
        String s7 = s1 + s2;

    }

    @Test
    public void test() {
        String s1 = "abc";//字面量
        String s2 = "abc";
        System.out.println(s1);
        System.out.println(s2);
    }

    public static boolean inPool(String s) {
        String s2 = s.intern();
        if (s2 == s)
            return true;
        return false;
    }
}
