public class InnerClassTest1 {
    
    // 少见
    public void method(){
        // 局部内部类
        class AA{

        }
    }

    // 常见
    // 返回一个实现了Comparable接口的类
    public Comparable getComparable(){
        class MyComparable implements Comparable{

            @Override
            public int compareTo(Object o) {
                return 0;
            }

        }
        return new MyComparable();
    }
}
