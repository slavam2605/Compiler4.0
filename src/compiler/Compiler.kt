package compiler

import ast.*
import compiler.util.CompilerBundle
import grammar.LangLexer
import grammar.LangParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

import java.io.OutputStream
import java.io.PrintWriter
import java.io.Writer

/**
 * @author Moklev Vyacheslav
 */
class Compiler {
    private val scope: Scope = Scope()

    fun compile(source: String, o: OutputStream) {
        val cs = CharStreams.fromString(source)
        val parser = LangParser(CommonTokenStream(LangLexer(cs)))
        val file = parser.file()
        val cb = CompilerBundle(PrintWriter(o))
        file.functions.forEach { function ->
            function.statements.forEach { statement -> 
                if (statement.mode == 1) {
                    scope.putVariable(
                            statement.varName.text,
                            Variable(Type.ofString(statement.typeName.text), statement.varName.text)
                    )
                } else {
                    val stmt = toStatement(statement)
                    stmt.compile(cb)
                }
            }
        }
        cb.flush()
    }

    private fun toStatement(context: LangParser.StatementContext): Statement {
        when (context.mode) {
            2 -> return AssignOp(
                    scope[context.varName.text] 
                            ?: throw IllegalStateException("No variable in scope: " + context.varName.text),
                    toExpression(context.e1)
            )
            else -> throw UnsupportedOperationException("Not supported parser rule branch: " + context.mode)
        }
    }

    private fun toExpression(context: LangParser.ExpressionContext): Expression {
        when (context.mode) {
            0 -> return scope[context.`var`.text] 
                    ?: throw IllegalStateException("No variable in scope: " + context.`var`.text)
            2 -> return toExpression(context.e1)
            4, 5 -> return BinaryOp(toExpression(context.e1), toExpression(context.e2), context.op.text)
            else -> throw UnsupportedOperationException("Not supported parser rule branch: " + context.mode)
        }
    }
}
