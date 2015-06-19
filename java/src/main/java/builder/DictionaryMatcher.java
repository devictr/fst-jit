package builder;

import util.Arc;
import util.Convert;
import util.Fst;
import util.State;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by devict on 18/06/15.
 */
public class DictionaryMatcher {


    public static void main(String... args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/devict/Software/FST/fst-jit/java/first_test/src/main/resources/wordlist")));
        String s;
        Fst fst = new Fst();
        State start = new State(0f);
        start.setId(0);
        fst.addState(start);
        fst.setStart(start);
        while ((s = in.readLine()) != null && s.length() != 0) {
            State currentState = fst.getStart();

            for (char ch : s.toCharArray()) {
                State targetState = currentState.getTarget((int) ch);
                if (targetState == null) {
                    targetState = new State();
                    fst.addState(targetState);
                    Arc arc = new Arc();
                    arc.setNextState(targetState);
                    arc.setIlabel((int) ch);
                    arc.setOlabel(0);
                    currentState.addArc(arc);

                }
                currentState = targetState;
            }
            currentState.setFinalState();
        }
        Convert.export(fst, "test");
        Convert.exportFstToDot(fst, "test.dot");
    }
}
