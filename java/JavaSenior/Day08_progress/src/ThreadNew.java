import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadNew {
    public static void main(String[] args) {
        // 3.创建callabel接口实现类的对象
        NumThread numThread = new NumThread();
        // 4.将此对象作为参数创建FutureTask对象
        FutureTask futureTask = new FutureTask(numThread);
        // 5.将FutureTask对象作为参数传递到Thread类中，并启动
        new Thread(futureTask).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("-------------main1");
        try {
            System.out.println("-------------main2");
            // 6.获取Callable中call方法的返回值
            // get()返回值即为FutureTask构造器参数callable实现类重写的call方法的返回值
            Object sum = futureTask.get();//相当于js中的await，会等待执行完后才执行后续代码
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("-------------3");//等待futureTask.get()的结果

    }
}

// 1.创建一个实现Callable的实现类
class NumThread implements Callable {
    // 2.实现call方法，将此线程需要执行的操作声明在call()中
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            Thread.sleep(100);
            System.out.println(i);
            sum += i;
        }
        return sum;
    }
}