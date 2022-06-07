// simplified version of JSON
grammar ArithmeticExpression;

start : expr <EOF> ;

expr
    : expr (multop|divop) expr  // the order will enforce operator precedence
    | expr (addop|minusop) expr
    | '(' expr ')'
    | INT
    | VARIABLE
    ;
addop : '+' ;
minusop : '-' ;
multop : '*' ;
divop : '/' ;


INT : '0' | [1-9] [0-9]* ;
VARIABLE    : [a-z]+ ;

// special instruction to ignore whitespaces, go to next token
WS : [ \t\n\r] + -> skip ;