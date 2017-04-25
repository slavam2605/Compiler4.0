package compiler.util;

import ast.Variable;

/**
 * @author Moklev Vyacheslav
 */
public class CompileHint {
    private String store = null;
    private Variable varStore = null;
    
    public static CompileHint ofStore(String store) {
        CompileHint hint = new CompileHint();
        hint.store = store;
        return hint;
    }
    
    public static CompileHint ofVarStore(Variable varStore) {
        CompileHint hint = new CompileHint();
        hint.varStore = varStore;
        return hint;
    }
    
    public static final CompileHint EMPTY_HINT = new CompileHint();

    public String getStore() {
        return store;
    }

    public Variable getVarStore() {
        return varStore;
    }
}
