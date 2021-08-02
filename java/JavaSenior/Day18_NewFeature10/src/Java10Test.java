import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Java10Test {
    @Test
    public void test1(){
        //1.声明变量时，根据所附的值，推断变量的类型
        var num=10;
        var integers = new ArrayList<Integer>();
        integers.add(123);

        //2.遍历操作
        for (var i : integers) {
            System.out.println(i);
        }
        //3.普通的遍历操作
        for (var i=0;i<100;i++){
            System.out.println(i);
        }
    }
    @Test
    public void test2(){
        //示例1
        var list1 = List.of("java", "python", "C");
        var copy1 = List.copyOf(list1);
        System.out.println(copy1==list1);//true

        //示例2
        var list2 = new ArrayList<>();
        var copy2 = List.copyOf(list2);
        System.out.println(copy2==list2);//false
    }
}
