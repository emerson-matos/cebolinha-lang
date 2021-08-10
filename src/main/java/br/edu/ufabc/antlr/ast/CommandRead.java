package br.edu.ufabc.antlr.ast;

import br.edu.ufabc.antlr.datastructures.CebolinhaLangSymbol;
import br.edu.ufabc.antlr.datastructures.CebolinhaLangVariable;

public class CommandRead implements AbstractCommand {

    private final String id;
    private final CebolinhaLangVariable variable;

    public CommandRead(String id, CebolinhaLangSymbol variable) {
        this.id = id;
        this.variable = (CebolinhaLangVariable) variable;
    }

    @Override
    public String generateCode() {
        String inputCode = variable.getType() == CebolinhaLangVariable.NUMBER ? "Double" : "Line";
        return id + " = _key.next" + inputCode + "();\n";
    }

    @Override
    public String toString() {
        return "CommandRead{" +
                "id='" + id + '\'' +
                '}';
    }
}
