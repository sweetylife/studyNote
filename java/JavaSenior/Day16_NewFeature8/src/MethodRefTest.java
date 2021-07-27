import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class MethodRefTest {
    //情况1：对象::非静态方法
    //Consumer中的void accept(T t)
    //PrintStream 中的void println(T t)
    @Test
    public void test1(){
        Consumer<String> con = str -> System.out.println(str);
        con.accept("北京");
        System.out.println("---------------------------------------");
        Consumer<String> con1 = System.out::println;
        con1.accept("北京");
    }
    //情况2：类::静态方法
    @Test
    public void test2(){
        Comparator<Integer> com1 = (t1,t2)->Integer.compare(t1,t2);
        int compare = com1.compare(12, 21);
        System.out.println(compare);
        System.out.println("--------------------------------------");
        Comparator<Integer> com2 = Integer::compare;
        int compare1 = com2.compare(12, 21);//相当于Integer.compare(12, 21)
        System.out.println(compare1);
    }
    //情况3：类::非静态方法
    @Test
    public void test3(){
        Comparator<Integer> com1 = (t1,t2)->t1.compareTo(t2);
        int compare = com1.compare(12, 22);
        System.out.println(compare);

        System.out.println("--------------------------------------");

        Comparator<Integer> com2 = Integer::compareTo;
        int compare1 = com2.compare(12, 22);//相当于12.compareTo(22)
        System.out.println(compare1);

        System.out.println("-----------------------------------------");

        BiPredicate<String,String> pre = String::equals;
        boolean test = pre.test("abc", "abc");
        System.out.println(test);

        System.out.println("-------------------------------------------");

        Person tom = new Person("Tom", 12);
        Function<Person,String> pre1 = Person::getName;
        String apply = pre1.apply(tom);
        System.out.println(apply);
    }
}
