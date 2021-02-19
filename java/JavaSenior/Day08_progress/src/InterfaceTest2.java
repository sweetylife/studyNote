
public class InterfaceTest2 {
    
}
interface A2{
    public void run();
}
class B1 implements A2{

    private A2 target;

    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }

    public B1(A2 a){
        this.target=a;
    }
}

class B2 implements A2{

    @Override
    public void run() {
        System.out.println("B1");
    }

}