import java.util.List;

class FstGeneratorSwitch {

    private List<State> fstStates;
    private StringBuffer strBuff;

    public FstGeneratorSwitch(List<State> fstStates) {
        this.fstStates = fstStates;
        strBuff = new StringBuffer();
    }

    public StringBuffer compute() {

        append("class FstComputSwitch {");    

        appendWithTab("public static float compute(int[] token) {", 1);

        generateCases(fstStates.get(0), 2);

        appendWithTab("}", 1);

        append("}");

        return strBuff;
    }


    private void generateCases(State currentState, int tab) {

    }

    private void append(String strToAppend) {
        strBuff.append(strToAppend);
        strBuff.append("\n");
    }

    private void appendWithTab(String strToAppend, int numberOfTab) {
        for (int i = 0; i < numberOfTab; i++) {
            strBuff.append("\t");
        }
        strBuff.append(strToAppend);
        strBuff.append("\n");
    }

}
