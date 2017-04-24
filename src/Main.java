import grammar.LangLexer;
import grammar.LangParser;
import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * @author Vyacheslav Moklev
 */
public class Main {
    public static void main(String[] args) throws IOException {
        CharStream cs = CharStreams.fromString("uint32 foo(uint32 a, uint32 b) {}");
        LangParser parser = new LangParser(new CommonTokenStream(new LangLexer(cs)));
        LangParser.FunctionContext function = parser.function();
        System.out.println(function.types.stream().map(RuleContext::getText).collect(Collectors.toList()));
        System.out.println(function.ids.stream().map(Token::getText).collect(Collectors.toList()));
    }
}
