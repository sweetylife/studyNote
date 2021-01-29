public class Test {
    public static void main(String[] args) {
        Test test=new Test();
        test.func(new Men());//走很多路 吃很多饭
        test.func(new Student());//走很少路  吃很少饭
    }
    public void func(Person person){
        person.walk();
        person.eat();
    }
}
