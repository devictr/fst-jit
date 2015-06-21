package builder;

import util.Arc;
import util.Fst;
import util.State;

import java.util.*;

/**
 * Minimal Acyclic Subsequential Transducer builder class
 * Created by devict on 18/06/15.
 */
public class MastBuilder {

    private Fst fst;
    private Map<State, List<State>> stateDict;

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
        Arc arcForTarget = current.getArcForTarget((int) ch);
        if (arcForTarget != null) {
            return;
        }
        Arc arc = new Arc();
        arc.setNextState(target);
        arc.setIlabel(ch);
        current.addArc(arc);
    }

    public Fst buildMast(List<InputOutputPair> pairs) {

        int maxWordLength = getMaxWordLength(pairs);
        int prefixLength, j;
        stateDict = new HashMap<>();
        State[] tempStates = new State[maxWordLength + 1];
        for (int i = 0; i < maxWordLength + 1; i++) {
            tempStates[i] = new State();
        }
        String previousWord = "";
        String currentWord;
        int currentOutput;
        double counter = 0.;
        int percentage;
        System.out.println("Starting to build FST using the MAST algorithm");
        /*
        Loop for the words in the input list
         */
        for (InputOutputPair pair : pairs) {
            if (counter % 100000 == 0) {
                percentage = (int) ((counter / pairs.size()) * 100);
                System.out.println("[MastBuilder] FST Build status : " + percentage + "%");
            }
            currentWord = pair.getInput();
            currentOutput = pair.getOutput();

            int i = 0;
            while ((i < currentWord.length()) && (i < previousWord.length()) && (previousWord.charAt(i) == currentWord.charAt(i))) {
                i++;
            }
            prefixLength = i;

            for (i = previousWord.length(); i > prefixLength; i--) {
                State newState = null;
                if (stateDict.containsKey(tempStates[i])) {
                    for (State state : stateDict.get(tempStates[i])) {
                        if (state.equals(tempStates[i])) {
                            newState = state;
                            break;
                        }
                    }
                }
                if (newState == null) {
                    newState = tempStates[i];
                    fst.addState(newState);
                    if (stateDict.containsKey(newState)) {
                        stateDict.get(newState).add(newState);
                    } else {
                        List<State> stateList = new ArrayList<>();
                        stateList.add(newState);
                        stateDict.put(newState, stateList);
                    }
                }
                tempStates[i] = new State();
                setTransition(tempStates[i - 1], previousWord.charAt(i - 1), newState);
            }
            for (i = prefixLength + 1; i <= currentWord.length(); i++) {
                setTransition(tempStates[i - 1], currentWord.charAt(i - 1), tempStates[i]);
            }
            if (!Objects.equals(currentWord, previousWord)) {
                tempStates[currentWord.length()].setFinalState();
            }
            for (int k = 1; k < prefixLength + 1; k++) {
                if (tempStates[k - 1].getOutputForInput(currentWord.charAt(k - 1)) == currentOutput) {
                    currentOutput = 0;
                    break;
                }
                int outSuff = tempStates[k - 1].getOutputForInput(currentWord.charAt(k - 1));
                tempStates[k - 1].removeOutputForInput(currentWord.charAt(k - 1));
                for (char c : tempStates[k].getTransitionsInputs()) {
                    setOutput(tempStates[k], c, outSuff);
                }

            }
            if (!Objects.equals(currentWord, previousWord)) {
                setOutput(tempStates[prefixLength], currentWord.charAt(prefixLength), currentOutput);
            }
            previousWord = currentWord;
            counter++;
        }

        for (int i = previousWord.length(); i > 0; i--) {
            State newState = null;
            if (stateDict.containsKey(tempStates[i])) {
                for (State state : stateDict.get(tempStates[i])) {
                    if (state.equals(tempStates[i])) {
                        newState = state;
                        break;
                    }
                }
            }
            if (newState == null) {
                newState = tempStates[i];
                tempStates[i] = new State();
                fst.addState(newState);
                if (stateDict.containsKey(newState)) {
                    stateDict.get(newState).add(newState);
                } else {
                    List<State> stateList = new ArrayList<State>();
                    stateList.add(newState);
                    stateDict.put(newState, stateList);
                }
            }
            setTransition(tempStates[i - 1], previousWord.charAt(i - 1), newState);
        }
        fst.setStart(tempStates[0]);
        fst.addState(tempStates[0]);
        System.out.println("[MastBuilder] FST Build status : 100%");
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
