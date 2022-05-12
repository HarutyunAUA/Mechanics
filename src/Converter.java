import java.util.Arrays;

public class Converter {

    //Initial version
    public static Spring createSpring(int[] signals){
        validateInput(signals);
        StringBuilder expr=new StringBuilder("[");
        for (int signal : signals) {
            if (signal == 1)
                expr.append("[]");
        }
        expr.append("]");
        return SpringArray.equivalentSpring(expr.toString());
    }
    private static void validateInput(int[] signals){
        if(signals.length!=8)
            throw new IllegalArgumentException("The length should be 8");
        for (int signal : signals) {
            if (signal != 0 && signal != 1)
                throw new IllegalArgumentException("Signals should be either 0 or 1");
        }
    }
    public static double[] getOscillations(Spring spring){
        return spring.move(8,1,0, 5);
    }
   public static double[] getAmplitudes(double[] oscillations){
        double[] imag=new double[oscillations.length];
        Arrays.fill(imag, 0);
        FT.transform(oscillations,imag);
       return oscillations;
   }
}
