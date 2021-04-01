package com.tian.java;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarTest {

    @Test
    public void test() {

        //1.实例化
//        Calendar instance = Calendar.getInstance();//当前的时间
        GregorianCalendar instance = new GregorianCalendar();
        System.out.println(instance);//java.util.GregorianCalendar[time=1614741744514,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,useDaylight=false,transitions=31,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2021,MONTH=2,WEEK_OF_YEAR=10,WEEK_OF_MONTH=1,DAY_OF_MONTH=3,DAY_OF_YEAR=62,DAY_OF_WEEK=4,DAY_OF_WEEK_IN_MONTH=1,AM_PM=0,HOUR=11,HOUR_OF_DAY=11,MINUTE=22,SECOND=24,MILLISECOND=514,ZONE_OFFSET=28800000,DST_OFFSET=0]
        
        //2.常用方法
        //get()
        System.out.println(instance.get(Calendar.DAY_OF_MONTH));//这个月的第几天
        System.out.println(instance.get(Calendar.DAY_OF_YEAR));//这一年的第几天

        //set()
        instance.set(Calendar.DAY_OF_YEAR,61);

        //add()
        instance.add(Calendar.DAY_OF_MONTH,30);

        //getTime() 日历类--->Date
        Date time = instance.getTime();

        //setTime():Date--->日历类
        Date date = new Date();
        instance.setTime(date);


    }
}
