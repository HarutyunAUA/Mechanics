import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Testing {

    public static void check(){

        List<Integer[]> binaryInputs=new ArrayList<>();
        generateAllBinaryStrings(8, new Integer[8], 0, binaryInputs);
        System.out.printf("%-20s %-20s %-20s %-20s", "Binary Input", "Decimal Value", "Program Output", "Error");
        System.out.println();
        int numOfErrors=0;
        for(int i=1; i<256; i++){
            Integer[] binaryRep=binaryInputs.get(i);
            int result=Converter.findDecimalValue(binaryRep);

            int trueValue= getDecimalFromBinary(binaryRep);
            String binaryString=getBinaryString(binaryRep);
            if(trueValue-result!=0)
                numOfErrors++;
            System.out.printf("%-20s %-20d %-20d %-20d", binaryString, trueValue,result, Math.abs(result-trueValue));
            System.out.println();
        }

        double percent=(256-numOfErrors)/256.0*100;
        System.out.printf("Correct output: "+(256-numOfErrors)+"/256 (%.3f%%)", percent);
    }

    private static String getBinaryString(Integer[] binaryRep) {
        StringBuilder s=new StringBuilder();
        for(int i: binaryRep)
            s.append(i);
        return s.toString();
    }

    /**
     * Took the code from https://www.geeksforgeeks.org/generate-all-the-binary-strings-of-n-bits/
     * and modified for my case
     */

        // Function to generate all binary strings
       public static void generateAllBinaryStrings(int n, Integer arr[], int i, List<Integer[]> finalResult)
        {
            if (i == n)
            {
                finalResult.add(Arrays.copyOf(arr, arr.length));
                return;
            }
            // First assign "0" at ith position
            // and try for all other permutations
            // for remaining positions
            arr[i] = 0;
            generateAllBinaryStrings(n, arr, i + 1,finalResult);
            // And then assign "1" at ith position
            // and try for all other permutations
            // for remaining positions
            arr[i] = 1;
            generateAllBinaryStrings(n, arr, i + 1,finalResult);
        }

        public static int getDecimalFromBinary(Integer[] bits){
           int result=0;
           for(int i=bits.length-1; i>=0; i--){
               if(bits[i]==1)
                    result+=(int)Math.pow(2,bits.length-1-i);
           }
           return result;
        }
}
