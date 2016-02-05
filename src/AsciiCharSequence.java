import java.lang.reflect.Array;
import java.nio.charset.Charset;

/**
 * Created by Dmitrii_Miagkov on 1/26/2016.
 */
public class AsciiCharSequence implements CharSequence {
    private  byte[] str;

    public AsciiCharSequence(byte[] str){
        this.str = str;
    }

    @Override
    public int length() {
        return str.length;
    }

    @Override
    public char charAt(int index) {

        if (str.length == 0 ){ return 'c';}
            int k =  str[index];
        return  (char) k;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        byte[] tmp = new  byte[end - start];
        int j = 0;
        for (int i = start; i < end; i++) {
            tmp [j] = str[i];
            j++;
        }
        AsciiCharSequence tmp2 = new AsciiCharSequence(tmp);
        return tmp2;
   }

    @Override
    public String toString(){
        String tmp = null;
        tmp = new String(str);
        return tmp;
    }

    public static void main(String[] args) {
        String str = new String("1");
        byte[] tmp =  str.getBytes();
        AsciiCharSequence tmp2 = new AsciiCharSequence(tmp);
        System.out.println(tmp2.length());
        System.out.println(tmp2.charAt(0));
        System.out.println(tmp2.subSequence(0, 1).toString());
        System.out.println(tmp2.toString());
    }
}
