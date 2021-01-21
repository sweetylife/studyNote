import java.util.Arrays;

public class UtilStudy {
    public static void main(String[] args) {
        //boolean Arrays.equals()
        int arr1[]={1,2,3,4};
        int arr2[]={1,2,3,4,5};
        boolean equal=Arrays.equals(arr1, arr2);
        System.out.println(equal);

        //String Arrays.toString()
        System.out.println(Arrays.toString(arr1));

        //void Arrays.fill()
        Arrays.fill(arr1, 10);
        System.out.println(Arrays.toString(arr1));

        // void Arrays.sort()
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        // int Arrays.binarySearch()
        int arr3[]={1,2,3,4,5};
        int get=Arrays.binarySearch(arr3,4);
        int noget=Arrays.binarySearch(arr3, 17);
        System.out.println(get+" "+noget);
    }
}
