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
    :   (functions+=function)*
    ;
    
function
    :   resultType=type funcName=ID '(' 
            (paramTypes+=type paramNames+=ID ({peek(1).getType() != RPAREN}?  ',')?)* ')' '{' 
                (statements+=statement)* 
            '}'
    ;
    
statement locals [int mode] 
    :   'return' e1=expression ';'       {$mode = 0;}
    |   typeName=type varName=ID ';'     {$mode = 1;}
    |   varName=ID '=' e1=expression ';' {$mode = 2;}
    ;
    
expression locals [int mode]
    :   var=ID                                     {$mode = 0;}
    |   intLit=LITERAL_INT                         {$mode = 1;} 
    |   '(' e1=expression ')'                      {$mode = 2;}
    |   '-' e1=expression                          {$mode = 3;}
    |   e1=expression op=('*' | '/') e2=expression {$mode = 4;}
    |   e1=expression op=('+' | '-') e2=expression {$mode = 5;}
    ;
    
type
    :   'int32';