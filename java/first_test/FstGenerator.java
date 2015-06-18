import java.util.List;

class FstGenerator {

    private StringBuffer strBuff;
    private List<State> fstStates;

    public FstGenerator(List<State> fstStates) {
        this.fstStates = fstStates;
        this.strBuff = new StringBuffer();
    }

    public StringBuffer compute() {
        append("class HelloWorld {");
         
        generateMain();

        append("}");

        return strBuff;
    }

    private void append(String strToAppend) {
        strBuff.append(strToAppend);
        strBuff.append("\n");
    }

    private void generateMain() {
        appendWithTab("public void main(String... args) {", 1);

        appendWithTab("System.out.println(\"Hello World !\");", 2);

        appendWithTab("}", 1);
    }

    private void appendWithTab(String strToAppend, int numberOfTab) {
        for (int i = 0; i < numberOfTab; i++) {
            strBuff.append("\t");
        }
        strBuff.append(strToAppend);
        strBuff.append("\n");
    }

}
