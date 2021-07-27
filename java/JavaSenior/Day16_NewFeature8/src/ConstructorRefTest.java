import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructorRefTest {
    @Test
    public void test1(){
        Supplier<Person> per = ()->new Person();
        per.get();
        System.out.println("--------------------------------");
        Supplier<Person> per1 = Person::new;
        per1.get();
    }
    @Test
    public void test2(){
        Function<Integer,Person> per = Person::new;
        Person apply = per.apply(12);
        System.out.println(apply);
    }
    @Test
    public void test3(){
        BiFunction<String,Integer,Person> per = Person::new;
        Person tom = per.apply("tom", 12);
        System.out.println(tom);
    }
    @Test
    public void test4(){
        Function<Integer,String[]> func1 = length -> new String[length];
        func1.apply(12);
        System.out.println("---------------------------");
        Function<Integer,String[]> func2 = String[]::new;
        func2.apply(12);
    }
}
