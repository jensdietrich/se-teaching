grammar JSON;

json : value;
obj : '{' pair (',' pair)* '}' | '{' '}';
pair : key ':' value;
arr : '[' value (',' value)* ']' | '[' ']' ;
value : STRING | NUMBER | obj | arr ;
key : STRING ;

STRING : '"' (LETTERORDIGIT)* '"';
// fragment: no rule will be generated for this, mainly used as "macro" to make grammar more readable
fragment LETTERORDIGIT : [a-zA-Z0-9] ;
fragment NUMBER : '-'? INT ('.' [0-9] +)? ;
// no leading zeros in INT
fragment INT : '0' | [1-9] [0-9]* ;

// special instruction to ignore whitespaces, go to next token
WS : [ \t\n\r] + -> skip ;