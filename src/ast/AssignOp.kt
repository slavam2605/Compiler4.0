package ast

import compiler.util.CompileHint
import compiler.util.CompilerBundle

/**
 * @author Moklev Vyacheslav
 */
class AssignOp(private val left: Expression, private val right: Expression) : Statement {
    override fun compile(cb: CompilerBundle, hint: CompileHint) {
        if (left is Variable) {
            right.compile(cb, CompileHint.ofVarStore(left))
        } else {
            throw UnsupportedOperationException("Left side expression is not a variable")
        }
    }
}
