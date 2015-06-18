import java.util.List;

class FstGeneratorSwitch {

    private StringBuffer strBuff;

    public FstGeneratorSwitch() {
    }

    public StringBuffer compute(State initState) {
        strBuff = new StringBuffer();

        append("class FstComputeSwitch {");    

        appendWithTab("public static float compute(int[] token) {", 1);

        appendWithTab("int pos=0;", 2);
        appendWithTab("float result=0f;", 2);

        generateCases(initState, 2);

        appendWithTab("}", 1);

        append("}");

        return strBuff;
    }


    private void generateCases(State currentState, int tab) {
       
        if( currentState.getNumArcs() > 0) { 
            generateTokenLengthTest(tab);
            appendWithTab("switch(token[pos++]) {", tab);
            for (int i = 0; i < currentState.getNumArcs(); i++) {
                appendWithTab("case " + currentState.getArc(i).getIlabel()  + ":", tab+1); 
                if (currentState.getArc(i).getWeight() != 0f) {
                    appendWithTab("result+=" + currentState.getArc(i).getWeight() + "f;", tab+2);
                }
                generateCases(currentState.getArc(i).getNextState(), tab+2);
            }
            appendWithTab("default:", tab+1);
            appendWithTab("return -1;", tab+2);
            appendWithTab("}", tab);
        } else {
            appendWithTab("return (pos!=token.length) ? -1 : result;", tab);
        }
    }

    private void generateTokenLengthTest(int tab) {
        appendWithTab("if(pos>=token.length) {return -1;}", tab);
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
