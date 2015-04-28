package com.isep.fst;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by devict on 28/04/15.
 */
public class MutableTransitionMatrixTest {

    private MutableTransitionMatrix transitionMatrix;

    @Before
    public void setUp() {
        transitionMatrix = new MutableTransitionMatrix(0);
    }

    @Test
    public void testAddStateHasStatesGetStates() {
        assertEquals(transitionMatrix.getNumberOfStates(), 1);
        assertTrue(transitionMatrix.hasState(0));
        assertFalse(transitionMatrix.hasState(1));

        int newState = transitionMatrix.addState();

        assertEquals(transitionMatrix.getNumberOfStates(), 2);
        assertTrue(transitionMatrix.hasState(0));
        assertTrue(transitionMatrix.hasState(newState));
        assertTrue(transitionMatrix.hasState(0));
        assertTrue(transitionMatrix.hasState(1));

        assertArrayEquals(transitionMatrix.getStates(), new int[]{0, 1});
    }

    @Test
    public void testAddTransition() {
        int initialState = 0;
        assertEquals(transitionMatrix.getNumberOfStates(), 1);
        assertEquals(transitionMatrix.getNumberOfTransitions(), 0);

        int newState = transitionMatrix.addState();
        assertEquals(transitionMatrix.getNumberOfStates(), 2);

        /*
        Transition s0 --- a --- s1
         */
        transitionMatrix.addTransition(initialState, 'a', newState);
        assertEquals(transitionMatrix.getNumberOfStates(), 2);
        assertEquals(transitionMatrix.getNumberOfTransitions(), 1);
        assertEquals(transitionMatrix.getTarget(initialState, 'a'), newState);

        /*
        Transition s0 --- b --- s0
         */
        transitionMatrix.addTransition(initialState, 'b', initialState);
        assertEquals(transitionMatrix.getNumberOfStates(), 2);
        assertEquals(transitionMatrix.getNumberOfTransitions(), 2);
        assertEquals(transitionMatrix.getTarget(initialState, 'a'), newState);
        assertEquals(transitionMatrix.getTarget(initialState, 'b'), initialState);

        /*
        Transition s1 --- a --- s1
         */
        transitionMatrix.addTransition(newState, 'a', newState);
        assertEquals(transitionMatrix.getNumberOfStates(), 2);
        assertEquals(transitionMatrix.getNumberOfTransitions(), 3);
        assertEquals(transitionMatrix.getTarget(initialState, 'a'), newState);
        assertEquals(transitionMatrix.getTarget(initialState, 'b'), initialState);
        assertEquals(transitionMatrix.getTarget(newState, 'a'), newState);

        /*
        Transition s1 --- b --- s1
         */
        transitionMatrix.addTransition(newState, 'b', newState);
        assertEquals(transitionMatrix.getNumberOfStates(), 2);
        assertEquals(transitionMatrix.getNumberOfTransitions(), 4);
        assertEquals(transitionMatrix.getTarget(initialState, 'a'), newState);
        assertEquals(transitionMatrix.getTarget(initialState, 'b'), initialState);
        assertEquals(transitionMatrix.getTarget(newState, 'a'), newState);
        assertEquals(transitionMatrix.getTarget(newState, 'b'), newState);
    }

    @Test
    public void testGetTargetNull() {
        int intialState = 0;
        assertEquals(transitionMatrix.getTarget(0, 'a'), -1);
    }

}
