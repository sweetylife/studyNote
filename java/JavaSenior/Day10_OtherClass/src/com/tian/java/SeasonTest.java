package com.tian.java;

import java.util.Arrays;

public class SeasonTest {
    public static void main(String[] args) {
        Season spring=Season.SPRING;
        Seanson2 winter=Seanson2.WINTER;
        System.out.println(winter);

        Seanson2[] values = Seanson2.values();
        System.out.println(Arrays.toString(values));

        Seanson2 winter1 = Seanson2.valueOf("WINTER");
        System.out.println(winter1);
    }
}
class Season{

    //1.声明Season对象的属性:private final
    private final String seasonName;
    private final String seasonDesc;
    //2.私有化的构造器，并给对象属性赋值
    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
    //3.提供当前枚举类的多个对象：public static final
    public static final Season SPRING = new Season("春天","春天在哪里");
    public static final Season SUMMER = new Season("夏天","夏天在哪里");
    public static final Season AUTUMN = new Season("秋天","秋天在哪里");
    public static final Season WINTER = new Season("冬天","冬天在哪里");
    //4.获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
    //5.提供toString方法

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}

interface Info{
    void show();
}
enum Seanson2 implements Info {
    SPRING("春天","春天在哪里"){
        @Override
        public void show() {
            System.out.println("春天");
        }
    },
    SUMMER("夏天","夏天在哪里"){
        @Override
        public void show() {
            System.out.println("夏天");
        }
    },
    AUTUMN("秋天","秋天在哪里"){
        @Override
        public void show() {
            System.out.println("秋天");
        }
    },
    WINTER("冬天","冬天在哪里"){
        @Override
        public void show() {
            System.out.println("冬天");
        }
    };

    String seasonName;
    String seasonDesc;

    Seanson2(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
}