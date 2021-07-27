import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPITest {
    //创建Stream方式一：通过集合
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();
        //stream（）:返回一个顺序流
        Stream<Employee> stream = employees.stream();
        //parallelStream()返回一个并行流
        Stream<Employee> employeeStream = employees.parallelStream();
    }
    //创建Stream方式二：通过数组
    @Test
    public void test2(){
        //Arrays.stream(T[] array):返回一个流
        int[] arr = new int[]{1,2,3,4,5};
        IntStream stream = Arrays.stream(arr);

        Employee tom = new Employee(1001, "Tom", 12, 122);
        Employee Jerry = new Employee(1001, "Jerry", 12, 122);
        Employee[] employees = {tom, Jerry};
        Stream<Employee> stream1 = Arrays.stream(employees);
    }
    //创建Stream方式三：Stream.of();
    @Test
    public void test3(){
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
    }
    //创建Stream方式四：创建无限流
    @Test
    public void test4(){
        //迭代
        //遍历前10个偶数
        Stream.iterate(0,t->t+2).limit(10).forEach(System.out::println);

        //生成
        //Stream.generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
    //筛选与切片
    @Test
    public void test5(){
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Employee> stream = employees.stream();
        //过滤
        stream.filter(e->e.getSalary()>601).forEach(System.out::println);
        System.out.println();

        //截断
        employees.stream().limit(3).forEach(System.out::println);
        System.out.println();

        //跳过数据
        employees.stream().skip(2).forEach(System.out::println);
        System.out.println();

        //筛选:通过流所生成元素的hashCode和equals去除重复元素
        employees.stream().distinct().forEach(System.out::println);
    }
    //映射
    @Test
    public void test6(){
        //map(Function f) 接受一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素
        List<String> list = Arrays.asList("aa", "bb", "cc");
        list.stream().map(str->str.toUpperCase()).forEach(System.out::println);

        //练习1：获取员工姓名长度大于3的员工
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<String> nameStream = employees.stream().map(Employee::getName);
        nameStream.filter(e->e.length()>3).forEach(System.out::println);

        //练习2：
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest::fromStringToStream);
        streamStream.forEach(s->s.forEach(System.out::println));
        //flatMap(Function f)接受一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest::fromStringToStream);
        characterStream.forEach(System.out::println);
    }
    //将字符串中的多个字符构成的集合转换为对应的Stream的实例
    public static Stream<Character> fromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for (Character c:str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }
    //排序（排序的类需要实现Comparable接口或者定制排序）
    @Test
    public void test7(){
        List<Integer> integers = Arrays.asList( 34, 56,12, 78, 90);
        integers.stream().sorted().forEach(System.out::println);

        //定制排序
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((e1,e2)->Integer.compare(e1.getAge(),e2.getAge())).forEach(System.out::println);
    }
}
