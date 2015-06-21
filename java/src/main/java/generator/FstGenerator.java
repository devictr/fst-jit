package generator;

import util.State;

public class FstGenerator {

    private StringBuffer strBuff;

    public FstGenerator() {
    }

    public StringBuffer compute(State initState, String className) {
        strBuff = new StringBuffer();
        System.out.println("[FstGenerator] Transforming Fst to a java class");
        append("package generated;");

        append("public class " + className + " {");    

        appendWithTab("public static float compute(int[] token) {", 1);

        appendWithTab("int pos=0;", 2);
        appendWithTab("float result=0f;", 2);

        generateCases(initState, 2);

        appendWithTab("}", 1);

        append("}");
        System.out.println("[FstGenerator] Successfully transformed fst to " + className + ".java");
        return strBuff;
    }


    private void generateCases(State currentState, int tab) {
       
        if( currentState.getNumArcs() > 0) { 
            generateTokenLengthTest(tab);
            appendWithTab("switch(token[pos++]) {", tab);
            for (int i = 0; i < currentState.getNumArcs(); i++) {
                appendWithTab("case " + currentState.getArc(i).getIlabel()  + ":", tab+1); 
                if (currentState.getArc(i).getOlabel() != 0) {
                    appendWithTab("result+=" + currentState.getArc(i).getOlabel() + "f;", tab+2);
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
