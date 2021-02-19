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
                System.out.println(i);
            }
        }
    }
}