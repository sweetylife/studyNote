import java.io.Console;

public class BlockTest {
    public static void main(String[] args) {
        System.out.println(Persons.desc);//staticBlock Chinese

        Persons p1=new Persons();//unStaticBlock
        System.out.println(p1.name);
    }
}
class Persons{
    String name="yyy";
    int age;
    static String desc="Chinese";
    public Persons(){
        System.out.println("constructor");
    }
    public Persons(String name,int age){
        this.name=name;
        this.age=age;
    }
    public void eat(){
        System.out.println("吃饭");
    }
    public String toString(){
        return "Person [name="+ name+", age"+age+"]";
    }
    static{
        System.out.println("staticBlock");
    }
    {
        name="tiantian";
        System.out.println("unStaticBlock");
    }
}
