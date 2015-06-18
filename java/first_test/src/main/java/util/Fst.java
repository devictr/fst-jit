package util; /**
 * 
 * Copyright 1999-2012 Carnegie Mellon University.  
 * Portions Copyright 2002 Sun Microsystems, Inc.  
 * Portions Copyright 2002 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 * 
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL 
 * WARRANTIES.
 *
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A mutable finite state transducer implementation.
 *
 * Holds an ArrayList of {@link State} objects allowing
 * additions/deletions.
 * 
 * @author John Salatas
 */
public class Fst {

    // fst states
    private ArrayList<State> states = null;

    // initial state
    protected State start;

    // input symbols map
    protected String[] isyms;

    // output symbols map
    protected String[] osyms;

    /**
     * Default Constructor
     */
    public Fst() {
        states = new ArrayList<State>();
    }

    /**
     * Constructor specifying the initial capacity of the states ArrayList (this
     * is an optimization used in various operations)
     * 
     * @param numStates
     *            the initial capacity
     */
    public Fst(int numStates) {
        if (numStates > 0) {
            states = new ArrayList<State>(numStates);
        }
    }


    /**
     * Get the initial states
     * @return the initial state
     */
    public State getStart() {
        return start;
    }

    /**
     * Set the initial state
     * 
     * @param start
     *            the initial state
     */
    public void setStart(State start) {
        this.start = start;
    }

    /**
     * Get the number of states in the fst
     * @return number of states
     */
    public int getNumStates() {
        return this.states.size();
    }

    public State getState(int index) {
        return states.get(index);
    }

    /**
     * Adds a state to the fst
     * 
     * @param state
     *            the state to be added
     */
    public void addState(State state) {
        this.states.add(state);
        state.id = states.size() - 1;
    }

    /**
     * Get the input symbols' array
     * @return array of input symbols
     */
    public String[] getIsyms() {
        return isyms;
    }

    /**
     * Set the input symbols
     * 
     * @param isyms
     *            the isyms to set
     */
    public void setIsyms(String[] isyms) {
        this.isyms = isyms;
    }

    /**
     * Get the output symbols' array
     * @return array fo output symbols
     */
    public String[] getOsyms() {
        return osyms;
    }

    /**
     * Set the output symbols
     * 
     * @param osyms
     *            the osyms to set
     */
    public void setOsyms(String[] osyms) {
        this.osyms = osyms;
    }


    /**
     * Deletes a state
     * 
     * @param state
     *            the state to delete
     */
    public void deleteState(State state) {

        if (state == start) {
            System.err.println("Cannot delete start state.");
            return;
        }

        states.remove(state);

        for (State s1 : states) {
            ArrayList<Arc> newArcs = new ArrayList<Arc>();
            for (int j = 0; j < s1.getNumArcs(); j++) {
                Arc a = s1.getArc(j);
                if (!a.getNextState().equals(state)) {
                    newArcs.add(a);
                }
            }
            s1.setArcs(newArcs);
        }
    }

    /**
     * Remaps the states' ids.
     * 
     * States' ids are renumbered starting from 0 up to @see
     *
     */
    public void remapStateIds() {
        int numStates = states.size();
        for (int i = 0; i < numStates; i++) {
            states.get(i).id = i;
        }

    }

    public void deleteStates(HashSet<State> toDelete) {

        if (toDelete.contains(start)) {
            System.err.println("Cannot delete start state.");
            return;
        }

        ArrayList<State> newStates = new ArrayList<State>();

        for (State s1 : states) {
            if (!toDelete.contains(s1)) {
                newStates.add(s1);
                ArrayList<Arc> newArcs = new ArrayList<Arc>();
                for (int j = 0; j < s1.getNumArcs(); j++) {
                    Arc a = s1.getArc(j);
                    if (!toDelete.contains(a.getNextState())) {
                        newArcs.add(a);
                    }
                }
                s1.setArcs(newArcs);
            }
        }
        states = newStates;

        remapStateIds();
    }

}