package com.isep.fst;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Victor Delépine on 27/04/15.
 */
public class AbstractMutableFST extends FST {


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
}
