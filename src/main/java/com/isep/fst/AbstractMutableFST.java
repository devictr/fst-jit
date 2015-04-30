package com.isep.fst;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Victor Delépine on 27/04/15.
 */
public abstract class AbstractMutableFST extends FST {


    public AbstractMutableFST(int initialState, MutableTransitionMatrix transitionMatrix) {
        super(initialState, transitionMatrix, new HashSet<>(), new HashMap<>());
    }

    public FST setFinalState(int state) throws FSTException {
        if (!this.transitionMatrix.hasState(state)) {
            throw new FSTException("Unknown state " + state);
        }
        this.finalStates.add(state);
        return this;
    }

    public FST addOutput(int state, String output) throws FSTException {
        if (!this.transitionMatrix.hasState(state)) {
            throw new FSTException("Unknown state " + state);
        }
        this.outputs.get(state).add(output);
        return this;
    }

    public FST addOutputs(int state, List<String> outputs) throws FSTException {
        for (String output : outputs) {
            this.addOutput(state, output);
        }
        return this;
    }

    public int addState() {
        return this.transitionMatrix.addState();
    }

    public FST addTransition(int source, Character letter, int target) throws FSTException {
        if (letter == null) {
            throw new FSTException("Epsilon transition not allowed : source state : " + source + ", target state : " + target);
        }
        if (!this.transitionMatrix.hasState(source)) {
            throw new FSTException("Unknown source state " + source);
        }
        if (!this.transitionMatrix.hasState(target)) {
            throw new FSTException("Unknown target state " + target);
        }
        this.transitionMatrix.addTransition(source, letter, target);
        return this;
    }
}
