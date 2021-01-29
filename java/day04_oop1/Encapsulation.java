
public class Encapsulation{
    public static void main(String[] args) {
        Animal a=new Animal();
    }
}
class Animal{
    String name;
    int age;
    public Animal(){
        System.out.println("constructor");
    }
    public Animal(String name){
        this();
        this.name=name;
    }
    public Animal(String name,int age){
        this(name);
        this.age=age;
    }
}