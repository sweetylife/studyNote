public class ComparableCircleTest {
    public static void main(String[] args) {
        System.out.println(new ComparableCircle(2.2).compareTo(new ComparableCircle(2.2)));
        System.out.println(new ComparableCircle(2.2).compareTo(new String("AA")));
    }
}

interface CompareObject {
    int compareTo(Object o);
}

class Circle {
    private Double radius = 0.0;

    public Circle(Double radius) {
        this.radius = radius;
    }

    public Double getRadius() {
        return this.radius;
    }
}

class ComparableCircle extends Circle implements CompareObject {

    public ComparableCircle(Double radius) {
        super(radius);
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        } else if (o instanceof ComparableCircle) {
            ComparableCircle p = (ComparableCircle) o;
            return p.getRadius().compareTo(this.getRadius());
        }else{
            throw new RuntimeException("输入不正确");
        }
    }

}