import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    public static List<Employee> getEmployees(){
        List<Employee> list  = new ArrayList<>();
        list.add(new Employee(1001,"tom",34,600.38));
        list.add(new Employee(1002,"tom1",35,601.38));
        list.add(new Employee(1003,"tom2",36,602.38));
        list.add(new Employee(1004,"tom3",37,603.38));
        list.add(new Employee(1001,"tom",34,600.38));
        return list;
    }
}
