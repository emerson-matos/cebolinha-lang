package br.edu.ufabc.antlr.ast;

import java.util.List;

public class CommandFor implements AbstractCommand {

    private final List<AbstractCommand> commandList;
    private final String condition;

    public CommandFor(String condition, List<AbstractCommand> commandList) {
        this.condition = condition;
        this.commandList = commandList;
    }

    @Override
    public String generateCode() {
        StringBuilder sb = new StringBuilder();

        sb.append("for(");
        sb.append(this.condition);
        sb.append(") {\n");
        commandList.stream().map(AbstractCommand::generateCode).forEach(sb::append);
        sb.append("}\n");

        return sb.toString();
    }

    @Override
    public String toString() {
        return "CommandFor{" +
                "commandList=" + commandList +
                ", condition='" + condition + '\'' +
                '}';
    }
}
