package util;

/**
 * Copyright 1999-2012 Carnegie Mellon University.
 * Portions Copyright 2002 Sun Microsystems, Inc.
 * Portions Copyright 2002 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 * <p>
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL
 * WARRANTIES.
 */

import java.io.*;
import java.util.HashMap;

/**
 * Provides the required functionality in order to convert from/to openfst's
 * text format
 *
 * @author John Salatas
 */
public class Convert {

    /**
     * Default private Constructor.
     */
    private Convert() {
    }

    /**
     * Exports an fst to the openfst text format Several files are created as
     * follows: - basename.input.syms - basename.output.syms - basename.fst.txt
     * See <a
     * href="http://www.openfst.org/twiki/bin/view/FST/FstQuickTour">OpenFst
     * Quick Tour</a>
     *
     * @param fst      the fst to export
     * @param basename the files' base name
     * @throws IOException IO went wrong
     */
    public static void export(Fst fst, String basename) throws IOException {
        exportSymbols(fst.getIsyms(), basename + ".input.syms");
        exportSymbols(fst.getOsyms(), basename + ".output.syms");
        exportFst(fst, basename + ".fst.txt");
    }

    /**
     * Exports an fst to the openfst text format
     *
     * @param fst      the fst to export
     * @param filename the openfst's fst.txt filename
     * @throws IOException IO went wrong
     */
    private static void exportFst(Fst fst, String filename) throws IOException {
        FileWriter file;

        file = new FileWriter(filename);
        PrintWriter out = new PrintWriter(file);

        // print start first
        State start = fst.getStart();
        out.println(start.getId() + "\t" + start.getFinalWeight());


        String[] isyms = fst.getIsyms();
        String[] osyms = fst.getOsyms();
        int numStates = fst.getNumStates();
        for (int i = 0; i < numStates; i++) {
            State s = fst.getState(i);
            int numArcs = s.getNumArcs();
            for (int j = 0; j < numArcs; j++) {
                Arc arc = s.getArc(j);
                String isym = (isyms != null) ? isyms[arc.getIlabel()]
                        : "" + (char) arc.getIlabel();
                String osym = (osyms != null) ? osyms[arc.getOlabel()]
                        : Integer.toString(arc.getOlabel());

                out.println(s.getId() + "\t" + arc.getNextState().getId()
                        + "\t" + isym + "\t" + osym + "\t" + arc.getWeight());
            }
        }

        out.close();

    }

    /**
     * Exports an fst to the openfst text format
     *
     * @param fst      the fst to export
     * @param filename the openfst's fst.txt filename
     * @throws IOException IO went wrong
     */
    public static void exportFstToDot(Fst fst, String filename) throws IOException {
        FileWriter file;

        file = new FileWriter(filename);
        PrintWriter out = new PrintWriter(file);
        out.println("digraph FST {");
        out.println("rankdir = LR;");
        out.println("label = \"\";");
        out.println("center = 1;");
        out.println("ranksep = \"0.4\";");
        out.println("nodesep = \"0.25\";");
        String[] isyms = fst.getIsyms();
        String[] osyms = fst.getOsyms();
        int numStates = fst.getNumStates();
        for (int i = 0; i < numStates; i++) {
            String shape = fst.getState(i).isFinalState() ? "doublecircle" : "circle";
            out.println(fst.getState(i).getId() + " [label = \"" + fst.getState(i).getId() + "\", shape = " + shape
                    + ", style = bold, fontsize = 14]");
            for (int j = 0; j < fst.getState(i).getNumArcs(); j++) {
                int target = fst.getState(i).getArc(j).getNextState().getId();
                char label = (char) fst.getState(i).getArc(j).getIlabel();
                out.println("\t" + fst.getState(i).getId() + " -> " + target + " [label = \"" + label + "\", fontsize = 14];");
            }
        }
        out.println("}");
        out.close();

    }

    /**
     * Exports a symbols' map to the openfst text format
     *
     * @param syms     the symbols' map
     * @param filename the the openfst's symbols filename
     * @throws IOException IO went wrong
     */
    private static void exportSymbols(String[] syms, String filename)
            throws IOException {
        if (syms == null)
            return;

        FileWriter file = new FileWriter(filename);
        PrintWriter out = new PrintWriter(file);

        for (int i = 0; i < syms.length; i++) {
            String key = syms[i];
            out.println(key + "\t" + i);
        }

        out.close();

    }

    /**
     * Imports an openfst's symbols file
     *
     * @param filename the symbols' filename
     * @return HashMap containing the imported string-to-id mapping
     * @throws IOException           IO went wrong
     * @throws NumberFormatException import failed due to input data format
     */
    private static HashMap<String, Integer> importSymbols(String filename)
            throws NumberFormatException, IOException {

        File symfile = new File(filename);
        if (!(symfile.exists() && symfile.isFile())) {
            return null;
        }

        FileInputStream fis = new FileInputStream(filename);
        DataInputStream dis = new DataInputStream(fis);
        BufferedReader br = new BufferedReader(new InputStreamReader(dis));
        HashMap<String, Integer> syms = new HashMap<String, Integer>();
        String strLine;

        while ((strLine = br.readLine()) != null) {
            String[] tokens = strLine.split("\\t");
            String sym = tokens[0];
            Integer index = Integer.parseInt(tokens[1]);
            syms.put(sym, index);

        }
        br.close();

        return syms;
    }

}