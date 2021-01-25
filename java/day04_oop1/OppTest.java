import java.util.Arrays;

public class OppTest{
    public static void main(String[] args) {
        // Person p1=new Person();
        // p1.name="Sweety";
        // p1.run();
        OppTest test=new OppTest();
       test.show(1,2);
    }

    public double show(double m,double n){
        System.err.println("double");
        return m+n;
    }
    
    public int show(int m,int n){
        System.out.println("int");
        return m+n;
    }
}
