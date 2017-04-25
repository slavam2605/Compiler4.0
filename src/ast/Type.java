package ast;

/**
 * @author Moklev Vyacheslav
 */
public enum Type {
    UNKNOWN("", ""), INT_32("int32", "i32");
    
    private String name;
    private String llvmName;
    
    Type(String name, String llvmName) {
        this.name = name;
        this.llvmName = llvmName;
    }

    public String getName() {
        return name;
    }

    public String getLlvmName() {
        return llvmName;
    }
    
    public static Type ofString(String name) {
        for (Type type: values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
