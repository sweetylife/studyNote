import java.util.Arrays;
// 快速排序

public class Sort {
    public static void main(String[] args) {
        // 快速排序
        int[] arr = { 5, 2, 9, 3,10, 8,2,4,20,18,14, 0, 1, 6, 7,0 };
        System.out.println(Arrays.toString(arr));
        // quick_sort(arr,0,arr.length-1);
        bubble_sort(arr);
        System.out.println(Arrays.toString(arr));

    }
    public static void quick_sort(int[] arr, int start, int end) {
        if(start<end){
            int flag = start;
            int low= start;
            int high = end;
            while (true) {
                while (low < end && arr[low] - arr[flag] <= 0){low++;};
                while (high > start && arr[high] - arr[flag] >= 0){high--;};
                if (low < high) {
                    int temp = arr[low];
                    arr[low] = arr[high];
                    arr[high] = temp;
                }else{
                    break;
                }
            }
                int tmp = arr[high];
                arr[high] = arr[flag];
                arr[flag] = tmp;
                quick_sort(arr, start, high-1); // 递归调用 
                quick_sort(arr, high+1, end);
        }
    }
    public static void bubble_sort(int[] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=1;j<arr.length-i;j++){
                if(arr[j]<arr[j-1]){
                    int temp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;
                }
            }
        }
    }
}
