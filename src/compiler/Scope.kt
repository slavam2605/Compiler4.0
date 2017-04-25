package compiler

import ast.Variable

import java.util.ArrayList
import java.util.HashMap

/**
 * @author Moklev Vyacheslav
 */
class Scope {
    private val stack: MutableList<MutableMap<String, Variable>>

    init {
        stack = ArrayList<MutableMap<String, Variable>>()
        stack.add(HashMap<String, Variable>())
    }

    fun enterScope() {
        stack.add(HashMap(stack.last()))
    }
    
    fun leaveScope() {
        stack.removeAt(stack.lastIndex)
    }
    
    fun putVariable(name: String, variable: Variable) {
        if (stack.last()[name] != null) {
            throw IllegalStateException("Redefining the existing variable in scope")
        }
        stack.last()[name] = variable
    }

    operator fun get(name: String): Variable? {
        return stack.last()[name]
    }
    
}
