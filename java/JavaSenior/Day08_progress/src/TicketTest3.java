import static java.lang.Thread.sleep;

public class TicketTest3 {
    public static void main(String[] args) {
        Window3 w = new Window3();//因为只new了一个Window1，所以仍然是共用同一个ticket
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}

class Window3 implements Runnable {
    private int ticket = 100;
    private Object obj = new Object();
    @Override
    public void run() {
        while (true) {
            synchronized (this){ // synchronized (obj){
                if (ticket > 0) {
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":票号 " + ticket);
                    ticket--;
                } else {
                    break;
                }
            }

        }
    }

}
