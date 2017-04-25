package ast

import compiler.util.CompileHint
import compiler.util.CompilerBundle
import compiler.util.CompilerUtils.typeString

import java.util.HashMap

/**
 * @author Moklev Vyacheslav
 */
class BinaryOp(private val left: Expression, private val right: Expression, private val op: String) : Expression {
    private data class TypedOp(private val op: String, private val type: Type)

    override fun compile(cb: CompilerBundle, hint: CompileHint): String {
        val leftVal = left.compile(cb)
        val rightVal = right.compile(cb)
        if (left.type != right.type || left.type == Type.UNKNOWN)
            throw IllegalArgumentException("Invalid types: " + left.type + ", " + right.type)
        val llvmOp = llvmOps[TypedOp(op, left.type)] 
                ?: throw UnsupportedOperationException("Unknown type: " + left.type)

        val temp = hint.varStore?.nextVersion() 
                ?: hint.store 
                ?: cb.nextVar()
        cb.println("$temp = $llvmOp ${typeString(left.type, leftVal)}, ${typeString(right.type, rightVal)}")
        return temp
    }

    override fun getType(): Type {
        return Type.UNKNOWN
    }

    companion object {
        private val llvmOps = HashMap<TypedOp, String>()

        init {
            llvmOps.put(TypedOp("+", Type.INT_32), "add")
            llvmOps.put(TypedOp("-", Type.INT_32), "sub")
            llvmOps.put(TypedOp("*", Type.INT_32), "mul")
            llvmOps.put(TypedOp("/", Type.INT_32), "sdiv")
        }
    }
}
