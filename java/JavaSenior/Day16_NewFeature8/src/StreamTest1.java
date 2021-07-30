import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest1 {
    //1.匹配与查找
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
    //2.归约
    @Test
    public void test2(){
        //reduce(T identity,BinaryOperation)可以将流中元素反复结合起来，得到一个只。返回T
        //练习：求1-10的自然数的和
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = integers.stream().reduce(100, Integer::sum);
        System.out.println(reduce);
        //练习2：计算员工的总工资
        List<Employee> employees = EmployeeData.getEmployees();
        //有初始值
        Stream<Double> doubleStream = employees.stream().map(Employee::getSalary);
        Double reduce1 = doubleStream.reduce(1000.0, Double::sum);
        System.out.println(reduce1);
        //无初始值
        Stream<Double> doubleStream2 = employees.stream().map(Employee::getSalary);
        Optional<Double> reduce2 = doubleStream2.reduce(Double::sum);
        System.out.println(reduce2);
        //自定义
        Stream<Double> doubleStream3 = employees.stream().map(Employee::getSalary);
        Optional<Double> reduce3 = doubleStream3.reduce((e1, e2) -> e1 + e2);
        System.out.println(reduce3);
    }

    //3.收集
    @Test
    public void test7(){
        // collect(Collector c) 将流转换为其他形式。接受一个Collector接口的实现，用于给Stream中元素做汇总
        //练习：查找工资大于600的员工
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> collect = employees.stream().filter(e -> e.getSalary() > 600).collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println();
        Set<Employee> collect1 = employees.stream().filter(e -> e.getSalary() > 600).collect(Collectors.toSet());
        collect1.forEach(System.out::println);
    }
}
