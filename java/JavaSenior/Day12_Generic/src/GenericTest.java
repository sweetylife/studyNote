import org.junit.jupiter.api.Test;

import java.util.List;

public class GenericTest {
    @Test
    public void test1(){
//如果定义了泛型类，实例化没有指明类的泛型，则认为此泛型类型为Object类型
//        要求：如果定义的类是带泛型的，建议在实例化时要指明类的泛型
        Order order = new Order();
        order.setOrderT(123);
        order.setOrderT("ABC");

        Order<String> stringOrder = new Order<>();
        stringOrder.setOrderT("123");
        stringOrder.setOrderT("abc");

    }
    @Test
    public void test2(){
        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;

        //可以赋值给list1的
        List<Student> list3 = null;
        List<Person> list4 = null;

        //可以赋值给list2的
        List<Person> list5 = null;
        List<Object> list6 = null;
    }
}