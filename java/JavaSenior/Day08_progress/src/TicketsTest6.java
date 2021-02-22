
public class TicketsTest6 {
    public static void main(String[] args) {
        Window6 t1 = new Window6();
        Window6 t2 = new Window6();
        Window6 t3 = new Window6();
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}

class Window6 extends Thread {
    private static int ticket = 100;
    @Override
    public void run() {
        while (true) {
            if(show()<=0){
                break;
            }
        }
    }
    private static synchronized int show() {//同步监视器：当前类Window6.class
    // private synchronized int show() { //同步监视器t1，t2，t3
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + ":票号 " + ticket);
            ticket--;
        }
        return ticket;
    }
}
