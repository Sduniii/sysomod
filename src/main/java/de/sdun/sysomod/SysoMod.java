package de.sdun.sysomod;

/**
 * A Simple Mod for the System.out.println command.<br>
 * It adds the call reference at the end of the line, as with an error message.<br>
 * <br>
 * Load it with <code>SysoMod.loadSysoMod()</code> in your project.<br>
 * <br>
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

            private boolean isLn = false;

            @Override
            public void print(boolean b) {
                print((Object) b);
            }

            @Override
            public void print(char c) {
                print((Object) c);
            }

            @Override
            public void print(int i) {
                print((Object) i);
            }

            @Override
            public void print(long l) {
                print((Object) l);
            }

            @Override
            public void print(float v) {
                print((Object) v);
            }

            @Override
            public void print(double v) {
                print((Object) v);
            }

            @Override
            public void print(char[] chars) {
                print((Object) chars);
            }

            @Override
            public void print(String s) {
                print((Object) s);
            }

            @Override
            public void print(Object o) {
                super.print(getString(o));
            }

            @Override
            public void println(String s) {
                println((Object) s);
            }

            @Override
            public void println(Object o) {
                super.println(getString(o));
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

            private Object getString(Object o) {
                StackTraceElement e = getCallSite();
                String fo = String.format("%-70s", o);
                if (e != null && !e.getClassName().equalsIgnoreCase("java.io.PrintStream")) {
                    String callSite =
                            String.format("%s.%s(%s:%d)",
                                    e.getClassName(),
                                    e.getMethodName(),
                                    e.getFileName(),
                                    e.getLineNumber());
                    return fo + " at " + callSite;
                } else
                    return o;
            }
        });
    }
}
