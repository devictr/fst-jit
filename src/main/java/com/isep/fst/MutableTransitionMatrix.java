package com.isep.fst;

import java.util.ArrayList;

/**
 * Created by Victor Delépine on 27/04/15.
 */
public class MutableTransitionMatrix {

    private ArrayList<Character> letters;

    public int getNumberOfStates() {
        return 0;
    }

    public int[] getStates() {
        return new int[0];
    }

    public boolean hasState(int state) {
        return false;
    }

    public ArrayList<Character> getLetters() {
        return this.letters;
    }

    public char getTarget(char source, char letter) {
        return 0;
    }
}
