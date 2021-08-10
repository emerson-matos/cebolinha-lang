package br.edu.ufabc.antlr.ast;

import br.edu.ufabc.antlr.datastructures.CebolinhaLangSymbol;
import br.edu.ufabc.antlr.datastructures.CebolinhaLangSymbolTable;

import java.io.FileWriter;
import java.util.List;

public class CebolinhaLangProgram {
    private CebolinhaLangSymbolTable table;
    private List<AbstractCommand> commandList;
    private String programName;

    public void generateTarget() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("import java.util.Scanner;\n");
        stringBuilder.append("public class MainClass{\n");
        stringBuilder.append("\tpublic static void main(String args[]){\n");
        stringBuilder.append("\t\tScanner _key = new Scanner(System.in);\n");
        table.iterator().stream().map(CebolinhaLangSymbol::generetaCode).forEach(stringBuilder::append);
        commandList.stream().map(AbstractCommand::generateCode).forEach(stringBuilder::append);
        stringBuilder.append("\t}\n");
        stringBuilder.append("}\n");
        try (FileWriter fw = new FileWriter("src/main/java/MainClass.java")) {
            fw.write(stringBuilder.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public CebolinhaLangSymbolTable getTable() {
        return table;
    }

    public void setTable(CebolinhaLangSymbolTable table) {
        this.table = table;
    }

    public List<AbstractCommand> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<AbstractCommand> commandList) {
        this.commandList = commandList;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
