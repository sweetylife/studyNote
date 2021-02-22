public class InterReaction {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);
        t1.setName("01号窗口--");
        t2.setName("02号窗口--");
        t1.start();
        t2.start();
    }
}

class Number implements Runnable {

    private int num = 0;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();//唤醒wait
                if (num <= 100) {
                    System.out.println(Thread.currentThread().getName() + " " + num);
                    num++;

                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("wait结束");
                }else{
                    break;
                }
            }
        }
        
    }

}
