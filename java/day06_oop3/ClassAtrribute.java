import Person3.Head;

public class ClassAtrribute{
    public static void main(String[] args) {
       Persond persond= new Persond();
       persond.method();
    }
}
class Persond{
    public void method(){
        int num=10;
        class Head{
            public void show(){
                // num=11;//报错，不能修改
                System.out.println(num);
            }
        }
        Head head=new Head();
        head.show();
    }
    
}