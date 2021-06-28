public class Order<T> {
    String orderName;
    int orderId;
//    类的内部结构就可以使用类的泛型
    T orderT;

    public Order() {
    }
    public Order(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }
}

class SubOrder extends Order<String>{
    //由于子类在继承带泛型的父类时，已经指明了泛型类型，则实例化子类对象时，不再需要指明泛型
    //SubOrder不再是泛型类
}
class SubOrder2<T> extends Order<T>{
    //子类中没有指明泛型类，SubOrder2仍然是泛型类
}