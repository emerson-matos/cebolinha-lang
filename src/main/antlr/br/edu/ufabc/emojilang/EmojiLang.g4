grammar EmojiLang;

@header {
package br.edu.ufabc.emojilang;

import br.edu.ufabc.antlr.datastructures.EmojiLangSymbol;
import br.edu.ufabc.antlr.datastructures.EmojiLangSymbolTable;
import br.edu.ufabc.antlr.datastructures.EmojiLangVariable;
import br.edu.ufabc.antlr.exceptions.EmojiLangSemanticException;
}

@members{
private int _tipo;
private String _varName;
private String _varValue;
private EmojiLangSymbolTable _symbolTable = new EmojiLangSymbolTable();
private EmojiLangSymbol _symbol;

private void verificaId(String symbolName) {
    if (!(_symbolTable.exists(symbolName))) {
        throw new EmojiLangSemanticException("Symbol " + symbolName + " not declared!");
    }
}
}

prog        :   'programa' decl bloco 'fimprog;'
            ;
decl        :   (declaravar)+
            ;
declaravar  :   tipo ID {
                    _varName = _input.LT(-1).getText();
                    _varValue = null;
                    _symbol = new EmojiLangVariable(_varName, _tipo, _varValue);
                    _symbolTable.add(_symbol);
                }
                (VIR ID {
                    _varName = _input.LT(-1).getText();
                    _varValue = null;
                    _symbol = new EmojiLangVariable(_varName, _tipo, _varValue);
                    _symbolTable.add(_symbol);
                })*
                SC
            ;
tipo        :   'numero' { _tipo = EmojiLangVariable.NUMBER; }
            |   'texto' { _tipo = EmojiLangVariable.TEXT; }
            ;
bloco       :   (cmd)+
            ;
cmd         :   cmdLeitura
            |   cmdEscrita
            |   cmAttrib
            ;

cmdLeitura  :   'leia' AP
                       ID { verificaId(_input.LT(-1).getText()); }
                       FP
                       SC
            ;
cmdEscrita  :   'escreva' AP
                          ID { verificaId(_input.LT(-1).getText()); }
                          FP
                          SC
            ;
cmAttrib    :   ID { verificaId(_input.LT(-1).getText()); }
                ATTR
                expr
                SC
            ;
expr        :   termo ( OP termo )*
            ;
termo       :   ID { verificaId(_input.LT(-1).getText()); } | NUMBER
            ;

VIR         :   ','
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