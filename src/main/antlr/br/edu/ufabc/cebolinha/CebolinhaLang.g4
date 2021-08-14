grammar CebolinhaLang;

@header {
package br.edu.ufabc.cebolinha;

import br.edu.ufabc.antlr.ast.CebolinhaLangProgram;
import br.edu.ufabc.antlr.ast.AbstractCommand;
import br.edu.ufabc.antlr.ast.CommandAtribuicao;
import br.edu.ufabc.antlr.ast.CommandDecision;
import br.edu.ufabc.antlr.ast.CommandFor;
import br.edu.ufabc.antlr.ast.CommandRead;
import br.edu.ufabc.antlr.ast.CommandWrite;
import br.edu.ufabc.antlr.datastructures.CebolinhaLangSymbol;
import br.edu.ufabc.antlr.datastructures.CebolinhaLangSymbolTable;
import br.edu.ufabc.antlr.datastructures.CebolinhaLangVariable;
import br.edu.ufabc.antlr.exceptions.CebolinhaLangSemanticException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;
}

@members{
private int _tipo;
private String _varName;
private String _varValue;
private CebolinhaLangSymbolTable _symbolTable = new CebolinhaLangSymbolTable();
private CebolinhaLangSymbol _symbol;
private CebolinhaLangProgram program = new CebolinhaLangProgram();
private List<AbstractCommand> currentThread;
private List<AbstractCommand> listaTrue;
private List<AbstractCommand> listaFalse;
private Stack<List<AbstractCommand>> allCommands = new Stack<>();
private Stack<String> conditionStack = new Stack<>();

private String _readId;
private String _writeId;
private String _exprId;
private String _exprContent;
private String _exprDecision;
private String _exprFor;

private void verificaId(String symbolName) {
    if (!(_symbolTable.exists(symbolName))) {
        throw new CebolinhaLangSemanticException("Symbol " + symbolName + " not declared!");
    }
}

public void showCommands(Logger logger) {
    program.getCommandList().stream().map(Object::toString).forEach(logger::info);
}

public void generateCode() {
    program.generateTarget();
}
}

prog        :   'ploglama' decl bloco 'fimploglama;' { program.setTable(_symbolTable); program.setCommandList(allCommands.pop()); }
            ;
decl        :   (declaravar)+
            ;
declaravar  :   tipo ID {
                    _varName = _input.LT(-1).getText();
                    _varValue = null;
                    _symbol = new CebolinhaLangVariable(_varName, _tipo, _varValue);
                    _symbolTable.add(_symbol);
                }
                (VIR ID {
                    _varName = _input.LT(-1).getText();
                    _varValue = null;
                    _symbol = new CebolinhaLangVariable(_varName, _tipo, _varValue);
                    _symbolTable.add(_symbol);
                })*
                SC
            ;
tipo        :   'numelo' { _tipo = CebolinhaLangVariable.NUMBER; }
            |   'palavla' { _tipo = CebolinhaLangVariable.TEXT; }
            ;
bloco       :   { currentThread =  new ArrayList<AbstractCommand>();
                  allCommands.push(currentThread); } (cmd)+
            ;
cmd         :   cmdLeitura
            |   cmdEscrita
            |   cmdAttrib
            |   cmdSelecao
            |   cmdFor
            ;

cmdLeitura  :   'leia' AP
                       ID { verificaId(_input.LT(-1).getText());
                            _readId = _input.LT(-1).getText();
                            }
                       FP
                       SC {
                                allCommands.peek().add(new CommandRead(_readId, _symbolTable.getSymbol(_readId)));
                          }
            ;
cmdEscrita  :   'escleva' AP
                          ID {  verificaId(_input.LT(-1).getText());
                                _writeId = _input.LT(-1).getText();
                             }
                          FP
                          SC {
                                allCommands.peek().add(new CommandWrite(_writeId));
                             }
            ;
cmdAttrib    :   ID { verificaId(_input.LT(-1).getText());
                      _exprId = _input.LT(-1).getText();
                    }
                ATTR    { _exprContent = ""; }
                expr
                SC { allCommands.peek().add(new CommandAtribuicao(_exprId, _exprContent)); }
            ;
cmdSelecao  :   'se' AP
                     ID             {_exprDecision = _input.LT(-1).getText();}
                     OPREL          {_exprDecision += _input.LT(-1).getText();}
                     (ID | NUMBER)  {_exprDecision += _input.LT(-1).getText();}
                     FP
                     ACH            {currentThread = new ArrayList<AbstractCommand>(); allCommands.push(currentThread);}
                     (cmd)+
                     FCH {listaTrue = allCommands.pop();}
                     ('senao'
                                ACH {currentThread = new ArrayList<AbstractCommand>(); allCommands.push(currentThread);}
                                (cmd+) FCH{listaFalse = allCommands.pop();
                                            allCommands.peek().add(new CommandDecision(_exprDecision, listaTrue, listaFalse));} )?
            ;
cmdFor: 	FOR
            AP
            ID 				{ verificaId(_input.LT(-1).getText());
                              _varName = _input.LT(-1).getText();
                              _varValue = null;
                            }
            ATTR 			{ _exprContent = ""; }
            expr
            SC				{ _exprFor = _varName + " = " + _exprContent + ";";
                            }
            ID 				{ _exprFor += _input.LT(-1).getText();
                            }
            OPREL 			{ _exprFor += _input.LT(-1).getText();
                              _exprContent = "";
                            }
            expr			{ _exprFor += _exprContent;					}
            SC 				{ _exprFor += _input.LT(-1).getText();		}
            ID 				{ _exprFor += _input.LT(-1).getText();      }
            OP_CHANGE		{ _exprFor += _input.LT(-1).getText();	}
            FP
            ACH			    { currentThread = new ArrayList<AbstractCommand>();
                              allCommands.push(currentThread);
                              conditionStack.push(_exprFor);
                            }
            (cmd)+
            FCH 			{ CommandFor forCommand = new CommandFor(conditionStack.pop(), allCommands.pop());
                              allCommands.peek().add(forCommand);
                            }
    ;

expr        :   termo
                (
                    OP { _exprContent += _input.LT(-1).getText();}
                    termo
                )*
            ;

termo       :   ID     { verificaId(_input.LT(-1).getText());
                         _exprContent += _input.LT(-1).getText();
                       }
            |   NUMBER { _exprContent += _input.LT(-1).getText();}
            | STRING_VAL        { _exprContent += _input.LT(-1).getText();}
            ;
OP_CHANGE	:	'++'
			|	'--'
			;
STRING_VAL  : '"' ([a-z]|[A-Z]|[0-9]|' ')* '"'
			;
FOR         :   'pala'
            ;
VIR         :   ','
            ;
AP          :   '('
            ;
FP          :   ')'
            ;
SC          :   ';'
            ;
ACH         :   '{'
            ;
FCH         :   '}'
            ;
OP          :   '+' | '*' | '/' | '-'
            ;
OPREL       :   '>' | '<' | '>=' | '<=' | '==' | '!='
            ;
ATTR        :   '='
            ;
ID          :   [a-z] ([a-z]|[A-Z]|[0-9])*
            ;
NUMBER      :   [0-9]+ ('.'[0-9]+)?
            ;
WS          :   (' '|'\t'|'\n'|'\r') -> skip
            ;