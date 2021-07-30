import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
    //Optional为避免空指针异常的类

    //Optional.of(T t)创建一个Optional实例，t必须非空，且不能是null
    //Optional.empty():创建一个空的Optional实例
    //Optional.ofNullable(T t):t可以是null
    @Test
    public void test1(){
        Girl girl = new Girl();
        //girl=null; Optional.of(girl);会报错
        Optional.of(girl);
    }
    @Test
    public void test2(){
        Girl girl = new Girl();
        girl = null;
        Optional<Girl> girl1 = Optional.ofNullable(girl);
        System.out.println(girl1);//不报错，返回Optional.empty
        //orElse(T t1) 如果girl1是Optional.empty就返回参数中的对象t1，如果不是Optional.empty就返回girl1
        Girl jerry = girl1.orElse(new Girl("jerry"));
        System.out.println(jerry);
    }

    //原先容易空指针异常的情况
    public String getGirlName(Girl girl){
        return  girl.getName();
    }

    //优化后避免空指针异常的情况
    public String getGirlName2(Girl girl){
        Optional<Girl> girl1 = Optional.ofNullable(girl);
        Girl jerry = girl1.orElse(new Girl("Jerry"));
        return jerry.getName();
    }
    @Test
    public void test3(){
//        getGirlName(null);
        String girlName2 = getGirlName2(null);
        System.out.println(girlName2);
        String tom = getGirlName2(new Girl("Tom"));
        System.out.println(tom);
    }
}
