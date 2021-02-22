public class ProductTest {
    public static void main(String[] args) {
        Goods goods = new Goods();
        Productor productor = new Productor(goods);
        Customer customer = new Customer(goods);
        productor.setName("生产者");
        customer.setName("消费者");
        productor.start();
        customer.start();
    }
}

// 消费者和生产者都要操作productCount，所以将该数据封装在一个类中
class Goods {
    private int productCount = 0;

    public synchronized void add() {
        if (productCount < 20) {
            productCount++;
            System.out.println(Thread.currentThread().getName() + ":商品增加" + productCount + "个产品");
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void sub() {
        if (productCount > 0) {
            System.out.println(Thread.currentThread().getName() + ":商品减少" + productCount + "个产品");
            productCount--;
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Productor extends Thread {// 生产者

    private Goods goods;

    public Productor(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        System.out.println(getName() + "开始生产....");
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                goods.add();
            }
        }
}
class Customer extends Thread{//消费者
    private Goods goods;
    public Customer(Goods goods){
        this.goods=goods;
    }
    @Override
    public void run() {
        System.out.println(getName() + "开始购买....");
        while (true) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            goods.sub();
        }
    }
}