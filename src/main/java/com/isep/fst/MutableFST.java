package com.isep.fst;

/**
 * Created by Victor Delépine on 27/04/15.
 */
public class MutableFST extends AbstractMutableFST{

    public MutableFST() {
        super(0, new MutableTransitionMatrix(0));
    }
}
