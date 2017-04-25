package ast;

import compiler.util.CompileHint;
import compiler.util.CompilerBundle;

/**
 * @author Moklev Vyacheslav
 */
public interface Expression {
    String compile(CompilerBundle cb, CompileHint hint);
    Type getType();
    
    default String compile(CompilerBundle cb) {
        return compile(cb, CompileHint.EMPTY_HINT);
    }
}
