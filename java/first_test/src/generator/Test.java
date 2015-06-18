package generator;

class Test {

    public static void main (String[] args) {
        String strInput = "TOP";

        final int size = strInput.length();
        int[] input = new int[size];

        for(int i = 0; i < size; i++ ) {
            input[i] = (int) strInput.charAt(i);
        }

        float result = FstComputeSwitch.compute(input);
        System.out.println(result);
    }

}
