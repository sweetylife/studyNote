# java10新特性

## 1.局部变量类型推断

### 适用情况

```java
import org.junit.Test;

import java.util.ArrayList;

public class Java10Test {
    @Test
    public void test1(){
        //1.声明变量时，根据所附的值，推断变量的类型
        var num=10;
        var integers = new ArrayList<Integer>();
        integers.add(123);

        //2.遍历操作
        for (var i : integers) {
            System.out.println(i);
        }
        //3.普通的遍历操作
        for (var i=0;i<100;i++){
            System.out.println(i);
        }
    }
}
```

### 不适用情况

* 没有初始化的局部变量声明
* 方法的返回类型
* 方法的参数类型
* 属性
* catch代码块

## 2.集合新增创建不可变集合的方法
List.copyOf(Xxx coll):如果参数coll本身就是一个只读集合，则直接返回coll。如果参数coll不是只读集合，会返回一个新的只读集合。
```java
class Test{
    @Test
    public void test2(){
        //示例1
        var list1 = List.of("java", "python", "C");
        var copy1 = List.copyOf(list1);
        System.out.println(copy1==list1);//true

        //示例2
        var list2 = new ArrayList<>();
        var copy2 = List.copyOf(list2);
        System.out.println(copy2==list2);//false
    }
}
```