package generator;

import org.objectweb.asm.commons.GeneratorAdapter;
import util.Fst;

/**
 * Created by devict on 21/06/15.
 */
public class FstCompiler {

    private final Fst fst;
    private GeneratorAdapter ga;

    public FstCompiler(Fst fst, GeneratorAdapter ga) {
        this.fst = fst;
        this.ga = ga;
    }

    public void compile() {
        // Switch generation etc. here
    }
}
