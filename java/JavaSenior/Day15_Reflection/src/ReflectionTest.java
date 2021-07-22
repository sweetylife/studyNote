import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
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
    @Test
    public void test8(){
        Class<Person> clazz = Person.class;
        //getMethods（） 获取当前运行时类及其所有父类中声明为public的方法
        Method[] methods = clazz.getMethods();
        for (Method m :methods){
            System.out.println(m);
        }
        //getDeclareMethods（）获取当前运行时类中声明的所有方法。（不包含父类中声明的方法）
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m: declaredMethods) {
            //1.获取方法声明的注释
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a:annotations){
                System.out.println(a);
            }
            //2.权限修饰符
            System.out.println(Modifier.toString(m.getModifiers()));
            //3.返回值类型
            System.out.println(m.getReturnType().getName());
            //4.方法名
            System.out.println(m.getName());
            //5.形参列表
            Class<?>[] parameterTypes = m.getParameterTypes();
            if (!(parameterTypes == null || parameterTypes.length == 0)){
                for (int i=0;i<parameterTypes.length;i++){
                    if (i==parameterTypes.length-1){
                        System.out.println(parameterTypes[i].getName()+"args_"+i);
                        break;
                    }
                    System.out.println(parameterTypes[i].getName()+"args_"+i+",");
                }
            }
            //6.抛出的异常
            Class<?>[] exceptionTypes = m.getExceptionTypes();
            if(!(exceptionTypes==null||exceptionTypes.length==0)){
                System.out.print("throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    if (i==exceptionTypes.length-1){
                        System.out.println(exceptionTypes[i].getName());
                        break;
                    }
                    System.out.println(exceptionTypes[i].getName()+",");
                }
            }
        }
    }
    @Test
    public void test9(){
        Class<Person> clazz = Person.class;
        //getConstructors() 获取当前运行时类中声明为public的构造器
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor i:constructors){
            System.out.println(i);
        }
        //getDeclaredConstructors() 获取当前运行时类中声明的所有构造器
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor i:declaredConstructors){
            System.out.println(i);
        }
    }
    //获取父类
    @Test
    public void test10(){
        Class<Person> clazz = Person.class;
        Class<? super Person> superclass = clazz.getSuperclass();
        System.out.println(superclass);
    }
    //获取带泛型的父类
    @Test
    public void test11(){
        Class<Person> clazz = Person.class;
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);
    }
    //获取带泛型的父类的泛型
    @Test
    public void test12(){
        Class<Person> clazz = Person.class;
        Type genericSuperclass = clazz.getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        //获取泛型类型
        Type[] actualTypeArguments = paramType.getActualTypeArguments();
        System.out.println(((Class)actualTypeArguments[0]).getName());
    }

    //获取运行时类实现的接口
    @Test
    public void test13(){
        Class<Person> clazz = Person.class;
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class c : interfaces) {
            System.out.println(c);
        }
        //获取父类实现的接口
        Class<?>[] interfaces1 = clazz.getSuperclass().getInterfaces();
        for (Class c : interfaces1) {
            System.out.println(c);
        }
    }

    //如何操作运行时类的指定的属性
    @Test
    public void test14() throws Exception {
        Class<Person> clazz = Person.class;
        //创建运行时类的对象
        Person person = clazz.newInstance();

            //方式1：
            //获取指定的属性(要求运行时类中属性声明为public）
            //Field id = clazz.getField("id");

        //方式2：
        //获取指定的属性
        Field id = clazz.getDeclaredField("id");
        id.setAccessible(true);
        //设置当前属性的值
        //set（）
        id.set(person,1001);
        //获取指定属性的值
        Object o = id.get(person);
        System.out.println(o);
    }

    //如何操作运行时类中指定的方法
    @Test
    public void test15() throws Exception{
        Class<Person> clazz = Person.class;
        Person person = clazz.newInstance();
        //getDeclaredMethod()：参数1：指明获取的对象的名称，参数2：调取方法的形参列表
        Method show = clazz.getDeclaredMethod("showNation", String.class);
        show.setAccessible(true);
        Object invoke = show.invoke(person, "中国");
        System.out.println(invoke);

        //如何调用静态方法
        Method show1 = clazz.getDeclaredMethod("show");
        show1.setAccessible(true);
        Object invoke1 = show1.invoke(null);
        System.out.println(invoke1);
    }

    //如何调用运行时类中的构造器
    @Test
    public void test16() throws Exception{
        Class<Person> clazz = Person.class;
        //1.获取指定的构造器
        Constructor<Person> declaredConstructor = clazz.getDeclaredConstructor(String.class);
        //2.保证此构造器是可以访问的
        declaredConstructor.setAccessible(true);
        //3.调用构造器运行时类的对象
        Person tom = declaredConstructor.newInstance("Tom");
        System.out.println(tom);
    }

}

