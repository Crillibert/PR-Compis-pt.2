grammar Algebra;

programa
    : ecuacion* ';' EOF
    ;

ecuacion
    : expresion relop expresion
    ;

expresion
    : expresion POW expresion                  #powExpr
    | expresion (POR | DIV) expresion          #mulDivExpr
    | expresion (MAS | MENOS) expresion        #addSubExpr
    | LPAREN expresion RPAREN                  #parenExpr
    | (MAS | MENOS)* atom                      #atomExpr
    ;

atom
    : reales   #numberAtom
    | variable #varAtom
    ;

reales
    : NUMERO_REAL
    ;

variable
    : VARIABLE
    ;

relop
    : EQ      #eqOp
    | GT      #gtOp
    | LT      #ltOp
    | ASIGN   #assignOp
    ;

VARIABLE
    : [a-zA-Z_][a-zA-Z0-9_]*
    ;

NUMERO_REAL
    : [0-9]+('.'[0-9]+)?
    ;

LPAREN : '{';
RPAREN : '}';
MAS    : '+';
MENOS  : '-';
POR    : '*';
DIV    : '/';
GT     : '>';
LT     : '<';
EQ     : '=';
POW    : '^';
ASIGN  : '=>';

WS
    : [ \r\n\t]+ -> skip
    ;