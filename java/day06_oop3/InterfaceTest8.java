
public class InterfaceTest8 {
    public static void main(String[] args) {
        CompareA.method();//compareA--01
        SubClass.method();//subclass--01(这里不是重写，而是另外一个方法)
        SubClass s=new SubClass();
        s.method2();//subclass--02
        s.method3();//compareA--03
    }
}

interface CompareA{
    // 静态方法
    public static void method(){
        System.out.println("compareA--01");
    }
    // 默认方法 这里的default不能省略
    public default void method2(){
        System.out.println("compareA--02");
    }
    // 默认方法 public可以省略
    default void method3(){
        System.out.println("compareA--03");
    }

}

class SubClass implements CompareA{
    public static void method(){
        System.out.println("subclass--01");
    }
    public void method2(){
        System.out.println("subclass--02");
    }
}