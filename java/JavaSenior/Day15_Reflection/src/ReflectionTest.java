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

    @Test
    public void test3() throws ClassNotFoundException {
        //方式1：调用运行时类的属性.class
        Class aClass = Person.class;
        //方式2：通过运行时类的对象，调用getClass()
        Person person = new Person();
        Class aClass1 = person.getClass();
        //方式3：调用Class的静态方法：forName（String classPath）
        Class aClass3 = Class.forName("Person");
        //方法4：使用类的加载器 ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class aCLass4 = classLoader.loadClass("Person");
    }
}

