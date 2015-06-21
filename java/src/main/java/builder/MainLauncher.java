package builder;

import generator.BytecodeCompiler;
import generator.FstGenerator;
import org.apache.commons.cli.*;
import util.Convert;
import util.Fst;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by devict on 19/06/15.
 */
public class MainLauncher {

    private static final Options options = new Options();

    static {
        options.addOption("g", "graph", true, "Prints the resulting FST in dot format. Specify destination file name in argument");
        options.addOption("j", "java-class", true, "Compiles the generated FST to a Java file. Specify the class name in argument");
        options.addOption("b", "bytecode", true, "Compiles the generated FST to bytecode and loads it. Specify the class name in argument");
    }

    public static Fst buildFst() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<InputOutputPair> pairs = new ArrayList<>();
        String s;
        String[] inputArray;
        while ((s = in.readLine()) != null && s.length() != 0) {
            inputArray = s.split("\\t");
            InputOutputPair pair = new InputOutputPair(inputArray[0], Integer.parseInt(inputArray[1]));
            pairs.add(pair);
        }
        MastBuilder builder = new MastBuilder();
        return builder.buildMast(pairs);
    }

    public static void toJava(Fst fst, String classname) {
        FstGenerator fstGenerator = new FstGenerator();
        StringBuffer stringBuffer = fstGenerator.compute(fst.getStart(), classname);
        try (BufferedWriter out = new BufferedWriter(new FileWriter(classname + ".java"))) {
            out.write(stringBuffer.toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void toBytecode(Fst fst, String className) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        BytecodeCompiler bytecodeCompiler = new BytecodeCompiler(fst, className);
        bytecodeCompiler.compile();
    }

    private static void help() {
        HelpFormatter help = new HelpFormatter();
        help.printHelp("Main", options);
        System.exit(0);
    }

    public static void main(String... args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        CommandLine cmd = null;
        CommandLineParser parser = new DefaultParser();
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            help();
        }

        Fst fst = buildFst();

        assert cmd != null;
        if (cmd.hasOption("g")) {
            System.out.println("[MastLauncher] Exporting FST to " + cmd.getOptionValue("g") + ".dot");
            Convert.exportFstToDot(fst, cmd.getOptionValue("g") + ".dot");
        }
        if (cmd.hasOption("j")) {
            toJava(fst, cmd.getOptionValue("j"));
        }
        if (cmd.hasOption("b")) {
            toBytecode(fst, cmd.getOptionValue("b"));
        }

    }

}
