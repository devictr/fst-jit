package builder;

/**
 * Created by devict on 18/06/15.
 */
public class InputOutputPair {
    private String input;

    private String output;

    public InputOutputPair(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}