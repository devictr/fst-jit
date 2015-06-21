package generator;

import org.objectweb.asm.Label;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LookupSwitchInsnNode;
import util.Fst;
import util.State;

/**
 * Created by devict on 21/06/15.
 */
public class FstCompiler {

    private final Fst fst;
    private GeneratorAdapter ga;

    private Label errorLabel;

    public FstCompiler(Fst fst, GeneratorAdapter ga) {
        this.fst = fst;
        this.ga = ga;
    }

    public void compile() {
        // Switch generation etc. here
        State initState = fst.getStart();
        // 0 : int[] token
        ga.newLocal(Type.INT_TYPE);
        // 1 : int pos
        ga.newLocal(Type.INT_TYPE);
        // 2 : float result
        ga.newLocal(Type.FLOAT_TYPE);

        // pos=0;
        ga.push(0);
        ga.storeLocal(1);
        // result=0f;
        ga.push(0f);
        ga.storeLocal(2);

        //generateCases(initState);
        testSwitch();
    }

    private void testSwitch() {
        InsnList insnList = new InsnList();
        LabelNode defaultLabelNode = new LabelNode(new Label());
        LabelNode[] nodes = new LabelNode[2];
        nodes[0] = new LabelNode(new Label());
        nodes[1] = new LabelNode(new Label());
        nodes[0].accept(ga);
        ga.push(42);
        nodes[1].accept(ga);
        ga.push(43);
        LookupSwitchInsnNode lookupSwitchInsnNode = new LookupSwitchInsnNode(defaultLabelNode, new int[]{1, 2}, nodes);
        insnList.add(lookupSwitchInsnNode);
        insnList.accept(ga);
    }

    private void generateCases(State currentState) {
        if (currentState.getNumArcs() > 0) {
            // if(pos>=token.length) {return -1;}
            generateTokenLengthTest();

            // switch(token[pos++])
            ga.loadLocal(0);
            ga.loadLocal(1);
            ga.iinc(1, 1);
            ga.arrayLoad(Type.INT_TYPE);

//            TableSwitchGenerator tsg = new TableSwitchGenerator();
//            List<Integer> keys = new ArrayList<>();
//            Label switchEnd;
//
//            for (int i = 0; i < currentState.getNumArcs(); i++) {
//                // case '<char>' :
//                keys.add(currentState.getArc(i).getIlabel());
//                if (currentState.getArc(i).getWeight() != 0f) {
//                    // result += <weight>;
//                    ga.loadLocal(2);
//                    ga.push(currentState.getArc(i).getWeight());
//                    ga.math(GeneratorAdapter.ADD, Type.FLOAT_TYPE);
//                    ga.storeLocal(2);
//                }
//
//                generateCases(currentState.getArc(i).getNextState());
//            }
//
//            // default : return -1
//            tsg.generateDefault();
//
//            switchEnd = ga.mark();
        } else {
            // return (pos!=token.length) ? -1 : result;
            generateTokenLengthTest();
            ga.loadLocal(2);
            ga.returnValue();
        }
    }

    private void generateTokenLengthTest() {
        // if(pos>=token.length) {return -1;}
        ga.loadLocal(1);
        ga.loadLocal(0);
        ga.arrayLength();
        ga.ifICmp(GeneratorAdapter.NE, errorLabel);
    }
}
