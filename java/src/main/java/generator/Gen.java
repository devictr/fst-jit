package generator;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

import java.lang.reflect.InvocationTargetException;

public class Gen {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "GenBCode", null, "java/lang/Object", null);

        Method init = Method.getMethod("void <init>()");
        GeneratorAdapter gaInit = new GeneratorAdapter(Opcodes.ACC_PUBLIC, init, null, null, cw);
        gaInit.visitCode();
        gaInit.loadThis();
        gaInit.invokeConstructor(Type.getType(Object.class), init);
        gaInit.returnValue();
        gaInit.endMethod();

        Method run = Method.getMethod("int run()");
        GeneratorAdapter ga = new GeneratorAdapter((Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC), run, null, null, cw);
        ga.visitCode();
        // BEGIN important part
        ga.push(42);
        // END important part
        ga.returnValue();
        ga.endMethod();

        cw.visitEnd();

        byte[] generatedByteCode = cw.toByteArray();


        DynamicClassLoader l = new DynamicClassLoader();
        l.putClass("GenBCode", generatedByteCode);

        Class<?> GenBCode = l.loadClass("GenBCode");
        System.out.println(GenBCode.getMethod("run").invoke(null));
    }

}

