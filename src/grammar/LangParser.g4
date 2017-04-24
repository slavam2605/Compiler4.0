parser grammar LangParser;

@members {
    Token peek(int x) {
        return getInputStream().LT(1 + x);
    }
    <T> T val(T value) {
        System.out.println(value);
        return value;
    }
}

options {
    tokenVocab = LangLexer;
}

file
    :   function*
    ;
    
function
    :   type ID '(' (types+=type ids+=ID ({peek(1).getType() != RPAREN}?  ',')?)* ')' '{' statement* '}'
    ;
    
statement
    :   'return' expression ';'
    ;
    
expression
    :   ID
    |   expression ('*' | '/') expression
    |   expression ('+' | '-') expression
    ;
    
type
    :   'uint32';