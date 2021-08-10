package br.edu.ufabc.antlr.ast;

import java.util.List;

public class CommandDecision implements AbstractCommand {
    private String condition;
    private List<AbstractCommand> trueList;
    private List<AbstractCommand> falseList;

    public CommandDecision(String condition, List<AbstractCommand> trueList, List<AbstractCommand> falseList) {
        this.condition = condition;
        this.trueList = trueList;
        this.falseList = falseList;
    }

    @Override
    public String generateCode() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("if(");
        stringBuilder.append(condition);
        stringBuilder.append(") {\n");
        trueList.stream().map(AbstractCommand::generateCode).forEach(stringBuilder::append);
        if (falseList != null && !falseList.isEmpty()) {
            stringBuilder.append("} else {\n");
            falseList.stream().map(AbstractCommand::generateCode).forEach(stringBuilder::append);
        }
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "CommandDecision{" +
                "condition='" + condition + '\'' +
                ", tlueList=" + trueList +
                ", falseList=" + falseList +
                '}';
    }
}
