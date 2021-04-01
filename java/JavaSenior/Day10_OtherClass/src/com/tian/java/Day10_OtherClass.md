# 枚举类与注解

## 1 枚举类
### 1.1 枚举类的使用
* 枚举类的理解：类的对象只有有限个，确定的，称为枚举类
* 当需要定义一组常量时，建议使用枚举类
### 1.2 定义枚举类

#### 1.2.1 方法1

```java
package com.tian.java;

public class SeasonTest {
    public static void main(String[] args) {
        Season spring=Season.SPRING;
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
```

#### 1.2.2 方法二

```java
public class SeasonTest {
    public static void main(String[] args) {
        Seanson2 winter=Seanson2.WINTER;
        System.out.println(winter);
    }
}
enum Seanson2{
    SPRING("春天","春天在哪里"),
    SUMMER("夏天","夏天在哪里"),
    AUTUMN("秋天","秋天在哪里"),
    WINTER("冬天","冬天在哪里");

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
```

### 1.3 Enum常用方法

* values() 返回所有的枚举类对象
* valueOf(objName)根据objName返回对应的枚举对象，如果没有找到会抛出异常
* toString():返回当前枚举类对象常量的名称

```java
public class SeasonTest {
    public static void main(String[] args) {
        Seanson2[] values = Seanson2.values();
        System.out.println(Arrays.toString(values));
        //[SPRING, SUMMER, AUTUMN, WINTER]

        Seanson2 winter1 = Seanson2.valueOf("WINTER");
        System.out.println(winter1);
        //WINTER
    }
}
```

### 1.4 enum类实现接口

* 情况一：实现接口，在enum类中实现抽象方法
* 情况二：让枚举类的对象分别实现接口中的抽象方法

```java
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
```

## 2 注解
### 2.1 注解的概述
* 特殊标记 如：@Override
### 2.2 常见示例

* 示例一：生成文档相关的注解
    * @author
    * @version
    * @see 参考转向
    * @since 从哪个版本增加
    * @param 参数
    * @return 返回值的说明
    * @exception 可能异常说明
    
* 示例二：编译时进行格式检查
    * @Override
    * @Deprecated
    * @SuppressWarnings
    
* 示例三：跟踪代码依赖性，实现替代配置文件功能
    * @WebServlet("/login")     即访问login页面时执行该类
    
* 示例四：spring框架中关于“事务”的管理
    * @Test
    * @After
    * @AfterClass
    * @Before
    等等
      
### 2.3 自定义注解

* @interface声明
* 内部定义成员通常使用value表示
* 可以指定成员的默认值，使用default定义
* 如果自定义注解没有成员，表明是一个标识作用。
* 如果注解有成员，在使用时需要指明成员的值
* 自定义注解必须配上注解的信息处理流程才有意义

```java
public @interface MyAnnotation {
    String value() default "hi";//属性
}
@MyAnnotation(value="hello")
class Person{
    
}
```

### 2.4 jdk提供的元注解

* 元注解：对现有的注解进行解释说明的注解
    * Retention：指定所修饰的注解的生命周期：SOURCE、CLASS（默认）、RUNTIME（只有声明为RUNTIME生命周期的注解才能通过反射获取）
    * Target：用于指定被修饰的注解能修饰哪些程序元素
    * Documented：表示所修饰的注解在被javadoc解析时会保留
    * Inherited：被它修饰的注解会有继承性，比如Inherited修饰了@MyAnnotation，且@MyAnnotation注解Person，那么Person的子类也是被@MyAnnotation注解的

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({TYPE,FIELD,METHOD,PARAMETER})
@Retention(RetentionPolicy.SOURCE)
public @interface MyAnnotation {
    String value() default "hi";
}
```    

### 2.5 获取类的注解，通过反射获取注解信息（后续讲）

```java
import java.lang.annotation.Annotation;

class Test {
  public void test() {
    Class clazz = Student.class;
    Annotation[] annotations = clazz.getAnnotations();
  }
}

```

### 2.6 JDK8新特性 可重复注解

* jdk8之前

```java
@MyAnnotations({@MyAnnotation("hi"),@MyAnnotation("hi")})
class Person{
    
}
public @interface MyAnnotation {
  String value() default "hello";
}
public @interface MyAnnotations{
    MyAnnotation[] value();
}
```

* jdk8之后
* 1.在MyAnnotation上声明@repeatable，成员值为MyAnnotations.class
* 2.MyAnnotation的Target和Retention等元注解与MyAnnotations的元注解相同。

```java
import java.lang.annotation.Repeatable;

@MyAnnotation("hi")
@MyAnnotation("abc")
class Person {

}

@Repeatable(MyAnnotations.class)
public @interface MyAnnotation {
  String value() default "hello";
}
public @interface MyAnnotations{
  MyAnnotation[] value();
}
```

### 2.7 类型注解

```java
import java.util.ArrayList;

class Generic<@MyAnnotation T> {
  public void show() throws @myAnnotation RuntimeException {
    ArrayList<@MyAnnotation String> list = new ArrayList<>();
    int num = (@MyAnnotation int) 10L;
  }
}

@Target({TYPE,FIELD,METHOD,PARAMETER,TYPE_PARAMETER,TYPE_USE})
public @interface MyAnnotation {
  String value() default "hi";
}
```