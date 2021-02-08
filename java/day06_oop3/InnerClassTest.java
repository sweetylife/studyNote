public class InnerClassTest {
    public static void main(String[] args) {

    }
}

class Person3 {

    public void eat(){
        System.out.println("人，吃饭");
    }
    // 成员内部类----静态
    static class Head{
        
    }
    // 成员内部类----非静态
    class Foot{
        public void walk(){
            // this 指的是Foot的对象
            System.out.println("走路");
            Person3.this.eat();//省略了Person.this.指的是 Person类的对象的this
        }
    }
    // 局部内部类----方法中
    public void method() {
        class AA {

        }
    }

    // 局部内部类----代码块中
    {
        class BB {

        }
    }

    // 局部内部类----构造器中
    public Person3() {
        class CC{

        }
    }
}