class HelloWorldGenerator {

    private static StringBuffer strBuff = new StringBuffer();

    public static void compute() {
        append("class HelloWorld {");
         
        generateMain();

        append("}");
        System.out.print(strBuff.toString());
    }

    private static void append(String strToAppend) {
        strBuff.append(strToAppend);
        strBuff.append("\n");
    }

    private static void generateMain() {
        appendWithTab("public static void main(String... args) {", 1);

        appendWithTab("System.out.println(\"Hello World !\");", 2);

        appendWithTab("}", 1);
    }

    private static void appendWithTab(String strToAppend, int numberOfTab) {
        for (int i = 0; i < numberOfTab; i++) {
            strBuff.append("\t");
        }
        strBuff.append(strToAppend);
        strBuff.append("\n");
    }


}
