package builder;

import util.Arc;
import util.Fst;
import util.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Minimal Acyclic Subsequential Transducer builder class
 * Created by devict on 18/06/15.
 */
public class MastBuilder {

    private Fst fst;

    public MastBuilder() {
        fst = new Fst();
    }

    private int getMaxWordLength(List<InputOutputPair> pairs) {
        int max = 0;
        for (InputOutputPair s : pairs) {
            if (s.getInput().length() > max) max = s.getInput().length();
        }
        return max;
    }

    private void setTransition(State current, char ch, State target) {
        Arc arc = new Arc();
        arc.setNextState(target);
        arc.setIlabel(ch);
        current.addArc(arc);
    }

    public Fst buildMast(List<InputOutputPair> pairs) {

        int maxWordLength = getMaxWordLength(pairs);
        int prefixLengthPlusOne, j;
        State[] tempStates = new State[maxWordLength];
        for (int i = 0; i < maxWordLength; i++) {
            tempStates[i] = new State();
        }
        String previousWord = "";
        String currentWord = "";
        int currentOutput;

        /*
        Loop for the words in the input list
         */
        for (InputOutputPair pair : pairs) {
            currentWord = pair.getInput();
            currentOutput = pair.getOutput();
            /*
            The following loop calculate the length of the longest common prefix of currentWord and previousWord
             */
            int i = 1;
            while ((i < currentWord.length()) && (i < previousWord.length()) && (previousWord.charAt(i) == currentWord.charAt(i))) {
                i++;
            }
            prefixLengthPlusOne = i;
            /*
            We minimize the states from the suffix of the previous word
             */
            for (i = previousWord.length() - 1; i >= prefixLengthPlusOne; i--) {
                setTransition(tempStates[i - 1], previousWord.charAt(i), findMinimized(tempStates[i]));
            }
            /*
            This loop initializes the tail states for the current word
             */
            for (i = prefixLengthPlusOne; i < currentWord.length(); i++) {
                clearState(tempStates[i]);
                setTransition(tempStates[i - 1], currentWord.charAt(i), tempStates[i]);
            }

            if (currentWord != previousWord) {
                tempStates[currentWord.length() - 1].setFinalState();
                //setOutput(tempStates[currentWord.length()], "")
            }

            for (j = 1; j < prefixLengthPlusOne; j++) {
                if (tempStates[j - 1].getArcForTarget(currentWord.charAt(j - 1)) != null) {
                    if (tempStates[j - 1].getArcForTarget(currentWord.charAt(j - 1)).getOlabel() == currentOutput) {
                        currentOutput = 0;
                        break;
                    }
                    int outSuff;
                    outSuff = tempStates[j - 1].getArcForTarget(currentWord.charAt(j - 1)).getOlabel();
                    tempStates[j - 1].getArcForTarget(currentWord.charAt(j - 1)).setOlabel(0);
                    for (char c : currentWord.toCharArray()) {
                        if (tempStates[j].getArcForTarget((int) c) != null) {
                            setOutput(tempStates[j], c, outSuff);
                        }
                    }
                }



                /*commonPrefix = intersect(getOutput(tempStates[j - 1], currentWord.charAt(j)), currentOutput);
                wordSuffix = getOutput(tempStates[j - 1], currentWord.charAt(j)).replace(commonPrefix, "");
                setOutput(tempStates[j - 1], currentWord.charAt(j), commonPrefix);

                for (char c : currentWord.toCharArray()) {
                    if (tempStates[j].getArcForTarget((int) c) != null) {
                        setOutput(tempStates[j], c, wordSuffix + getOutput(tempStates[j], c));
                    }
                }

                if (tempStates[j].isFinalState()) {
                    Set<String> tempSet = new HashSet<String>();
                    // bloqué ici (cf. ligne 58 pseudocode)
                }*/
            }
            if (currentWord != previousWord) {
                setOutput(tempStates[prefixLengthPlusOne - 1], currentWord.charAt(prefixLengthPlusOne - 1), currentOutput);
            }
            previousWord = currentWord;

        }
        for (int i = currentWord.length() - 1; i >= 1; i--) {
            setTransition(tempStates[i - 1], previousWord.charAt(i), findMinimized(tempStates[i]));

        }
        fst.setStart(tempStates[0]);
        fst.setStates(new ArrayList<State>(Arrays.asList(tempStates)));
        return fst;
    }

    private void setOutput(State state, char c, int output) {
        Arc arc = state.getArcForTarget((int) c);
        if (arc != null) {
            arc.setOlabel(output);
        }
    }

    private int getOutput(State state, char c) {
        return state.getArcForTarget((int) c).getOlabel();
    }

    private State findMinimized(State state) {
        if (!fst.getStates().contains(state)) {
            fst.addState(state);
        }
        return state;
    }

    private void clearState(State state) {
        state = new State();
    }

    private String intersect(String s1, String s2) {
        HashSet<Character> h1 = new HashSet<Character>(), h2 = new HashSet<Character>();
        for (int i = 0; i < s1.length(); i++) {
            h1.add(s1.charAt(i));
        }
        for (int i = 0; i < s2.length(); i++) {
            h2.add(s2.charAt(i));
        }
        h1.retainAll(h2);
        Character[] res = h1.toArray(new Character[0]);
        StringBuilder str = new StringBuilder();
        for (Character c : res)
            str.append(c.toString());
        return str.toString();
    }
}
