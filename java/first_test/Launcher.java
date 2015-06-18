class Launcher {

    public static void main(String... args) {

        State state4 = new State(); 

        Arc arc34 = new Arc((int) 'H', 0, 0f, state4);
        Arc arc24 = new Arc((int) 'P', 0, 0f, state4);

        State state3 = new State(); 
        state3.addArc(arc34);

        Arc arc23 = new Arc((int) 'T', 0, 1f, state3);

        State state2 = new State(); 
        state2.addArc(arc23);
        state2.addArc(arc24);

        Arc arc12 = new Arc((int) 'O', 0, 0f, state2);

        State state1 = new State(); 
        state1.addArc(arc12);

        Arc arc01 = new Arc((int) 'M', 0, 0f, state1);

        State state0 = new State(); 
        state0.addArc(arc01);

        FstGenerator.compute();
    }

}
