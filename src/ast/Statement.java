package ast;

import compiler.util.CompileHint;
import compiler.util.CompilerBundle;

/**
 * @author Moklev Vyacheslav
 */
public interface Statement {
    void compile(CompilerBundle cb, CompileHint hint);

    default void compile(CompilerBundle cb) {
        compile(cb, CompileHint.EMPTY_HINT);
    }
}
