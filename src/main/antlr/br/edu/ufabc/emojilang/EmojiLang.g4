grammar EmojiLang;
@header {
package br.edu.ufabc.emojilang;
}
prog        :   'programa'  bloco   'fimprog;'
            ;
bloco       :   (cmd)+
            ;
cmd         :   cmdLeitura { System.out.println("reconheci um comando de leitura!"); }
            |   cmdEscrita { System.out.println("reconheci um comando de escrita!"); }
            |   cmAttrib { System.out.println("reconheci um comando de atribuição!"); }
            ;

cmdLeitura  :   'leia' AP
                       ID  { System.out.println("ID="+ _input.LT(-1).getText()); }
                       FP
                       SC
            ;
cmdEscrita  :   'escreva' AP
                          ID
                          FP
                          SC
            ;
cmAttrib    :   ID
                ATTR
                expr
                SC
            ;
expr        :   termo ( OP termo )*
            ;
termo       :   ID | NUMBER
            ;

AP          :   '('
            ;
FP          :   ')'
            ;
SC          :   ';'
            ;
OP          :   '+' | '*' | '/' | '-'
            ;
ATTR        :   '='
            ;
ID          :   [a-z] ([a-z]|[A-Z]|[0-9])*
            ;
NUMBER      :   [0-9]+ ('.'[0-9]+)?
            ;
WS          :   (' '|'\t'|'\n'|'\r') -> skip
            ;