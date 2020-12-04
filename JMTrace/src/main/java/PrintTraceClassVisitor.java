import jdk.internal.org.objectweb.asm.Opcodes;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.*;


public class PrintTraceClassVisitor extends ClassVisitor {
    static class PrintTraceMethodVisitor extends MethodVisitor {

        public PrintTraceMethodVisitor(int i, MethodVisitor methodVisitor) {
            super(i, methodVisitor);
        }

        @Override
        public void visitInsn(int opcode) {
            switch(opcode) {
                case AALOAD:
                case BALOAD:
                case CALOAD:
                case DALOAD:
                case FALOAD:
                case IALOAD:
                case LALOAD:
                case SALOAD:
                    visitInsn(DUP2);
                    visitMethodInsn(INVOKESTATIC, PrintTrace.getName(), "printTraceStarALOAD", "(Ljava/lang/Object;I)V", false);
                    break;
                case AASTORE:
                case BASTORE:
                case CASTORE:
                case DASTORE:
                case FASTORE:
                case IASTORE:
                case LASTORE:
                case SASTORE:

            }
            visitInsn(opcode);
        }

        @Override
        public void visitFieldInsn(int i, String s, String s1, String s2) {
            super.visitFieldInsn(i, s, s1, s2);
        }
    }

    public PrintTraceClassVisitor(int i, ClassVisitor classVisitor) {
        super(i, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        MethodVisitor mv = super.visitMethod(i, s, s1, s2, strings);
        return new PrintTraceMethodVisitor(ASM5, mv);
    }
}
