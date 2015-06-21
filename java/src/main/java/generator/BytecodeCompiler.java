package generator;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

import java.lang.reflect.InvocationTargetException;

public class BytecodeCompiler {

    private String className;
    private ClassWriter classWriter;
    private byte[] generatedByteCode;

    public BytecodeCompiler(String className) {
        this.className = className;
        classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
    }

    private void initClass() {
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, "java/lang/Object", null);
        Method init = Method.getMethod("void <init>()");
        GeneratorAdapter gaInit = new GeneratorAdapter(Opcodes.ACC_PUBLIC, init, null, null, classWriter);
        gaInit.visitCode();
        gaInit.loadThis();
        gaInit.invokeConstructor(Type.getType(Object.class), init);
        gaInit.returnValue();
        gaInit.endMethod();
    }

    private void createRunMethod() {
        Method run = Method.getMethod("int run()");
        GeneratorAdapter ga = new GeneratorAdapter((Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC), run, null, null, classWriter);
        ga.visitCode();
        // BEGIN important part
        ga.push(42);
        // END important part
        ga.returnValue();
        ga.endMethod();
    }

    private void getBytecode() {
        classWriter.visitEnd();
        generatedByteCode = classWriter.toByteArray();
    }

    private Class<?> loadClass() throws ClassNotFoundException {
        DynamicClassLoader l = new DynamicClassLoader();
        l.putClass(className, generatedByteCode);

        return l.loadClass(className);
    }

    public void compile() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        initClass();
        createRunMethod();
        getBytecode();
        Class<?> loadedClass = loadClass();
        System.out.println(loadedClass.getMethod("run").invoke(null));
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BytecodeCompiler bytecodeCompiler = new BytecodeCompiler("CompiledFST");
        bytecodeCompiler.compile();
    }

}

