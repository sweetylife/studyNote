import java.util.Vector;

public class Test {
    public static void main(String[] args) {
        Person p1=new Student();
        System.out.println(p1.count);
        p1.show();

        Vector vector=new Vector<>();
        vector.addElement(123);//123自动装箱为Integer+多态
        Object obj = vector.elementAt(0);
        // int score = (int) obj;//强转
        int score = (Integer) obj;//强转为Integer+自动拆箱
        System.out.println(score-100);
    }
}
