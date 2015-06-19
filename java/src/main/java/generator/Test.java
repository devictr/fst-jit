package generator;

import generated.FstComputeTest1;
import generated.FstComputeTest2;
import generated.FstComputeTest3;

public class Test {

    public static void main (String[] args) {

        String strInput = "";

        if(args.length > 0) {
            strInput = args[0];
        } else {
            strInput = "MOTH";
        }

        final int size = strInput.length();
        int[] input = new int[size];

        for(int i = 0; i < size; i++ ) {
            input[i] = (int) strInput.charAt(i);
        }

        float result1 = FstComputeTest1.compute(input);
        float result2 = FstComputeTest2.compute(input);
        float result3 = FstComputeTest3.compute(input);

        System.out.println("test for word : " + strInput);
        System.out.println("test1 -> " + result1);
        System.out.println("test2 -> " + result2);
        System.out.println("test3 -> " + result3);
    }

}
