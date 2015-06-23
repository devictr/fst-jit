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
        generateMain();

        for ( State state : fstStates ) {
            generateStateCase(state);
        }

        append("}");

        return strBuff;
    }

    private void generateMain() {
        appendWithTab("public static float compute(int[] token) {", 1);

        appendWithTab("return node_0(token, 0, 0f);", 2);

        appendWithTab("}", 1);
    }

    private void generateStateCase(State state) {
        switch (state.getNumArcs()) {
            case 0:
                generateLastStateCase(state);
                break;
            case 1:
                generateStateWithOneArc(state);
                break;
            default:
                generateGeneralState(state);
                break;
        } 
    }

    private void generateLastStateCase(State state) {
        appendWithTab("private static float node_" + state.getId()
               + "(int[] token, int pos, float result) {", 1); 
        appendWithTab("return result;", 2);
        appendWithTab("}", 1);
    }

    private void generateStateWithOneArc(State state) {
        appendWithTab("private static float node_" + state.getId()
                + "(int[] token, int pos, float result) {", 1);

        appendWithTab("if(pos>=token.length || token[pos]!=" + 
                state.getArc(0).getIlabel() + ")",2);
        appendWithTab("return -1;", 3);
        appendWithTab("return node_" + state.getArc(0).getNextState().getId()
               + "(token, pos+1, result+" +
               state.getArc(0).getWeight() + "f);", 2);

        appendWithTab("}", 1);
    }

    private void generateGeneralState(State state) {
        appendWithTab("private static float node_" + state.getId()
               + "(int[] token, int pos, float result) {", 1); 
        appendWithTab("if(pos>=token.length) {return -1;}", 2);
        appendWithTab("switch(token[pos]) {", 2);
        for (int i = 0; i < state.getNumArcs(); i++) {
            appendWithTab("case " + state.getArc(i).getIlabel()  + ":", 3); 
        appendWithTab("return node_" + state.getArc(i).getNextState().getId()
               + "(token, pos+1, result+" +
               state.getArc(i).getWeight() + "f);", 4);
        }
        appendWithTab("default:", 3);
        appendWithTab("return -1;", 4);
        appendWithTab("}",2);
        appendWithTab("}", 1);
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
