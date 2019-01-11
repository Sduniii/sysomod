package de.sdun.sysomod;
/**
 * A Simple Mod for the System.out.println command.
 * It at the reference to the line, like by an error message.
 *
 * Load with SysoMod.loadSysoMod() in your project.
 *
 * Created by Tobias Sdun on 31.08.2017.
 */
public class SysoMod {


    /**
     * Loads the Mod.
     */
    public static void loadSysoMod() {
        System.setOut(new java.io.PrintStream(System.out) {

            private StackTraceElement getCallSite() {
                for (StackTraceElement e : Thread.currentThread()
                        .getStackTrace())
                    if (!e.getMethodName().equals("getStackTrace")
                            && !e.getClassName().equals(getClass().getName()))
                        return e;
                return null;
            }

            @Override
            public void println(String s) {
                println((Object) s);
            }

            @Override
            public void println(Object o) {
                StackTraceElement e = getCallSite();
                String fo = String.format("%-70s", o);
                String callSite = e == null ? "??" :
                        String.format("%s.%s(%s:%d)",
                                e.getClassName(),
                                e.getMethodName(),
                                e.getFileName(),
                                e.getLineNumber());
                super.println(fo + "at " + callSite);
            }

            @Override
            public void println(double x) {
                println((Object) x);
            }

            @Override
            public void println(int x) {
                println((Object) x);
            }

            @Override
            public void println(float x) {
                println((Object) x);
            }

            @Override
            public void println(long x) {
                println((Object) x);
            }
        });
    }
}
