package com.tian.java;

import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionTest {
    @Test
    public void test(){
        Collection col= new ArrayList();

        //add(Object e)
        col.add("AA");
        col.add("BB");
        col.add(123);//自动装箱
        col.add(new Date());

        //size()
        System.out.println(col.size());

        //addAll()
        Collection col1=new ArrayList();
        col1.add("cc");
        col1.add(456);
        col.addAll(col1);//将col1的元素全部添加到col中
        System.out.println(col.size());
        System.out.println(col);

        //clear():清除集合元素
        col.clear();

        //isEmpty():判断当前集合是否为空
        System.out.println(col.isEmpty());


    }

    @Test
    public void test2(){
        Collection col=new ArrayList();

        col.add(123);
        col.add(456);
        col.add(new String("Tom"));
        col.add(false);
        col.add(new Person("Jerry",20));

        // 1.contains(Object obj)  在判断时会调用obj对象所在类的equals，所以向Collection接口的实现类的对象中添加数据obj时，
        // 要求obj所在的类要重写equals方法
        System.out.println(col.contains(123));//true
        System.out.println(col.contains(new String("Tom")));//true
        System.out.println(col.contains(new Person("Jerry",20)));//false->true

        //containsAll(Collection col):判断col中的所有元素是否都存在于当前集合中
        Collection col1 = Arrays.asList(123,456);
        System.out.println(col.containsAll(col1));
    }

    @Test
    public void test3(){
        Collection col= new ArrayList();
        col.add(1);//自动装箱
        col.add(2);
        col.add(3);
        col.add(4);
        col.add(6);

        //3.remove(Object obj)
        col.remove(4);
        System.out.println(col);//[1,2,3]

        //4.removeAll(Collection col1):从当前集合中移除col1中所有的元素（差集）
        Collection col1=Arrays.asList(1,2);
        col.removeAll(col1);
        System.out.println(col);//[3]

        //5.交集 retainAll(Collection col)
        Collection col2=Arrays.asList(3,5,6);
        col.retainAll(col2);
        System.out.println(col);//[3]

        //6.equals：要返回true，需要当前集合的元素与形参集合的元素相同，且顺序相同
        Collection col3=new ArrayList();
        col3.add(3);
        col3.add(6);
        System.out.println(col.equals(col3));//true  如果是[6,3]为false。因为ArrayList有序

        //7.hashcode()：返回当前对象的哈希值
        System.out.println(col.hashCode());

        //8.集合--->数组.toArray()
        Object[] arr = col.toArray();

        //9.数组--->集合 调用Arrays类的静态方法asList

        //9.1 不要用于基本类型：由于Arrays.ArrayList参数为可变长泛型，而基本类型是无法泛型化的，所以它把int[] arr数组当成了一个泛型对象，所以集合中最终只有一个元素arr。
        List list = Arrays.asList(new int[]{123,456});
        System.out.println(list);//[[I@e874448]
        List<int[]> list1 = Arrays.asList(new int[]{123,456});
        System.out.println(list1);//[[I@29b5cd00]

        //9.2 返回的是 java.util.Arrays.ArrayList，Arrays.ArrayList 是工具类 Arrays 的一个内部静态类，它没有完全实现List的方法，而 ArrayList直接实现了List 接口，实现了List所有方法。
        //    产生的集合并没有重写add,remove等方法，如果调用会报错

        //9.3 由于asList产生的集合元素是直接引用作为参数的数组，所以当外部数组或集合改变时，数组和集合会同步变化.
        String[] arr2=new String[]{"AA","BB","CC"};
        List<String> list3 = Arrays.asList(arr2);
        arr2[1]="我";
        list3.set(2,"爱");
        System.out.println(Arrays.toString(arr2));//[AA, 我, 爱]
        System.out.println(list3.toString());//[AA, 我, 爱]

        // 10.iterator():返回Iterator接口的实例，用于遍历集合元素

    }
}

class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
