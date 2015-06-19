package generator;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import util.State;
import util.Arc;

public class Launcher {

    public static void main(String... args) {
        
        State state1 = test1();
        State state2 = test2();
        State state3 = test3();
        
        FstGenerator fstGen = new FstGenerator();

        String classNameTest1 = "FstComputeTest1";
        String classNameTest2 = "FstComputeTest2";
        String classNameTest3 = "FstComputeTest3";

        StringBuffer strBuff1 = fstGen.compute(state1, classNameTest1);
        StringBuffer strBuff2 = fstGen.compute(state2, classNameTest2);
        StringBuffer strBuff3 = fstGen.compute(state3, classNameTest3);

        writeToFile(strBuff1, classNameTest1);
        writeToFile(strBuff2, classNameTest2);
        writeToFile(strBuff3, classNameTest3);
    }

    private static void writeToFile(StringBuffer strBuff, String className) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("../src/main/java/generated/" + className + ".java"))) {
            out.write(strBuff.toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static State test1() {
        State state9 = new State(); 
        state9.setId(9);

        Arc arc59 = new Arc((int) 'P', 0, 0f, state9);

        State state5 = new State(); 
        state5.setId(5);
        state5.addArc(arc59);

        Arc arc89 = new Arc((int) 'R', 0, 0f, state9);

        State state8 = new State(); 
        state8.setId(8);
        state8.addArc(arc89);

        Arc arc75 = new Arc((int) 'O', 0, 1f, state5);
        Arc arc78 = new Arc((int) 'A', 0, 0f, state8);

        State state7 = new State(); 
        state7.setId(7);
        state7.addArc(arc75);
        state7.addArc(arc78);

        Arc arc67 = new Arc((int) 'T', 0, 0f, state7);

        State state6 = new State(); 
        state6.setId(6);
        state6.addArc(arc67);

        Arc arc45 = new Arc((int) 'O', 0, 0f, state5);

        State state4 = new State(); 
        state4.setId(4);
        state4.addArc(arc45);

        Arc arc39 = new Arc((int) 'H', 0, 0f, state9);

        State state3 = new State(); 
        state3.setId(3);
        state3.addArc(arc39);

        Arc arc23 = new Arc((int) 'T', 0, 1f, state3);
        Arc arc29 = new Arc((int) 'P', 0, 0f, state9);

        State state2 = new State(); 
        state2.setId(2);
        state2.addArc(arc23);
        state2.addArc(arc29);

        Arc arc12 = new Arc((int) 'O', 0, 0f, state2);

        State state1 = new State(); 
        state1.setId(1);
        state1.addArc(arc12);

        Arc arc01 = new Arc((int) 'M', 0, 0f, state1);
        Arc arc04 = new Arc((int) 'P', 0, 2f, state4);
        Arc arc04b = new Arc((int) 'T', 0, 5f, state4);
        Arc arc06 = new Arc((int) 'S', 0, 3f, state6);

        State state0 = new State(); 
        state0.setId(0);
        state0.addArc(arc01);
        state0.addArc(arc04);
        state0.addArc(arc04b);
        state0.addArc(arc06);

        return state0;
    }

    private static State test2() {
        State state7 = new State(); 
        state7.setId(7);

        Arc arc67 = new Arc((int) 'H', 0, 0f, state7);

        State state6 = new State(); 
        state6.setId(6);
        state6.addArc(arc67);

        Arc arc56 = new Arc((int) 'T', 0, 1f, state6);
        Arc arc57 = new Arc((int) 'P', 0, 0f, state7);

        State state5 = new State(); 
        state5.setId(5);
        state5.addArc(arc56);
        state5.addArc(arc57);

        Arc arc45 = new Arc((int) 'O', 0, 0f, state5);

        State state4 = new State(); 
        state4.setId(4);
        state4.addArc(arc45);

        Arc arc27 = new Arc((int) 'P', 0, 0f, state7);

        State state2 = new State(); 
        state2.setId(2);
        state2.addArc(arc27);

        Arc arc12 = new Arc((int) 'O', 0, 0f, state2);

        State state1 = new State(); 
        state1.setId(1);
        state1.addArc(arc12);

        Arc arc31 = new Arc((int) 'T', 0, 2f, state1);
        Arc arc34 = new Arc((int) 'L', 0, 0f, state4);

        State state3 = new State(); 
        state3.setId(3);
        state3.addArc(arc31);
        state3.addArc(arc34);

        Arc arc01 = new Arc((int) 'P', 0, 2f, state1);
        Arc arc01b = new Arc((int) 'T', 0, 6f, state1);
        Arc arc03 = new Arc((int) 'S', 0, 3f, state3);
        Arc arc04 = new Arc((int) 'M', 0, 0f, state4);

        State state0 = new State(); 
        state0.setId(0);
        state0.addArc(arc01);
        state0.addArc(arc01b);
        state0.addArc(arc03);
        state0.addArc(arc04);

        return state0;
    }

    private static State test3() {
        State state4 = new State(); 
        state4.setId(4);

        Arc arc34 = new Arc((int) 'H', 0, 0f, state4);
        Arc arc24 = new Arc((int) 'P', 0, 0f, state4);

        State state3 = new State(); 
        state3.setId(3);
        state3.addArc(arc34);

        Arc arc23 = new Arc((int) 'T', 0, 1f, state3);

        State state2 = new State(); 
        state2.setId(2);
        state2.addArc(arc23);
        state2.addArc(arc24);

        Arc arc12 = new Arc((int) 'O', 0, 0f, state2);

        State state1 = new State(); 
        state1.setId(1);
        state1.addArc(arc12);

        Arc arc01 = new Arc((int) 'M', 0, 0f, state1);

        State state0 = new State(); 
        state0.setId(0);
        state0.addArc(arc01);

        return state0;
    }
}
