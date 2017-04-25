import compiler.Compiler;

import java.io.IOException;

/**
 * @author Vyacheslav Moklev
 */
public class Main {
    public static void main(String[] args) throws IOException {
//        CharStream cs = CharStreams.fromString("uint32 foo(uint32 a, uint32 b) {\n" +
//                "return a + b;\n" +
//                "\n}");
//        LangParser parser = new LangParser(new CommonTokenStream(new LangLexer(cs)));
//        LangParser.FunctionContext function = parser.function();
//        System.out.println(function.paramTypes.stream().map(RuleContext::getText).collect(Collectors.toList()));
//        System.out.println(function.paramNames.stream().map(Token::getText).collect(Collectors.toList()));
        new Compiler().compile("int32 foo(int32 a, int32 b) {\n" +
                "int32 a;\n" +
                "int32 b;\n" +
                "int32 c;\n" +
                "c = a + c;\n" +
                "c = a + c;\n" +
                "c = a + c;\n" +
                "c = a + b;\n" +
                "\n}", System.out);
    }
}
