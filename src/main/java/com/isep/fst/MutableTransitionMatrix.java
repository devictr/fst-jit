package com.isep.fst;

import com.rits.cloning.Cloner;

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
        this.matrix = new HashMap<>();
        this.matrix.put(initialState, new HashMap<>());
    }

    public int addState() {
        matrix.put(++lastState, new HashMap<>());
        return lastState;
    }

    public MutableTransitionMatrix addTransition(int source, char letter, int target) {
        matrix.get(source).put(letter, target);
        return this;
    }

    /**
     *
     * @param source
     * @param letter
     * @return int target state if it exists. <br/> Otherwise -1
     */
    public int getTarget(int source, char letter) {
        HashMap<Character, Integer> successors = matrix.get(source);
        if (successors == null) {
            throw new RuntimeException("Unknown source state : " + source);
        }
        int target;
        try {
            target = successors.get(letter);
        } catch (NullPointerException e) {
            return -1;
        }
        return target;
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
        Set<Integer> integers = matrix.keySet();
        Integer[] states = integers.toArray(new Integer[integers.size()]);
        int[] unboxedStates = new int[states.length];
        for (int i = 0; i < states.length; i++) {
            unboxedStates[i] = states[i];
        }
        return unboxedStates;
    }

    public ArrayList<Character> getLetters() {
        return matrix.values().stream().flatMap(m -> m.keySet().stream()).collect(Collectors.toCollection(ArrayList<Character>::new));
    }

    public HashMap<Integer, HashMap<Character, Integer>> getMap() {
        Cloner cloner = new Cloner();
        return cloner.deepClone(matrix);
    }

}
