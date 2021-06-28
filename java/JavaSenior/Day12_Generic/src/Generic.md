# 泛型

## 1.在集合中使用泛型
* 集合接口或集合类在jdk5.0时都修改为带泛型的结构
* 在实例化集合类时，可以指明具体的泛型类型
* 指明完以后，在集合类或接口中凡是定义类或接口时，内部结构（比如：方法、构造器、属性）使用到类的泛型的位置，都指定为实例化时的泛型类型
* 泛型的类型必须是类，不能是基本数据类型，需要基本数据类型的时候，需要使用包装类
* 如果实例化时没有指明泛型的类型，默认是Object类型
* 静态方法中不能使用类的泛型
* 异常类不能声明为泛型类
* 构造T类型的数组
```java
//错误方法
//T[] array = new T[10];
//正确方法
T[] array = (T[]) new Object[10];
```

## 2.自定义泛型

```java
public class Order<T> {
    String orderName;
    int orderId;
//    类的内部结构就可以使用类的泛型
    T orderT;

    public Order() {
    }
    public Order(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }
}

class SubOrder extends Order<String>{
    //由于子类在继承带泛型的父类时，已经指明了泛型类型，则实例化子类对象时，不再需要指明泛型
    //SubOrder不再是泛型类
}
class SubOrder2<T> extends Order<T>{
    //子类中没有指明泛型类，SubOrder2仍然是泛型类
}
```

## 3.自定义泛型结构的种类

```java
class Father<T1,T2>{
    
}
//子类不保留父类泛型
//1.擦除
class Son<A,B> extends Father{
    //等价class Son extends Father<Object,Object>{}
}
//2.具体类型
class Son<A,B> extends Father<Integer,String>{
    //等价class Son extends Father<Object,Object>{}
}

//子类部分保留父类泛型
class Son<T2,A,B> extends Father<Integer,T2>{
    //等价class Son extends Father<Object,Object>{}
}
//子类全部保留父类泛型
class Son<T1,T2,A,B> extends Father<T1,T2>{
    //等价class Son extends Father<Object,Object>{}
}
```

## 4.泛型方法
* 泛型方法：在方法中出现泛型的结构，泛型参数与类的泛型参数没有任何关系
* 换句话说，泛型方法所属的类是不是泛型泛型类都没关系
* 泛型方法是可以声明为静态的。原因：泛型方法是在调用方式时确定的，并非在实例化类时确定。

```java
import java.util.ArrayList;
import java.util.List;

class Order {
    public <E> List<E> copyFromArrayToList(E[] arr) {
        ArrayList<E> list = new ArrayList<>();
        for (E e:arr){
            list.add(e);
        }
        return list;
    }
}

class Test {
    Order<String> order = new Order<>();
    Integer[] arr = new Integer[]{1,2,3,4};
    List<Integer> list = order.copyFromArrayToList(arr);
}

```
* 第一个<E>代表这是一个泛型方法
* 第二个<E>代表要返回的类型
* 第三个<E>代表接收的参数类型