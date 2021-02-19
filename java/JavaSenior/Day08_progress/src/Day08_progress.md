# 程序、进程、线程

## 1. 基本概念：程序、进程、线程

### 1.1程序

是为了完成特定任务，用某种语言编写的一组指令的合计，即一段静态的代码，静态对象。

### 1.2进程

是程序的一次执行过程，或是正在运行的一个程序，是一个动态的过程，有它自身的产生、存在和消亡的过程-----生命周期。

* 进程作为资源分配的单位，系统在运行时会为每个进程分配不同的内存区域。

### 1.3线程

进程可进一步细化为线程，是一个程序内部的一条执行路径。

* 若一个进程同一时间并行执行多个线程，就是支持多线程的，
* 线程作为调度和执行的单位，每个线程拥有独立的运行栈和程序计数器，线程切换的开销小
* 一个进程中的多个线程共享 堆和方法区

### 1.4 单核cpu和多核cpu的理解

* 一个java应用程序java.exe，其实至少有三个线程：main()主线程，gc()垃圾回收线程，异常处理线程

### 1.5并行与并发

* 并行：多个cpu同事执行多个任务，多个人同时做不同的事
* 并发：一个cpu（采用时间片）同时执行多个任务。比如：秒杀

### 1.6使用多线程的优点

* 提高应用的响应。对图形化界面更有意义，课增强用户的体验
* 提高计算机的系统cpu的利用率
* 改善程序结构，将既长又复杂的进程分为多个线程，独立运行，利于理解和修改

## 2. 线程的创建和使用

### 2.1创建方式1

``` java
public class ThreadTest {
    public static void main(String[] args) {
        // 3.创建Thread子类的对象
        MyThread myThread = new MyThread();
        // 4.通过此对象调用start()
        myThread.start();

        System.out.println("hello");
    }
}

// 1.创建一个继承Thread的子类
class MyThread extends Thread {
    // 2.重写Thread类的run()方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName()+":"+ i);
            }
        }
    }
}
```

### 2.2创建方式2

``` java
// 创建Thread类的匿名子类的方式
new Thread(){
    public void run(){
        // todo1
    }
}.start();

new Thread(){
    public void run(){
        // todo2
    }
}.start();
```

### 2.3注意

* 如果直接执行myThread.run()，不会报错，但是仍然在主线程中执行，不会在另一个线程中执行
* Thread.currentThread().getName()获取当前线程的名称
* myThread.start()不能调用两次，即线程不能启动两次。需要再new一个线程去start

### 2.4 Thread的常用方法

* start()启动当前线程，调用当前线程的run()
* run()通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中
* currentThread()静态方法，返回执行当前代码的线程
* getName()获取当前线程的名字
* setName()设置当前线程的名字
* yield() 释放当前cpu的执行权（在run中使用this.yield()）
* join() 在线程a中调用线程b的join()，此时线程a就进入阻塞状态，直到线程b执行完成后，线程a才结束阻塞状态，等待cpu的分配
![图 1](../../../../images/d526196cb439a80c705e5661d7cd0db2f57fad08576e6005a32af6a78a53f5f4.png)  

* sleep(long millitime) 让当前线程阻塞指定的时间，结束后等待cpu分配资源后继续执行
* isAlive() 判断该线程是否还存活

``` java
public class ThreadTest {
    public static void main(String[] args) {
        Thread.currentThread().setName("主线程");//修改main线程的名称
        System.out.println(Thread.currentThread().getName());//输出 主线程
        
        // 修改线程名称方法1
        MyThread myThread = new MyThread();
        myThread.setName("线程一");//修改myThread线程的名称
        myThread.start();

        // 修改线程名称方法2
        MyThread myThread = new MyThread2("线程二");
        myThread.start();

        for(int i=0;i<100;i++){
            if(i==20){
                try{
                    myThread.join();//等待myThread进程执行完再继续执行
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                
            }
        }
        System.out.println(myThread.isAlive());

    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"执行");
        this.yield();
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"执行");

        try{
            sleep(millis:1000);
        }catch(InterruptedException){
            e.printStackTrace();
        }
    }
    public MyThread2(String name){
        super(name);
    }
}
```

### 2.5线程的调度

* 调度策略
  * 时间片
  ![图 2](../../../../images/8a92fbe4768005007c1db5b9ec8799160ef34b8ac32958d2135e9c86ed79b0f5.png)  
  * 抢占式：高优先级的线程抢占cpu
* 调度方法
  * 同优先级线程组成先进先出队列，使用时间片策略
  * 对高优先级，使用优先调度的抢占式策略
* 线程的优先级
  * MAX_PRIORITY；10
  * MIN_PRIORITY：1
  * NORM_PRIORITY：5
* 涉及的方法
  * getPriority() 获取线程的优先级
  * setPriority(int p) 设置线程的优先级
  说明：高优先级的线程要抢占低优先级线程cpu的执行权，但是只是从概率上讲，高优先级的线程高概率的情况下被执行，并不意味着只有当高优先级的线程执行完以后，低优先级的线程才执行。

### 2.6 买票模拟

```java
public class TicketsTest {
    public static void main(String[] args) {
        Window window1 = new Window();
        Window window2 = new Window();
        Window window3 = new Window();
        window1.setName("窗口1");
        window2.setName("窗口2");
        window3.setName("窗口3");
        window1.start();
        window2.start();
        window3.start();
    }
}

class Window extends Thread {
    private static int ticket = 100;

    @Override
    public void run() {
        while (ticket > 0) {
            System.out.println(getName() + ":票号为：" + ticket);
            ticket--;
        }
    }
}
```

### 2.7多线程的创建3

```java
public class ThreadTest1 {
    public static void main(String[] args) {
        // 3.创建实现类的对象
        MThread mThread=new MThread();
        // 4.将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
        Thread thread = new Thread(mThread);
        // 5.通过Thread的对象调用start方法
        thread.start();

        Thread thread2 = new Thread(mThread);
        thread2.setName("线程2");
        thread2.start();
    }
}

// 1.创建一个实现了Runnable接口的类
class MThread implements Runnable{

    // 2.实现类去实现Runnable中的抽象方法：run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
           System.out.println(i);
        }

    }
}
```

### 3.8买票模拟2

```java
public class TicketsTest2 {
    public static void main(String[] args) {
        Window1 w = new Window1();//因为只new了一个Window1，所以仍然是共用同一个ticket
        Thread t1=new Thread(w);
        Thread t2=new Thread(w);
        Thread t3=new Thread(w);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}
class Window1 implements Runnable{
    private int ticket=100;
    @Override
    public void run() {
    while(ticket>0){
            System.out.println(Thread.currentThread().getName()+":票号 "+ticket);
            ticket--;
        }
    }
}
```

### 2.9 买票模拟的两种方式比较

* 开发中：优先选择：实现Runnable接口的方式
* 原因：
  1. 实现的方式没有类的单继承性的局限性
  2. 实现的方式更适合来处理多个线程有共享数据的情况。
* 联系：public class Thread implements Runnable
* 相同点：两种方式都需要重写run方法，将线程要执行逻辑声明在run方法中

### 2.10 难点

```java

interface A{
    public void run();
}

class B1 implements A{

    private A target;

    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }

    public B1(A a){
        this.target=a;
    }
}

class B2 implements A{

    @Override
    public void run() {
        System.out.println("B2");
    }

}
```

## 3. 线程的生命周期

## 4. 线程的同步

## 5. 先后才能的通信

## 6.jdk 5.0新增线程创建方式
