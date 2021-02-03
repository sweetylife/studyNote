# OOP

## static

* 可以修饰：属性，方法，代码块，内部类

### 使用static修饰属性：静态变量（或类变量）

* 创建了类的多个对象，多个对象共享同一个对象。
* 静态变量随着类的加载而加载。（早于对象创建）
* 由于类只会加载一次，则静态变量在内存中也只会存在一份，存在方法区的静态域中。
* 使用场景：1.属性是共享的，2.常量

```java
public class StaticPerson{
    public static void main(String[] args) {
        Chinese c1=new Chinese();
        Chinese c2=new Chinese();
        c1.nation="China";
        System.out.println(c2.nation);//China
    }
}
class Chinese{
    public static String nation;
    String name;
    int age;
}
```

```java
public class StaticPerson{
    public static void main(String[] args) {
        Chinese.nation="China";
    }
}
class Chinese{
    public static String nation;
}
```

### 使用static修饰方法

* 静态方法中，只能调用静态方法或属性
* 非静态方法中，可以调用静态和非静态属性。
* 在静态方法中，不能使用this和super关键字。
* 什么时候使用：1.操作静态属性的方法，2.工具类中的方法

```java
public class StaticPerson{
    public static void main(String[] args) {
        Chinese.show();
    }
}
class Chinese{
    public static nation;
    public static void show(){
        System.out.println(nation);//省略的是Chinese.nation
    }
}
```

## 单例设计模式

* 饿汉式

```java
public class Singleton {
    public static void main(String[] args) {
        Bank bank1 = Bank.getInstance();
        Bank bank2 = Bank.getInstance();
        System.out.println(bank1==bank2);//true
    }
}
class Bank{
    // 1.构造器私有化
    private Bank(){

    }
    // 2.内部创建类的对象
    private static Bank instance = new Bank();
    // 3.提供公共的方法，返回类的对象
    public static Bank getInstance(){
        return instance;
    }
}
```

* 懒汉式(暂时不安全)

```java
public class SingletonTest {
    public static void main(String[] args) {
        Order order1 = Order.getInstance();
        Order order2 = Order.getInstance();
        System.out.println(order1 == order2); //true
    }
}

class Order {
    // 1.私有化构造器
    private Order() {

    }

    // 2.声明当前对象，没有初始化
    private static Order instance = null;

    // 3.返回类对象
    public static Order getInstance() {
        if (instance == null) {
            instance = new Order();
        }
        return instance;
    }
}
```

### 应用场景

* 网站的计数器
* 应用程序的日志应用
* 数据库连接池
* 读取配置文件的类
* Application 也是单例的典型应用
* windows的任务管理器
* 回收站

## main方法的理解

* main() 方法作为程序的入口
* 也是一个普通的静态方法
* main方法的形参也可以作为与控制台交互的方式

```java
public class MainDemo {
    public static void main(String[] args) {//setConfigure中可以设置该参数
        for(int i=0;i<args.length;i++){
            System.out.println(args[i]);
        }
    }
}
```

![图 1](../../images/623b87c53b684b5df14c95af2f0db03c11445c20830c04b652c7940587d0251c.png)  

## 代码块（初始化块）

* 1.作用：用来初始化类、对象
* 2.代码块如果有修饰的话只能是static
* 3.代码块分为：静态代码块和非静态代码块
* 4.代码块中可以输出语句

### 静态代码块

* 随着类的加载而执行（只执行一次）
* 多个静态代码块，按照声明的先后顺序执行
* 只能使用静态方法和属性

### 非静态代码块

* 随着对象的创建而执行
* 每创建一个对象就执行一次
* 作用：可以在创建对象时，对对象的属性进行初始化
* 默认赋值->显式赋值->代码块->构造器->对象方法
* 多个非静态代码块，按照声明的先后顺序执行
* 可以使用静态方法和属性或者非静态方法和属性

### 加载顺序

* 父静态代码块 --> 中静态代码块 --> 子静态代码块
* main方法
* 父代码块 父无参构造器 --> 中代码块 中无参构造器 --> 子代码块 子无参构造器 

