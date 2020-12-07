
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;


public class PrintTraceClassVisitor extends ClassVisitor {


    public PrintTraceClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        MethodVisitor mv = super.visitMethod(i, s, s1, s2, strings);
        return (s.startsWith("java") || s.startsWith("sun")) ? mv
                : new PrintTraceMethodVisitor(ASM5, mv);
    }
}
