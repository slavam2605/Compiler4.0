package ast;

import compiler.util.CompileHint;
import compiler.util.CompilerBundle;

/**
 * @author Moklev Vyacheslav
 */
public class Variable implements Expression {
    private Type type;
    private String name;
    private int lastVersion = 0;
    private String value = null;

    public Variable(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    private String valueRepresentation() {
        return value == null
                ? "%" + (lastVersion == 0 ? name : name + "." + lastVersion)
                : value;
    }
    
    public String nextVersion() {
        value = null;
        lastVersion++;
        return valueRepresentation();
    }
    
    @Override
    public String compile(CompilerBundle cb, CompileHint hint) {
        return valueRepresentation();
    }

    @Override
    public Type getType() {
        return type;
    }
}
