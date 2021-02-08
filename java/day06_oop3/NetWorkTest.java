public class NetWorkTest{
    public static void main(String[] args) {
       System.out.println(new BBB().x);
    }
}
interface AAA{
   int x=0;

   default void method(){
    System.out.println("interface--method");
   }
}
class BBB {
   public static int x=1;
   public void method(){
    System.out.println("superclass--method");
   }
}
class CCC extends BBB {
    public void method(){
        System.out.println("ccc--method");
    }
}