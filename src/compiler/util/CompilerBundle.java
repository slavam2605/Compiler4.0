package compiler.util;

import java.io.PrintWriter;

/**
 * @author Moklev Vyacheslav
 */
public class CompilerBundle {
    private int varCounter = 0;
    private PrintWriter pw;

    public CompilerBundle(PrintWriter pw) {
        this.pw = pw;
    }

    public void println(String s) {
        pw.println(s);
    }
    
    public String nextVar() {
        return "%temp" + varCounter++;
    }
    
    public void flush() {
        pw.flush();
    }
}
