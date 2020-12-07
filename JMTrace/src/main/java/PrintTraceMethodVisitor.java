import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import static org.objectweb.asm.Opcodes.*;

class PrintTraceMethodVisitor extends MethodVisitor {

    public PrintTraceMethodVisitor(int i, MethodVisitor methodVisitor) {
        super(i, methodVisitor);
    }

    @Override
    public void visitInsn(int opcode) {
//        System.out.println("opcode: " + opcode);
//        System.out.println(PrintTrace.getName());
//        mv.visitMethodInsn(INVOKESTATIC, PrintTrace.getName(), "printt", "()V", false);
        switch(opcode) {
            case AALOAD:
            case BALOAD:
            case CALOAD:
            case DALOAD:
            case FALOAD:
            case IALOAD:
            case LALOAD:
            case SALOAD:
//                System.out.println("opcode: " + opcode);
//                mv.visitMethodInsn(INVOKESTATIC, PrintTrace.getName(), "printt", "()V", false);
                mv.visitInsn(DUP2); // riri
                mv.visitMethodInsn(INVOKESTATIC, PrintTrace.getName(), "printTraceStarALOAD", "(Ljava/lang/Object;I)V", false);
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
                    mv.visitInsn(DUP2_X2);// vvrivv
                    mv.visitInsn(POP2);// vvri
                    mv.visitInsn(DUP2_X2); // rivvri
                } else {
                    mv.visitInsn(DUP_X2);//vriv
                    mv.visitInsn(POP);//vri
                    mv.visitInsn(DUP2_X1);//rivri
                }
                mv.visitMethodInsn(INVOKESTATIC, PrintTrace.getName(), "printTraceStarASTORE", "(Ljava/lang/Object;I)V", false);
                break;
        }
        mv.visitInsn(opcode);
    }

    @Override
    public void visitFieldInsn(int i, String s, String s1, String s2) {
        switch (i) {
            case GETSTATIC:
                mv.visitLdcInsn(s);
                mv.visitLdcInsn(s1);
                mv.visitMethodInsn(INVOKESTATIC, PrintTrace.getName(), "printTraceGetStatic", "(Ljava/lang/String;Ljava/lang/String;)V", false);
                break;
            case PUTSTATIC:
                mv.visitLdcInsn(s);
                mv.visitLdcInsn(s1);
                mv.visitMethodInsn(INVOKESTATIC, PrintTrace.getName(), "printTracePutStatic", "(Ljava/lang/String;Ljava/lang/String;)V", false);
                break;
            case GETFIELD:
                mv.visitInsn(DUP);
                mv.visitLdcInsn(s1);
                mv.visitMethodInsn(INVOKESTATIC, PrintTrace.getName(), "printTraceGetField", "(Ljava/lang/Object;Ljava/lang/String;)V", false);
                break;
            case PUTFIELD:
                mv.visitInsn(DUP2);
                mv.visitInsn(POP);
                mv.visitLdcInsn(s1);
                mv.visitMethodInsn(INVOKESTATIC, PrintTrace.getName(), "printTracePutField", "(Ljava/lang/Object;Ljava/lang/String;)V", false);
                break;
        }
        mv.visitFieldInsn(i, s, s1, s2);
    }
}