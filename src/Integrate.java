import java.util.function.DoubleUnaryOperator;

/**
 * Created by Dmitrii_Miagkov on 1/26/2016.
 */
public class Integrate {

//    private static final double a;
//    private static final double b;
//
    public static double integrate(DoubleUnaryOperator f, double a, double b) {
        double step  = 1E-7;
        double result  = 0;
        for (int i = 0; (a + step*i) < b; i++){
            result += f.applyAsDouble(a + step * i) * step;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.printf(String.valueOf(integrate(x -> 1, 0, 10)));
    }
}
