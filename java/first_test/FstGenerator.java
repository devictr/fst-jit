import java.util.List;

class FstGenerator {

    private StringBuffer strBuff;
    private List<State> fstStates;

    public FstGenerator(List<State> fstStates) {
        this.fstStates = fstStates;
        this.strBuff = new StringBuffer();
    }

    public StringBuffer compute() {
        append("class FstCompute {");
        emptyLine(); 
        generateMain();
        emptyLine();

        for ( State state : fstStates ) {
            generateStateCase(state);
        }

        append("}");

        return strBuff;
    }

    private void generateStateCase(State state) {
        
    }

    private void append(String strToAppend) {
        strBuff.append(strToAppend);
        strBuff.append("\n");
    }

    private void generateMain() {
        appendWithTab("public static int compute(int[] token) {", 1);

        appendWithTab("return node_0(token, 0, 0);", 2);

        appendWithTab("}", 1);
    }

    private void appendWithTab(String strToAppend, int numberOfTab) {
        for (int i = 0; i < numberOfTab; i++) {
            strBuff.append("\t");
        }
        strBuff.append(strToAppend);
        strBuff.append("\n");
    }

    private void emptyLine() {
        strBuff.append("\n");
    }

}
