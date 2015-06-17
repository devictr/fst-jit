/**
 * 
 * From sphinx4 project https://github.com/cmusphinx/sphinx4
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
 * original author John Salatas
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class State {

    protected int id = -1;

    private float fnlWeight;

    private ArrayList<Arc> arcs = null;

    protected int initialNumArcs = -1;

    protected State() {
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

    public void setArcs(ArrayList<Arc> arcs) {
        this.arcs = arcs;
    }

    public void setFinalWeight(float fnlfloat) {
        this.fnlWeight = fnlfloat;
    }

    public int getId() {
        return id;
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

}
