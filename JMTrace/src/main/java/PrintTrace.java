public class PrintTrace {
    private static final String name =
            PrintTrace.class.getCanonicalName().replace(".", "/");

    public static String getName() {
        return name;
    }

    public static void printTraceStarALOAD(Object arrayRef, int index) {
        String descriptor = String.format("%s[%d]", arrayRef.getClass().getCanonicalName(), index);
        System.out.println("R " + Thread.currentThread().getId() + " " + System.identityHashCode(arrayRef) + " " + descriptor);
    }

    public static void printTraceStarASTORE(Object arrayRef, int index) {
        String descriptor = String.format("%s[%d]", arrayRef.getClass().getCanonicalName(), index);
        System.out.println("W " + Thread.currentThread().getId() + " " + System.identityHashCode(arrayRef) + " " + descriptor);
    }

    public static void printTraceGetStatic(String className, String fieldName) {
        if (className.startsWith("java")) return;
        try {
            Object classObj = Class.forName(className.replace("/", "."));
            System.out.println("R " + Thread.currentThread().getId() + " " + System.identityHashCode(classObj) + " " + className + "." + fieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printTracePutStatic(String className, String fieldName) {
        if (className.startsWith("java")) return;
        try {
            Object classObj = Class.forName(className.replace("/", "."));
            System.out.println("W " + Thread.currentThread().getId() + " " + System.identityHashCode(classObj) + " " + className + "." + fieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printTraceGetField(String className, String fieldName) {
        if (className.startsWith("java")) return;
        try {
            Object classObj = Class.forName(className.replace("/", "."));
            System.out.println("R " + Thread.currentThread().getId() + " " + System.identityHashCode(classObj) + " " + className + "." + fieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printTracePutField(String className, String fieldName) {
        if (className.startsWith("java")) return;
        try {
            Object classObj = Class.forName(className.replace("/", "."));
            System.out.println("W " + Thread.currentThread().getId() + " " + System.identityHashCode(classObj) + " " + className + "." + fieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
