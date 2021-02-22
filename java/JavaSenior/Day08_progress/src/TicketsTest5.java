import static java.lang.Thread.sleep;

public class TicketsTest5 {
    public static void main(String[] args) {
        Window5 w = new Window5();//因为只new了一个Window5，所以仍然是共用同一个ticket
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

class Window5 implements Runnable {
    private int ticket = 100;
    @Override
    public void run() {
        while (true) {
            int result=show();
            if(result==0){
                break;
            }
        }
    }
    private synchronized int show(){
        if (ticket > 0) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":票号 " + ticket);
            ticket--;
        }
        return ticket;
    }

}
