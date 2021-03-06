package generator;

import util.State;
import java.util.List;
import java.util.ArrayList;

public class BigFstGenerator {

    private StringBuffer strBuff;

    public BigFstGenerator() {
    }

    public StringBuffer compute(State initState, String className) {
        strBuff = new StringBuffer();
        System.out.println("[FstGenerator] Transforming Fst to a java class");
        append("package generated;");

        append("public class " + className + " {");

        appendWithTab("public static float compute(int[] token) {", 1);

        appendWithTab("int pos=0;", 2);
        appendWithTab("float result=0f;", 2);
        appendWithTab("return state_" + initState.getId() + "(token, pos, result);", 2);

        appendWithTab("}", 1);

        List<State> doneStates = new ArrayList<>();
        generateCases(initState, doneStates);

        append("}");
        System.out.println("[FstGenerator] Successfully transformed fst to " + className + ".java");
        return strBuff;
    }


    private void generateCases(State currentState, List<State> doneStates) {
        List<State> nextStates = new ArrayList<>();

        append("\n\tprivate static float state_" + currentState.getId() +
                "(int[] token, int pos, float result) {");

        generateTokenLengthTest(2);
        appendWithTab("switch(token[pos++]) {", 2);
        for (int i = 0; i < currentState.getNumArcs(); i++) {
            appendWithTab("case " + currentState.getArc(i).getIlabel()  + ":", 3);

            if (currentState.getArc(i).getOlabel() != 0) {
                appendWithTab("result+=" + currentState.getArc(i).getOlabel() + "f;", 4);
            }

            if (currentState.getArc(i).getNextState().isFinalState()) {
                appendWithTab("if(pos==token.length) {return result;}", 4);
            }

            if( currentState.getArc(i).getNextState().getNumArcs() <= 0) {
                appendWithTab("return (pos!=token.length) ? -1 : result;", 4);
                continue;
            }

            appendWithTab("return state_" + currentState.getArc(i).getNextState().getId() +
                    "(token, pos, result);", 4);

            nextStates.add(currentState.getArc(i).getNextState());
        }
        appendWithTab("default:", 3);
        appendWithTab("return -1;", 4);
        appendWithTab("}", 2);
        appendWithTab("}", 1);

        for (State next: nextStates) {
            if (doneStates.contains(next)) {
                continue;
            }
            generateCases(next, doneStates);
            doneStates.add(next);
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
