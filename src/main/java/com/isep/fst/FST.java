package com.isep.fst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Victor Delépine on 27/04/15.
 */
public class FST {

    private int initialState;
    private MutableTransitionMatrix transitionMatrix;
    private List<? extends Comparable> finalStates;
    private List<? extends Comparable> outputs;

    public FST(int initialState, MutableTransitionMatrix transitionMatrix,
               List<? extends Comparable> finalStates,
               List<? extends Comparable> outputs) {
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

    public List<? extends Comparable> getFinalStates() {
        Collections.sort(finalStates);
        return finalStates;
    }

    public boolean isFinalState(int state) throws FSTException {
        if (!transitionMatrix.hasState(state)) {
            throw new FSTException("Unknown state : " + state);
        }
        return finalStates.contains(state);
    }

    public ArrayList<Character> getLetters() {
        return transitionMatrix.getLetters();
    }

    public int getTarget(int source, char letter) {
        return transitionMatrix.getTarget(source, letter);
    }

    public Object getOutputs(int state) throws FSTException {
        if (!transitionMatrix.hasState(state)) {
            throw new FSTException("Unknown state : " + state);
        }
        return null;
    }
}
