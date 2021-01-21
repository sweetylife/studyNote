public class OppTest{
    public static void main(String[] args) {
        Person p1=new Person();
        p1.name="Sweety";
        p1.run();
    }

}
class Person{
    String name;
    int age;
    Boolean isMale;

    public void eat(){
        System.out.println("eat");
    }
    public void run(){
        System.out.println("run");
    }
}