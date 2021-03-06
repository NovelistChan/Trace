import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

import javassist.ClassClassPath;
import javassist.ClassPool;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;


public class Agent {
//    static private Instrumentation inst = null;
    /**
     * This method is called before the application’s main-method is called, 
     * when this agent is specified to the Java VM. 
     **/
    public static void premain(String agentArgs, Instrumentation _inst) {
        System.out.println("PerfMonAgent.premain() was called.");
        // Initialize the static variables we use to track information.  
//        inst = _inst;
        // Set up the class-file transformer.  
        ClassFileTransformer trans = new TraceTransformer();
        System.out.println("Adding a transformer instance to the JVM.");
//        inst.addTransformer(trans);
        _inst.addTransformer(trans);
    }
}