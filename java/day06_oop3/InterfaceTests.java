public class InterfaceTests {
    
}
interface AA{
    void method1();
}
interface BB{
    void method2();
}
interface CC extends AA,BB{
    void method3();
}
class DD implements CC{

    @Override
    public void method1() {
        // TODO Auto-generated method stub

    }

    @Override
    public void method2() {
        // TODO Auto-generated method stub

    }

    @Override
    public void method3() {
        // TODO Auto-generated method stub

    }

}
