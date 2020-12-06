
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;


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
                    visitInsn(DUP2); // riri
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
                    if (opcode == LASTORE || opcode == DASTORE) {
                        visitInsn(DUP2_X2);// vvrivv
                        visitInsn(POP2);// vvri
                        visitInsn(DUP2_X2); // rivvri
                    } else {
                        visitInsn(DUP_X2);//vriv
                        visitInsn(POP);//vri
                        visitInsn(DUP2_X1);//rivri
                    }
                    visitMethodInsn(INVOKESTATIC, PrintTrace.getName(), "printTraceStartASTORE", "(Ljava/lang/Object;I)V", false);
                    break;
            }
            visitInsn(opcode);
        }

        @Override
        public void visitFieldInsn(int i, String s, String s1, String s2) {
            switch (i) {
                case GETSTATIC:
                    visitLdcInsn(s);
                    visitLdcInsn(s1);
                    visitMethodInsn(INVOKESTATIC, PrintTrace.getName(), "printTraceGetStatic", "(Ljava/lang/String;Ljava/lang/String;V)", false);
                    break;
                case PUTSTATIC:
                    visitLdcInsn(s);
                    visitLdcInsn(s1);
                    visitMethodInsn(INVOKESTATIC, PrintTrace.getName(), "printTracePutStatic", "(Ljava/lang/String;Ljava/lang/String;V)", false);
                    break;
                case GETFIELD:
                    visitInsn(DUP);
                    visitLdcInsn(s1);
                    visitMethodInsn(INVOKESTATIC, PrintTrace.getName(), "printTraceGetField", "(Ljava/lang/String;Ljava/lang/String;V)", false);
                    break;
                case PUTFIELD:
                    visitInsn(DUP2);
                    visitInsn(POP);
                    visitLdcInsn(s1);
                    visitMethodInsn(INVOKESTATIC, PrintTrace.getName(), "printTracePutField", "(Ljava/lang/String;Ljava/lang/String;V)", false);
                    break;
            }
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
