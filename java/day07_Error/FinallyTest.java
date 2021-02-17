public class FinallyTest{
    @Test
    public void test(){
        int num=method();
        System.out.println(num);
    }
    public int method(){
        try{
            int[] arr = new int[10];
            System.out.println(arr[10]);
            return 1;
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            return 2;
        }finally{
            System.out.println("我一定会被执行");
        }
    }
}