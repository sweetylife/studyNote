class AbstractTest{
    public static void main(String[] args) {
        Person p=new Person(){
            public void eat(){
                System.out.println("吃东西");
            }
        };
    }
}
abstract class Person{
    public abstract void eat();
}