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

    public static void printTraceStarASTORE() {

    }

    public static void printTraceGetStatic() {

    }

    public static void printTracePutStatic() {

    }

    public static void printTraceGetField() {

    }

    public static void printTracePutField() {

    }
}
