import java.util.Arrays;
import java.util.Collections;

public class StringStudy {
    public static void main(String[] args) {
        int[] ar = new int[4];
        System.out.println(ar); // [I@279f2327

        int[][] arr = new int[4][3];
        System.out.println(arr); // [[I@279f2327
        System.out.println(arr[0]); // [I@2ff4acd0
        System.out.println(arr[0][0]); // 0

        int[][] arr2 = new int[4][];
        System.out.println(arr2); // [[I@54bedef2
        System.out.println(arr2[0]); // null
        // System.out.println(arr2[0][0]); //报错

        int[][] arr1 = new int[][] { { 1, 2, 3 }, { 4, 5 }, { 6, 7, 8 } };
        System.out.println(arr1); // [[I@27716f4
        System.out.println(arr1[0]); // [I@8efb846
        System.out.println(arr1[0][0]); // 1

        // int[][] arr3=new int[10][];
        // for(int i=0;i<arr3.length;i++){
        // arr3[i]=new int[(i+1)];
        // for(int j=0;j<arr3[i].length;j++){
        // if(j==0||j==arr3[i].length-1){
        // arr3[i][j]=1;
        // }else{
        // arr3[i][j]=arr3[i-1][j-1] +arr3[i-1][j];
        // }
        // System.out.print(arr3[i][j]+" ");
        // }
        // System.out.println();
        // }

        System.out.println("----------------------------------");
        

        // long startTime = System.currentTimeMillis(); //获取开始时间
        // int[] arr5 = new int[50000];
        // for (int i = 0; i < arr5.length; i++) {
        //     arr5[i] = i + 1;
        // }
        // int[] arr6 = new int[50000];
        // for(int i=0;i<arr6.length;i++){
        //     reverse(arr5);
        //     int a=(int)(Math.random()*(arr5.length-i));
        //     arr6[i]=arr5[a];
        //     arr5[a]=0;
        // }
        // System.out.println(Arrays.toString(arr6));

        // int[] arr4=new int[50000];
        // label:for(int i=0;i<arr4.length;i++){
        //     int a=(int)(Math.random()*arr4.length+1);
        //     for(int j=0;j<arr4.length;j++){
        //         if(arr4[j]==a){
        //             i--;
        //             continue label;
        //         }
        //     }
        //     arr4[i]=a;
        // }
        // System.out.println(Arrays.toString(arr4));
        // long endTime = System.currentTimeMillis(); //获取结束时间
        // System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间


        
    
    }

    public static void reverse(int[] arr) {
        Arrays.sort(arr);
        for (int min = 0, max = arr.length - 1; min < max; min++, max--) {
            // 对数组的元素进行位置交换
            int temp = arr[min]; // 定义了一个什么都没有的变量 保存下标为min的元素 然后min就空了
            arr[min] = arr[max];
            arr[max] = temp;
        }
    }
}
