package builder;

/**
 * Created by devict on 18/06/15.
 */
public class InputOutputPair {
    private String input;

    private int output;

    public InputOutputPair(String input, int output) {
        this.input = input;
        this.output = output;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}