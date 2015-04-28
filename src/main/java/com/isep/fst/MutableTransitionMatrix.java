package com.isep.fst;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by devict on 27/04/15.
 */
public class MutableTransitionMatrix {

    private int lastState;
    private HashMap<Integer, HashMap<Character, Integer>> matrix;
    private ArrayList<Character> letters;

    public MutableTransitionMatrix(int initialState) {
        this.lastState = initialState;
        this.matrix = new HashMap<Integer, HashMap<Character, Integer>>();
    }

    public int addState() {
        int newState = lastState + 1;
        matrix.put(newState, new HashMap<Character, Integer>());
        return newState;
    }

    public MutableTransitionMatrix addTransition(int source, char letter, int target) {
        matrix.get(source).put(letter, target);
        return this;
    }

    public int getTarget(int source, char letter) {
        HashMap<Character, Integer> successors = matrix.get(source);
        if (successors == null) {
            throw new RuntimeException("Unknown source state : " + source);
        }
        return successors.get(letter);
    }

    public HashMap<Character, Integer> getTransitions(int source) {
        return matrix.get(source);
    }

    public int getNumberOfTransitions() {
        return matrix.values().stream().mapToInt(HashMap<Character,Integer>::size).sum(); // Java 8 FTW
    }

    public boolean hasState(int state) {
        return matrix.containsKey(state);
    }

    public int getNumberOfStates() {
        return matrix.keySet().size();
    }

    public int[] getStates() {
        Integer[] states = (Integer[]) matrix.keySet().toArray();
        int[] unboxedStates = new int[states.length];
        for (int i = 0; i < states.length; i++) {
            unboxedStates[i] = Integer.valueOf(states[i]);
        }
        return unboxedStates;
    }

    public ArrayList<Character> getLetters() {
        return matrix.values().stream().flatMap(m -> m.keySet().stream()).collect(Collectors.toCollection(ArrayList<Character>::new));
    }

}
