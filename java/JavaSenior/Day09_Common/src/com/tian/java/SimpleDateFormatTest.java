package com.tian.java;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {

    @Test
    public  void  test(){

        //方法一：使用默认构造器格式化和解析
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        //格式化：日期--->文本
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);//2021/3/2 下午4:09
        //解析：文本--->日期
        String str="2019/08/09 上午11:34";
        try {
            Date parse = simpleDateFormat.parse(str);
            System.out.println(parse);//Fri Aug 09 11:34:00 CST 2019
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //方法二：指定的方式格式化和解析
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //格式化：日期--->文本
        System.out.println(simpleDateFormat1.format(new Date()));//2021-03-02 04:09:03
        //解析：文本--->日期
        String str1="2019-08-09 11:34:30";
        try {
            Date parse = simpleDateFormat1.parse(str1);
            System.out.println(parse);//Fri Aug 09 11:34:30 CST 2019
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
