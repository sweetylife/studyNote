public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account(0);
        User u1 = new User(account);
        User u2 = new User(account);
        u1.start();
        u2.start();
    }
}

class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public synchronized void deposit(int money) {//这里的监视器是this，因为两个user操作的是同一个account
        if (money > 0) {
            balance += money;
            System.out.println(Thread.currentThread().getName() + "存款成功，余额为" + balance);
        }
    }
}

class User extends Thread {
    private Account account;

    public User(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.deposit(1000);
        }
    }
}
