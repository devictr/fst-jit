import java.util.List;
import java.util.Arrays;
import java.util.Collections;

class Launcher {

    public static void main(String... args) {

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

        List<State> statesToCompute = Arrays.asList(state1, state2,
                state0, state4, state3);

        Collections.sort(statesToCompute);

        FstGenerator.compute();
    }

}
