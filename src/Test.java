import java.util.Arrays;

/**
 * Created by Dmitrii_Miagkov on 1/19/2016.
 */
public class Test {
    private static int[] mergeArrays(int[] a1, int[] a2) {
        int newArrayLength = a1.length + a2.length;
        int[] a = new int[newArrayLength];
        int l = 0;
        int j = 0;
        for (int i = 0; i <= (newArrayLength - 1); i++) {

            if (a1[j] <= a2[l]) {
                a[i] = a1[j];
                if (j < (a1.length-1)) {j = j + 1;}
                else {
                    a1[j]=a2[(a2.length-1)];
                }
            } else {
                a[i] = a2[l];
                if (l< (a2.length -1)) {l = l + 1;}
                else {
                    a2[l]=a1[(a1.length-1)];
                }
            }
        }
        return a;
    }
     public static void main(String[] args){
         int[] a = new int[10];
         int[] a1 = new int[]{-1, 3, 5, 7, 9};
         int[] a2 = new int[]{-10, -9};
         a = mergeArrays(a1, a2);
         System.out.println(Arrays.toString(a));

     }
}
