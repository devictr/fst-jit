package com.isep.fst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Victor Delépine on 27/04/15.
 */
public class AbstractMutableFST extends FST {


    public AbstractMutableFST(int initialState, MutableTransitionMatrix transitionMatrix) {
        super(initialState, transitionMatrix, new HashSet<>(), new HashMap<>());
    }


}
