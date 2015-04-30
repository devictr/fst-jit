package com.isep.fst;

import java.util.*;

/**
 * Created by Victor Delépine on 27/04/15.
 */
public class FST {

    private int initialState;
    protected MutableTransitionMatrix transitionMatrix;
    protected Set<? extends Comparable> finalStates;
    protected Map<? extends Comparable, Set<String>> outputs;

    public FST(int initialState, MutableTransitionMatrix transitionMatrix,
               Set<? extends Comparable> finalStates,
               Map<? extends Comparable, Set<String>> outputs) {
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

    public Set<? extends Comparable> getFinalStates() {
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
