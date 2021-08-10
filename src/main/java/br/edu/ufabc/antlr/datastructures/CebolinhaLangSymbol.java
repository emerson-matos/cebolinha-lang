package br.edu.ufabc.antlr.datastructures;

public abstract class CebolinhaLangSymbol {
    protected String name;

    CebolinhaLangSymbol(String name) {
        this.name = name;
    }

    public abstract String generetaCode();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CebolinhaLangSymbol{" +
                "name='" + name + '\'' +
                '}';
    }
}
