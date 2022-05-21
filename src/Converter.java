import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Converter {


    //Assuming we have only springs with stiffness 1
    public static Spring createSpringSystem(Integer[] signals){
        validateInput(signals);
        ArrayList<Spring> springs=new ArrayList<>();
        StringBuilder expr=new StringBuilder("[");
        for (int i=0; i<signals.length; i++) {
            if (signals[i] == 1){
                Spring currentSpring=createSpring((int)(Math.pow(2,7-i)));
                springs.add(currentSpring);
                expr.append("[]");
            }
        }
        expr.append("]");
        Spring[] result=new Spring[springs.size()];
        Spring currentStiffSpring=SpringArray.equivalentSpring(expr.toString(),springs.toArray(result));
        return squareStiffness(currentStiffSpring);
    }
    public static Spring createSpring(int stiff){
        StringBuilder expr=new StringBuilder("[");
        expr.append("[]".repeat(Math.max(0, stiff)));
        expr.append("]");
        return SpringArray.equivalentSpring(expr.toString());
    }
    public static Spring squareStiffness(Spring spring){
        int stiff=(int)spring.getK();
        StringBuilder expr=new StringBuilder("[");
        expr.append("[]".repeat(Math.max(0, stiff)));
        expr.append("]");
        Spring[] stiffnessArray=new Spring[stiff];
        Arrays.fill(stiffnessArray, new Spring(stiff));
        return SpringArray.equivalentSpring(expr.toString(), stiffnessArray);
    }
    private static void validateInput(Integer[] signals){
        if(signals.length!=8)
            throw new IllegalArgumentException("The length should be 8");
        for (int signal : signals) {
            if (signal != 0 && signal != 1)
                throw new IllegalArgumentException("Signals should be either 0 or 1");
        }
    }
    public static double[] getOscillations(Spring spring){
        //Values for t and v0 are obtained after brute testing with other numbers
        return spring.move(256,1,0, spring.getK());
    }
   public static double[] getAmplitudes(double[] oscillations){
        double[] imag=new double[oscillations.length];
        Arrays.fill(imag, 0);
        FT.transform(oscillations,imag);
       return oscillations;
   }

    public static int getFrequency(double[] oscillations){
        HashMap<Integer, Integer> map=new HashMap<>();
        double[] frequencies = Converter.getAmplitudes(oscillations);
        for(double d : frequencies){
            int num=(int)Math.ceil(Math.abs(d));
            if(map.containsKey(num)){
                map.put(num, map.get(num)+1);
            }
            else
                map.put(num, 1);
        }
        return findMostFrequent(map);
    }

    public static int findMostFrequent(HashMap<Integer, Integer> map){
        int maxKey=0;
        int maxValue=0;
        for(int key : map.keySet()){
            if(map.get(key)>maxValue) {
                maxKey=key;
                maxValue = map.get(key);
            }
        }
        return maxKey;
    }

    public static int findDecimalValue(Integer[] binaryRep){
        Spring currentSpring=Converter.createSpringSystem(binaryRep);
        double[] oscillations = Converter.getOscillations(currentSpring);
        return Converter.getFrequency(oscillations);
    }
}
