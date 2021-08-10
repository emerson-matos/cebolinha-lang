package br.edu.ufabc.antlr.ast;

public class CommandAtribuicao implements AbstractCommand {

    private String id;
    private String expr;

    public CommandAtribuicao(String id, String expr) {
        this.id = id;
        this.expr = expr;
    }

    @Override
    public String generateCode() {
        return id + " = " + expr + ";\n";
    }

    @Override
    public String toString() {
        return "CommandAtribuicao{" +
                "id='" + id + '\'' +
                ", expr='" + expr + '\'' +
                '}';
    }
}
