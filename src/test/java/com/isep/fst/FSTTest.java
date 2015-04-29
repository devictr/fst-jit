package com.isep.fst;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


/**
 * Created by Victor Delépine on 28/04/15.
 */
public class FSTTest {

    public static final int INITIAL_STATE = 2;
    public static final int MATRIX_INITIAL_STATE1 = 1;
    public static final List<Integer> FINAL_STATE = Arrays.asList(1, 4, 3, 2);
    public static final List<Integer> OUTPUTS = Arrays.asList(7, 8, 9);
    public static final int NUMBER_OF_STATE_MATRIX = 7;

    @Rule
    public ExpectedException willBeThrown = ExpectedException.none();

    private FST subject;

    @Before
    public void init() {
        MutableTransitionMatrix transitionMatrix = new MutableTransitionMatrix(MATRIX_INITIAL_STATE1);
        for (int i = 1; i < NUMBER_OF_STATE_MATRIX; i++)
            transitionMatrix.addState();
        this.subject = new FST(INITIAL_STATE, transitionMatrix, FINAL_STATE, OUTPUTS);
    }

    @Test
    public void getNumberOfStates() {
        assertEquals(NUMBER_OF_STATE_MATRIX, subject.getNumberOfStates());
    }

    @Test
    public void getStates() {
        int[] expected = new int[NUMBER_OF_STATE_MATRIX];
        for (int i = 0; i < NUMBER_OF_STATE_MATRIX; i++)
            expected[i] = i + 1;
        assertArrayEquals(expected, subject.getStates());
    }

    @Test
    public void getInitialState() {
        assertEquals(INITIAL_STATE, subject.getInitialState());
    }

    @Test
    public void isFinalStateShouldThrowFSTException() throws FSTException {
        willBeThrown.expect(FSTException.class);
        subject.isFinalState(NUMBER_OF_STATE_MATRIX + INITIAL_STATE + 1);
    }

    @Test
    public void getFinalStates() {
        List<Integer> expect = FINAL_STATE;
        Collections.sort(expect);
        assertThat(subject.getFinalStates(), is(expect));
    }

    @Test
    public void isFinalState() throws FSTException {
        int state = FINAL_STATE.get((int) (Math.random() * FINAL_STATE.size()));
        assertTrue(subject.isFinalState(state));

        List<Integer> notContainInFinalState = Lists.newArrayList();
        int[] states = subject.getStates();
        for (int st : states)
            if (!FINAL_STATE.contains(st))
                notContainInFinalState.add(st);

        if (notContainInFinalState.size() > 0) {
            state = notContainInFinalState.get((int) (Math.random() * notContainInFinalState.size()));
            assertFalse(subject.isFinalState(state));
        }
    }

    @Test
    public void getLetters() {
        assertThat(subject.getLetters().size(), is(0));

        MutableTransitionMatrix transitionMatrix = new MutableTransitionMatrix(MATRIX_INITIAL_STATE1);
        for (int i = 1; i < NUMBER_OF_STATE_MATRIX; i++)
            transitionMatrix.addState();
        transitionMatrix.addTransition(1, 'a', 3);
        transitionMatrix.addTransition(2, 'b', 1);
        transitionMatrix.addTransition(3, 'c', 2);
        this.subject = new FST(INITIAL_STATE, transitionMatrix, FINAL_STATE, OUTPUTS);

        assertThat(subject.getLetters().size(), is(3));
        assertThat(subject.getLetters(), is(Arrays.asList('a', 'b', 'c')));
    }

    @Test
    public void getTarget() {
        assertThat(subject.getTarget(1, 'a'), is(-1));

        MutableTransitionMatrix transitionMatrix = new MutableTransitionMatrix(MATRIX_INITIAL_STATE1);
        for (int i = 1; i < NUMBER_OF_STATE_MATRIX; i++)
            transitionMatrix.addState();
        transitionMatrix.addTransition(1, 'a', 3);
        transitionMatrix.addTransition(2, 'b', 1);
        transitionMatrix.addTransition(3, 'c', 2);
        this.subject = new FST(INITIAL_STATE, transitionMatrix, FINAL_STATE, OUTPUTS);

        assertThat(subject.getTarget(1, 'a'), is(3));
        assertThat(subject.getTarget(2, 'b'), is(1));
        assertThat(subject.getTarget(3, 'c'), is(2));
    }

    @Test
    public void getoutputsShouldThrowFSTException() throws FSTException {
        willBeThrown.expect(FSTException.class);
        subject.getOutputs(NUMBER_OF_STATE_MATRIX + INITIAL_STATE + 1);
    }
}
