import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {
    public static void main(String[] args) {
        // 1.提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);   //ExecutorService是接口
        // AbstractExecutorService实现接口

        // 2.管理
        ThreadPoolExecutor service1 = (ThreadPoolExecutor)service;  //ThreadPoolExecutor继承于  AbstractExecutorService  实现了ExecutorService
        service1.setCorePoolSize(15);

        // 3.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new NumberThread());// 适用于Runnable
        Future future = service.submit(new NumberThread2());// 适用于Callable，Runnable

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("main--------main1"); 
        try {
            System.out.println(future.get());//await到结果再继续后面的
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        
        // 4.关闭线程池
        service.shutdown();

    }
}

class NumberThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0)
                System.out.println(Thread.currentThread().getName() + "----" + i);
        }
    }
}
class NumberThread2 implements Callable {
    @Override
    public Object call() throws Exception {
        int sum=0;
        for (int i = 0; i < 20; i++) {
            Thread.sleep(200);
            if (i % 2 != 0){
                sum+=i;
                System.out.println(Thread.currentThread().getName() + "----" + i);
            }
        }
        return sum;
    }
}