package br.edu.ufabc.antlr.ast;

public class CommandWrite implements AbstractCommand {

    private String id;

    public CommandWrite(String id) {
        this.id = id;
    }

    @Override
    public String generateCode() {
        return "System.out.println(" + id + ");\n";
    }

    @Override
    public String toString() {
        return "CommandWrite{" +
                "id='" + id + '\'' +
                '}';
    }
}
