import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Properties;
import java.util.Random;

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

    @Test
    public void test4() throws IOException {
        Properties pros = new Properties();
        //读取配置文件的方式一:(路径相对于module）
        FileInputStream fis = new FileInputStream("src\\user.properties");
        pros.load(fis);

        //读取配置文件方式二：（路径相对于src）
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("user.properties");
        pros.load(is);

        String name = pros.getProperty("name");
        String password = pros.getProperty("password");
        System.out.println("name:"+name+" password:"+password);
    }

    @Test
    public void test5() throws IllegalAccessException, InstantiationException {
        Class<Person> clazz = Person.class;
        /*
        newInstance():调用此方法，创建对应的运行时类的对象，内部调用了运行时类的空参构造器

        要想此方法正常创建运行时类的对象，要求：
        1.运行时类必须提供空参的构造器
        2.空参的构造器的访问权限通常设置为public

        在javabean中需要提供一个public的空参构造器，原因：
        1.便于通过反射，创建运行时类的对象
        2，便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器
         */
        Person person = clazz.newInstance();
        System.out.println(person);
    }

    @Test
    public void test6(){
        int num = new Random().nextInt(3);//0,1,2
        String classPath = "";
        switch (num){
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.lang.Object";
                break;
            case 2:
                classPath = "Person";
                break;

        }
        try {
            Object instance = getInstance(classPath);
            System.out.println(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /*
    创建一个指定类的对象
    classpath：指定类的全类名
    */
    public Object getInstance(String classPath) throws Exception {
        Class<?> aClass = Class.forName(classPath);
        return aClass.newInstance();
    }

    @Test
    public void test7(){
        Class<Person> clazz = Person.class;

        //获取属性结构
        //getField():获取当前运行时类及其父类中声明为public访问权限的属性
        Field[] fields = clazz.getFields();
        for (Field f:fields){
            System.out.println(f);
        }

        //getDeclareFields（）：获取当前运行时类中声明的所有属性（不包含父类中声明的属性）
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            //1.权限修饰符
            int modifiers = f.getModifiers();
            System.out.print(Modifier.toString(modifiers));
            //2.数据类型
            Class<?> type = f.getType();
            System.out.print(type);
            //3.变量名
            String name = f.getName();
            System.out.print(name);

            System.out.println();
        }
    }
}

