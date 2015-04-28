package com.isep.fst;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Victor Delépine on 27/04/15.
 */
public class FST {

    private int initialState;
    private MutableTransitionMatrix transitionMatrix;
    private ArrayList<Integer> finalStates;
    private ArrayList<Integer> outputs;

    public FST(int initialState, MutableTransitionMatrix transitionMatrix, ArrayList<Integer> finalStates, ArrayList<Integer> outputs) {

        this.initialState = initialState;
        this.transitionMatrix = transitionMatrix;
        this.finalStates = finalStates;
        this.outputs = outputs;
    }

    public int getNumberOfStates() {
        return transitionMatrix.getNumberOfStates();
    }

    public int[] getStates() {
        return transitionMatrix.getStates();
    }

    public int getInitialState() {
        return initialState;
    }

    public ArrayList<Integer> getFinalStates() {
        Collections.sort(finalStates);
        return finalStates;
    }

    public boolean isFinalState(int state) throws FSTException {
        if (transitionMatrix.hasState(state)) {
            throw new FSTException("Unknown state : " + state);
        }
        return finalStates.contains(state);
    }

    public ArrayList<Character> getLetters() {
        return transitionMatrix.getLetters();
    }

    public char getTarget(char source, char letter) {
        return 0;
    }

    public Object getOutputs(int state) {
        return null;
    }
}
