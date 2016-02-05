import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by Dmitrii_Miagkov on 10/12/2015.
 */
class mergeArrays1 {


    public static int[] mergeArrays(int[] a1, int[] a2) {

        int newArrayLength = a1.length + a2.length;
        int[] a = new int[newArrayLength];

        for (int i = 1; i <= newArrayLength; ++i) {

            int l = 1;
            int j = 1;

            if (a1[j] <= a2[l]) {
                a[i] = a1[j];
                j += 1;
            } else {
                a[i] = a2[l];
                l += 1;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        int[] a1 = new int[]{1, 3, 5, 7, 9};
        int[] a2 = new int[]{2, 4, 6, 8, 10};
        a = mergeArrays(a1, a2);
        System.out.println(a);

    }
}
