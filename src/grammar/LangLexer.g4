lexer grammar LangLexer;

PLUS:       '+';
MINUS:      '-';
TIMES:      '*';
DIV:        '/';
ASSIGN:     '=';
LPAREN:     '(';
RPAREN:     ')';
SEMICOLON:  ';';
COLON:      ':';
LT:         '<';
GT:         '>';
EQ:         '==';
NEQ:        '!=';
NOT:        '!';
AND:        '&';
OR:         '|';
XOR:        '^';
ANDAND:     '&&';
OROR:       '||';
LSBRACK:    '[';
RSBRACK:    ']';
LBRACE:     '{';
RBRACE:     '}';
DOT:        '.';
DOTDOT:     '..';
DOTDOTDOT:  '...';
COMMA:      ',';

KW_RETURN:  'return';
KW_INT32:   'int32';

ID:             [a-zA-Z_]+ [a-zA-Z0-9_]*;

LITERAL_INT:    [0-9]+;

WS:             [ \t\n\r]+ -> skip;
COMMENT:        '/*' .*? '*/' -> skip;
LINE_COMMENT:   '//' ~'\n'* '\n' -> skip;