# 15 Java 反射机制

## 反射的概述
从对象获取对象的类

## 反射的使用

```java
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
    //反射之前对Person的操作
    @Test
    public void test1(){
        //构造器
        Person tom = new Person("Tom", 12);
        //属性
        tom.age = 10;
        //方法
        tom.show();
    }
    //有反射之后
    @Test
    public void test2() throws Exception{
        Class clazz = Person.class;
        //构造器
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        Object tom = constructor.newInstance("Tom", 12);
        Person p = (Person) tom;
        //属性
        Field age = clazz.getDeclaredField("age");
        age.set(p,10);
        //方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        System.out.println("----------------------------------------------------------");
        //调用私有结构
        //构造器
        Constructor constructor1 = clazz.getDeclaredConstructor(String.class);
        constructor1.setAccessible(true);
        Object Jerry = constructor1.newInstance("Jerry");
        Person j = (Person) Jerry;
        //属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(j,"tian");
        //方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(j, "中国");
        System.out.println(nation);
    }
}

```