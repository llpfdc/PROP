/**
 * @author Lluís Pujalte Feliu De Cabrera
 *
 */
grammar Expr;


expr: '('expr')'                                #parentExpr
    | expr (MULT|DIV) expr                      #multDivExpr
    | expr (PLUS|MINUS) expr                    #addMinusExpr
    | <assoc=right> expr '^' expr               #exponentExpr
    |NAME '(' expr ')'                          #functionExpr
    |NAME '(' REF ',' REF ')'                   #functionDExpr
    |NAME '(' REF ',' REF ','REF ',' REF ')'    #functionDBExpr
    |NUM                                        #numericExpr
    |MINUS NUM                                  #numericNegExpr
    |REF                                        #idExpr
    ;

fragment LETTER : [a-zA-Z];
fragment DIGIT  : [0-9];

REF             :'~' NAME ':' ID;

MULT            : '*';
DIV             : '/';
PLUS            : '+';
MINUS           : '-';

ID              :DIGIT+;
NAME            :LETTER+;
NUM             :DIGIT+('.'DIGIT+)?;
WHITESPACE      :' '->skip;
STRING          : '"' ~["]* '"' -> skip;