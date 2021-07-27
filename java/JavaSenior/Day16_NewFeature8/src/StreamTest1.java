import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamTest1 {
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();
        //allMatch(Predicate p) 检查是都匹配所有的元素
        //练习：是否所有的员工的年龄都大于18
        boolean allMatch = employees.stream().allMatch(employee -> employee.getAge() > 18);
        System.out.println(allMatch);
        //anyMatch(Predicate p) 检查是否有一个满足的元素
        boolean b = employees.stream().anyMatch(employee -> employee.getSalary() > 602);
        System.out.println(b);
        //noneMatch(Predicate p) 检查是否没有匹配的元素
        boolean t = employees.stream().noneMatch(employee -> employee.getName().startsWith("s"));
        System.out.println(t);
        //findFirst() 返回第一个元素
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first);
        //findAny() 返回任意一个
        Optional<Employee> any = employees.parallelStream().findAny();
        System.out.println(any);
        //count() 求个数
        long count = employees.stream().filter(employee -> employee.getSalary() > 600).count();
        System.out.println(count);
        //max(Comparator c)返回流中的最大值
        //返回最高的工资
        Stream<Double> doubleStream = employees.stream().map(Employee::getSalary);
        Optional<Double> max = doubleStream.max(Double::compare);
        System.out.println(max);
        //min(Comparator c)返回流中的最小值
        //练习：返回最低工资的员工
        Optional<Employee> min = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(min);
        //forEach(Consumer c)内部迭代
        employees.stream().forEach(System.out::println);
        //集合的遍历操作
        employees.forEach(System.out::println);
    }
}
