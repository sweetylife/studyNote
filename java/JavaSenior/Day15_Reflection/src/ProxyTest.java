import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        //获取代理类对象
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(new SuperMan());
        String belief = proxyInstance.getBelief();
        System.out.println(belief);

        proxyInstance.eat("麻辣烫");
    }
}
interface Human{
    String getBelief();
    void eat(String food);
}
//被代理类
class SuperMan implements Human{

    @Override
    public String getBelief() {
        System.out.println("我相信");
        return "我相信";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃"+food);
    }
}
//获取代理类对象的类
class ProxyFactory{
    //调用此方法，返回一个代理类,调用该代理类的对象的方法时，会自动调用myInvocationHandler的invoke方法
    public static Object getProxyInstance(Object obj){
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        myInvocationHandler.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),myInvocationHandler);
    }
}
//代理类
class MyInvocationHandler implements InvocationHandler{
    //当通过代理类的对象，调用方法a时，就会自动的调用如下的方法：invoke()
    private Object obj;
    public void bind(Object obj){
        this.obj=obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理工厂的准备工作");
        Object invoke = method.invoke(obj, args);//调用被代理类的方法
        System.out.println("代理工厂的收尾工作");
        return invoke;
    }
}