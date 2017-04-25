package compiler.util;

import ast.Type;

/**
 * @author Moklev Vyacheslav
 */
public class CompilerUtils {
    public static String typeString(Type type, String value) {
        return type.getLlvmName() + " " + value;
    }
}
