import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class PrintTraceVisitor extends ClassVisitor {
    public PrintTraceVisitor(int i, ClassVisitor classVisitor) {
        super(i, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        return super.visitMethod(i, s, s1, s2, strings);
    }
}
