/**
 *
 * from sphinx4 project https://github.com/cmusphinx/sphinx4
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
 * original author : John Salatas
 *
 */

public class Arc {

    private float weight;

    private int iLabel;

    private int oLabel;

    private State nextState;

    public Arc() {
    }

    public Arc(int iLabel, int oLabel, float weight, State nextState) {
        this.weight = weight;
        this.iLabel = iLabel;
        this.oLabel = oLabel;
        this.nextState = nextState;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getIlabel() {
        return iLabel;
    }

    public void setIlabel(int iLabel) {
        this.iLabel = iLabel;
    }

    public int getOlabel() {
        return oLabel;
    }

    public void setOlabel(int oLabel) {
        this.oLabel = oLabel;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Arc other = (Arc) obj;
        if (iLabel != other.iLabel)
            return false;
        if (nextState == null) {
            if (other.nextState != null)
                return false;
        } else if (nextState.getId() != other.nextState.getId())
            return false;
        if (oLabel != other.oLabel)
            return false;
        if (!(weight == other.weight)) {
            if (Float.floatToIntBits(weight) != Float
                    .floatToIntBits(other.weight))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + iLabel + ", " + oLabel + ", " + weight + ", " + nextState
            + ")";
    }
    
} 
