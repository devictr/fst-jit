package builder;

import util.Convert;
import util.Fst;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by devict on 19/06/15.
 */
public class MastLauncher {

    public static void main(String... args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/devict/Software/FST/fst-jit/java/first_test/src/main/resources/wordlist")));
        List<InputOutputPair> pairs = new ArrayList<InputOutputPair>();
        String s;
        String[] inputArray;
        while ((s = in.readLine()) != null && s.length() != 0) {
            inputArray = s.split("\\t");
            InputOutputPair pair = new InputOutputPair(inputArray[0], Integer.parseInt(inputArray[1]));
            pairs.add(pair);
        }
        MastBuilder builder = new MastBuilder();
        Fst fst = builder.buildMast(pairs);
        Convert.exportFstToDot(fst, "test.dot");

    }
}
