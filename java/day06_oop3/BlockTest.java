public class BlockTest {
    public static void main(String[] args) {
        System.out.println(Persons.desc);//staticBlock Chinese
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
    static{
        desc="修改";
    }
}
