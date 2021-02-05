public class FinalTest {
    final int FIRST = 0;// 显式赋值，每个对象的属性值都一样
    final int SECOND;
    {
        SECOND=2;//每个对象的属性值都一样，但是赋值需要调用多行方法
    }

    final int THIRD;
    public FinalTest(){
        THIRD=1;
    }
    public FinalTest(int n){//每个对象的属性值不一样
        THIRD=n;
    }
}
