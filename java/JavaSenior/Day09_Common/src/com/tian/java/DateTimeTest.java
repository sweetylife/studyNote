package com.tian.java;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateTimeTest {

    //    1.System类中的currentTimeMillis()获取时间戳
    @Test
    public void tes1() {
        long time = System.currentTimeMillis();
        System.out.println(time);
    }

    //    2.java.util.Date类
    //                |----java.sql.Date
    @Test
    public void test2() {
        //构造器一：Date():创建一个当前时间的Date对象
        Date date = new Date();
        System.out.println(date.toString());//Fri Feb 26 17:23:12 CST 2021
        System.out.println(date.getTime());//1614331446969(毫秒）

        //构造器二：创建指定好描述的Date对象
        Date date1 = new Date(1614331446969L);
        System.out.println(date1.toString());//Fri Feb 26 17:24:06 CST 2021

        //创建java.sql.Date对象
        java.sql.Date date2=new java.sql.Date(1614331446969L);
        System.out.println(date2);//2021-02-26

        //util.Date----->sql.Date
        Date date3=new Date();
        java.sql.Date date4= new java.sql.Date(date3.getTime());


    }

    @Test
    public void test3(){

        //now() 获取当前的时间
        LocalDate now = LocalDate.now();//2021-03-03
        LocalTime now1 = LocalTime.now();//15:00:38.339705500
        LocalDateTime now2 = LocalDateTime.now();//2021-03-03T15:00:38.339705500

        //of()  设置指定的年月日时分秒，没有偏移量
        LocalDateTime time = LocalDateTime.of(2021, 3, 3, 13, 23, 43);

        //getXxx()
        System.out.println(time.getDayOfYear());
        System.out.println(time.getDayOfWeek());
        System.out.println(time.getMonth());
        System.out.println(time.getMonthValue());
        System.out.println(time.getMinute());

        //不可变性
        LocalDateTime time1 = time.withDayOfMonth(22);
        System.out.println(time);
        System.out.println(time1);

        //添加
        System.out.println(now2.plusDays(3));
        System.out.println(now2.minusDays(4));

    }

    @Test
    public void test4(){
        //now():获取本初子午线对应的标准时间
        Instant instant = Instant.now();
        System.out.println(instant);//2021-03-03T08:21:35.277030100Z

        //添加时间的偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);//2021-03-03T16:21:35.277030100+08:00

        //获取对应的毫秒数
        long milli = instant.toEpochMilli();
        System.out.println(milli);//1614759880439


        //ofEpochMilli() 通过给定的毫秒数，获取Instant实例 ----->Date(long millis)
        Instant instant1 = Instant.ofEpochMilli(1614759880439L);
    }

    @Test
    public void test5(){
        //方式一：预定义的标准格式，如：ISO_LOCAL_DATE_TIME,ISO_LOCAL_DATE,ISO_DATE_TIME
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String format = formatter.format(LocalDateTime.now());//2021-03-03T16:53:20.8683418
        System.out.println(formatter.parse(format));//{},ISO resolved to 2021-03-03T16:54:16.807828900

        //方式二：本地化相关格式
        //ofLocalizedDateTime()适用于LocalDateTime  参数有：FormatStyle.LONG，FormatStyle.MEDIUM,FormatStyle.SHORT
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withZone(ZoneId.systemDefault());
        String format1 = formatter1.format(LocalDateTime.now());//2021年3月3日 CST 下午5:13:07,  2021年3月3日 下午5:00:34 , 2021/3/3 下午4:59
        System.out.println(formatter1.parse(format1));//{InstantSeconds=1614763276},ISO,Asia/Shanghai resolved to 2021-03-03T17:21:16

        //ofLocalizedDate()适用于LocalDate 参数有:FormatStyle.FULL,FormatStyle.LONG，FormatStyle.MEDIUM,FormatStyle.SHORT
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        String format2 = formatter2.format(LocalDate.now());//2021年3月3日星期三,2021年3月3日,2021年3月3日,2021/3/3
        System.out.println(formatter2.parse(format2));//{},ISO resolved to 2021-03-03

        //方式三：自定义的格式(常用）
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String format3 = formatter3.format(LocalDateTime.now());//2021-03-03 05:25:45
        System.out.println(formatter3.parse(format3));//{MicroOfSecond=0, MilliOfSecond=0, NanoOfSecond=0, MinuteOfHour=25, HourOfAmPm=5, SecondOfMinute=15},ISO resolved to 2021-03-03

    }
    @Test
    public void test6(){
        TemporalAdjuster temporalAdjuster = new TemporalAdjuster() {
            @Override
            public Temporal adjustInto(Temporal temporal) {
                return LocalDate.of(2021,8,10);
            }
        };
        System.out.println(LocalDate.now().with(temporalAdjuster));
    }
}
