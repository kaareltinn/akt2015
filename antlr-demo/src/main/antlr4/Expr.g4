grammar Expr;

init: expr;

expr: expr '*' expr
    | expr '+' expr
    | Ident
    | Int
    ;

Ident: ('a'..'z'|'A'..'Z')+ ;
Int: ('0'..'9')+;

WS: [ \t\r\n]+ -> skip;
