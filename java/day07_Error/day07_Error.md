# 异常

## 1. 异常的体系结构

* Error：一般不编写针对性的代码进行处理
* Exception：可以进行异常的处理
  * 编译时异常（checked）
    * IOException
      * FileNotFoundException
  * 运行时异常（unchecked）
    * NullPointerException：空指针异常
    * ArrayIndexOutOfBoundsException：数组角标越界
    * ClassCastException：类型转换异常
    * NumberFormatException：数值类型异常
    * InputMismatchException：输入不匹配
    * ArithmeticException：算术异常

## 2. try-catch-finally

```java
public class ExceptionTest{
    public void main(String[] args){
        String str = "123";
        str = "abc";
        try{
            int num = Integer.parseInt(str);
        }catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }catch(NullPointerException e){
            e.printStackTrace();
        }catch(Exception e){
            //如果上面的异常匹配，这里不会执行
            // 如果大的异常在前，小的异常在后，会编译出错，因为小异常永远执行不到
            System.out.println("出错啦");
        }
    }
}
```

1. finally是可选的
2. 使用try将有可能出现异常的代码包裹起来，在执行过程中，一旦出现异常，就会生成一个对应异常类的对象，根据此对象的类型，去catch中进行匹配
3. 一旦try中的异常对象匹配到某一个catch时，就进入catch中进行异常的处理，一旦处理完成，就跳出当前的try-catch结构
4. catch中的异常类型如果没有子父关系，则谁声明在前都无所谓。
catch的异常类型如果有子父关系，则要求子类一定声明在父类前，否则报错。
5. 常用的异常对象处理的方式：① String getMessage() ② printStackTrace()
6. 在try结构中声明的变量，在出了try结构以后，就不能再调用
7. 有finally时，会先执行finally，最后执行return;

    * 执行结果：我一定会被执行 1

        ```java
        public class FinallyTest{
            @Test
            public void test(){
                int num=method();
                System.out.println(num);
            }
            public int method(){
                try{
                    int[] arr = new int[10];
                    return 1;
                }catch(ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                    return 2;
                }finally{
                    System.out.println("我一定会被执行");
                }
            }
        }
        ```

    * 执行结果：一堆错误  我一定会被执行 2

        ```java
        public class FinallyTest{
            @Test
            public void test(){
                int num=method();
                System.out.println(num);
            }
            public int method(){
                try{
                    int[] arr = new int[10];
                    System.out.println(arr[10]);
                    return 1;
                }catch(ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                    return 2;
                }finally{
                    System.out.println("我一定会被执行");
                }
            }
        }
        ```

    * 执行结果：一堆错误 我一定会被执行 3

        ```java
        public class FinallyTest{
            @Test
            public void test(){
                int num=method();
                System.out.println(num);
            }
            public int method(){
                try{
                    int[] arr = new int[10];
                    System.out.println(arr[10]);
                    return 1;
                }catch(ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                    return 2;
                }finally{
                    System.out.println("我一定会被执行");
                    return 3;
                }
            }
        }
        ```

8. finally的使用场景：数据库连接，输入输出流，网络编程Socket等资源，JVM是不能自动回收的，我们需要自己手动的进行资源释放，就需要声明在finally中。
9. try-catch-finally 可以嵌套。

* 体会1：使用try-catch-finally处理编译时异常，使得程序在编译时不再报错，但是运行时仍然可能报错。相当于使用try-catch-finally将一个编译时可能出现的异常，延迟到运行时出现，
* 体会2：开发中，由于运行时异常比较常见，通常不针对运行时异常(RuntimeException)编写try-catch-finally了，针对编译时异常(Exception)，一定要考虑异常的处理。

## 3. throws+异常类型

"throws + 异常类型" 写在方法的声明处，指明此方法执行时可能会抛出的异常类型或异常类型的父类，一旦当方法体执行时，出现异常，仍会在异常代码处生成一个异常类的对象，此对象满足throws后异常类型时，就会被抛出。异常代码后续的代码，就不再执行。

```java
public class ExceptionTest2{
    public static void main(String[] args){
        try{
            method2();
        }catch(IOException e){
            e.printSTackTrace();
        }
    }
    public static void method2() throws IOException{
        method1();
    }
    public static void method1() throws FileNotFoundException,IOException{
        File file = new File("hello.txt");
        FileInputStream fis = new FileInputStream("file");

        int data = fis.read();
        while(data != 1){
            System.out.print((char)data);
            data = fis.read();
        }
        fis.close();
    }
}
```

## 3.重写方法规则之一

==**子类重写的方法抛出的异常不大于父类被重写的方法抛出的异常类型**==

## 4.try-catch-finally 与 throws的选择

* 如果父类中被重写的方法没有throws方式处理异常，则子类重写的方法也不能使用throws，意味着如果子类重写的方法中有异常，必须使用try-catch-finally方式处理。
* 执行的方法a中，先后又调用了另外几个方法，这几个方法是递进关系执行的。我们建议这几个方法使用throws的方法进行处理。而执行的方法a可以考虑使用try-catch-finally方式进行处理。

## 5.手动的抛出异常

```java
class Student{
    private int id;
    public void register(int id){
        if(id > 0){
            this.id=id;
        }else{
            throw new RuntimeException("您输入的数据非法");
        }
    }
}
```

```java
class Student{
    private int id;
    public void register(int id) throws Exception{
        if(id > 0){
            this.id=id;
        }else{
            throw new Exception("您输入的数据非法");
        }
    }
}
```

## 6.用户自定义异常类

* 如何自定义异常类？
    1. 继承与现有的异常结构:RuntimeException,Exception
    2. 提供全局常量：serialVersionUID
    3. 提供重载构造器

```java
public class MyException extends RuntimeException{
    static final long serialVersionUID = -7034897190745766939L;
    public MyException(){
        
    }
    public MyException(String message){
        super(message);
    }
}
```
