# java9的新特性

## 1.jdk目录结构变化
## 2.模块化系统

注意：如果使用该方式，那么该module下所有的类都需要导入

```java
//module1下的src文件夹下new module-info.java文件
module module1 {
    requires module2;
}
```
```java
//module2下的src文件夹下new module-info.java文件（com.tian.bean是module2,src中的package）
module module2 {
    exports com.tian.bean;
}
```

## 3.Repl工具 jShell命令
在cmd中jshell回车后，可直接输入代码执行

## 4.语法改进：接口的私有方法
接口中的方法的访问权限修饰符添加了private,私有的接口方法不能在接口之外调用

## 5.语法改进：钻石操作符的使用<>
钻石操作符与匿名实现类不能共存，在9中才可以使用

## 6.try操作的升级
* java8中，可以实现资源的自动关闭，但是要求执行后必须在try括号中初始化，否则编译不通过
* java9中可以在外边声明，但在try内部不可修改，此时的资源是final的

```java
package com.tian.java;

import java.io.IOException;
import java.io.InputStreamReader;

public class Java9Test {
    public static void main(String[] args) {
        // jdk8之前的写法
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(System.in);
            char[] chars = new char[20];
            int len;
            if((len= inputStreamReader.read(chars))!=-1){
                String s = new String(chars, 0, len);
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //jdk8中资源关闭操作
        try(InputStreamReader inputStreamReader = new InputStreamReader(System.in)){
            char[] chars = new char[20];
            int len;
            if((len= inputStreamReader.read(chars))!=-1){
                String s = new String(chars, 0, len);
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
}
```

## 7.String存储结构变更

9及以上变成了byte[]  而不是char[],基于String的其他类的存储结构都变成了byte[]

## 8.集合工厂方法：快速创建只读集合

```java
package com.tian.java;

import org.junit.jupiter.api.Test;

import java.util.*;

public class Java9Test1 {
    //java9新特性：集合工厂方法：创建只读集合
    @Test
    public void test(){
        //jdk8中创建只读集合1
        List<String> nameList = new ArrayList<>();
        nameList.add("Tom");
        nameList.add("Jerry");
        nameList.add("tian");
        List<String> list = Collections.unmodifiableList(nameList);
//        list.add("yyy"); 返回的list是只读的集合

        //jdk8中创建只读集合2
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        //integers.add(6); integers是只读集合，添加数据会报错

        //jdk9中创建只读集合
        List<Integer> integers1 = List.of(1, 2, 3, 4, 5);
        Set<Integer> integers2 = Set.of(1, 2, 3, 4, 5);
        Map<String, Integer> tom = Map.of("Tom", 12, "jerry", 45);
        Map<String, Integer> stringIntegerMap = Map.ofEntries(Map.entry("Tom", 12), Map.entry("Jerry", 12));
    }
}

```

## 9.InputStream的新方法：transferTo()

```java
class Test{
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
}
```

## 10.增强的StreamAPI

```java
class Test{
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
```

## 11 JavaScript引擎升级：Nashorn
