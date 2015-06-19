package util;

/**
 * From sphinx4 project https://github.com/cmusphinx/sphinx4
 * <p>
 * Copyright 1999-2012 Carnegie Mellon University.
 * Portions Copyright 2002 Sun Microsystems, Inc.
 * Portions Copyright 2002 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 * <p>
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL
 * WARRANTIES.
 * <p>
 * original author John Salatas
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class State implements Comparable<State> {

    protected int id = -1;
    protected int initialNumArcs = -1;
    private float fnlWeight;
    private ArrayList<Arc> arcs = null;
    private boolean isFinalState = false;

    public State() {
        arcs = new ArrayList<Arc>();
    }

    public State(float fnlWeight) {
        this();
        this.fnlWeight = fnlWeight;
    }

    public State(int initialNumArcs) {
        this.initialNumArcs = initialNumArcs;
        if (initialNumArcs > 0) {
            arcs = new ArrayList<Arc>(initialNumArcs);
        }
    }

    public void arcSort(Comparator<Arc> cmp) {
        Collections.sort(arcs, cmp);
    }

    public float getFinalWeight() {
        return fnlWeight;
    }

    public void setFinalWeight(float fnlfloat) {
        this.fnlWeight = fnlfloat;
    }

    public void setArcs(ArrayList<Arc> arcs) {
        this.arcs = arcs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumArcs() {
        return this.arcs.size();
    }

    public void addArc(Arc arc) {
        this.arcs.add(arc);
    }

    public Arc getArc(int index) {
        return this.arcs.get(index);
    }

    public void setFinalState() {
        isFinalState = true;
    }

    public boolean isFinalState() {
        return isFinalState;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        State other = (State) obj;
        if (id != other.id)
            return false;
        if (!(fnlWeight == other.fnlWeight)) {
            if (Float.floatToIntBits(fnlWeight) != Float
                    .floatToIntBits(other.fnlWeight))
                return false;
        }
        if (arcs == null) {
            if (other.arcs != null)
                return false;
        } else if (!arcs.equals(other.arcs))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(" + id + ", " + fnlWeight + ")");
        return sb.toString();
    }

    public Arc deleteArc(int index) {
        return this.arcs.remove(index);
    }

    @Override
    public int hashCode() {
        return id * 991;
    }

    public void setArc(int index, Arc arc) {
        arcs.set(index, arc);
    }

    @Override
    public int compareTo(State otherState) {
        if (this.arcs.size() == 0) {
            return 42;
        } else if (otherState.getNumArcs() == 0) {
            return -42;
        }

        return (scanArcsToFind(otherState, this)) ? -42 : 42;
    }

    private boolean scanArcsToFind(State stateToFind,
                                   State currentState) {
        if (currentState.getNumArcs() == 0) {
            return false;
        }
        if (currentState.equals(stateToFind)) {
            return true;
        }

        for (int i = 0; i < currentState.getNumArcs(); i++) {
            if (scanArcsToFind(stateToFind, currentState.getArc(i)
                    .getNextState())) {
                return true;
            }
        }

        return false;

    }

    public State getTarget(int letter) {
        for (Arc arc : arcs) {
            if (arc.getIlabel() == letter) {
                return arc.getNextState();
            }
        }
        return null;
    }

    public Arc getArcForTarget(int letter) {
        for (Arc arc : arcs) {
            if (arc.getIlabel() == letter) {
                return arc;
            }
        }
        return null;
    }

    public List<Arc> getArcs() {
        return arcs;
    }

}
