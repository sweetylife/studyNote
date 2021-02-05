public class InterfaceTest {
    public static void main(String[] args) {
        InterfaceTest test=new InterfaceTest();
        Flyable flyable= new Bullet();
        test.method(flyable);
    }
    public void method(Flyable flyable){
        flyable.fly();
    }
}
interface Flyable{
    // 全局常量
    public static final int MAX_SPEED=7900;
    int MIN_SPEED=1;//书写时public static final可以省略,但其实仍然存在

    // 抽象方法
    public abstract void fly();
    void stop();//public abstract 可以省略，但其实仍然存在
}
interface Attackable{
    void attack();
}
class Bullet implements Flyable,Attackable{

    @Override
    public void fly() {
        System.out.println("飞");
    }

    @Override
    public void stop() {
        System.out.println("停");
    }

    @Override
    public void attack() {
        System.out.println("攻击");
    }
}