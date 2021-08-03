package br.edu.ufabc.antlr.datastructures;

public abstract class EmojiLangSymbol {
    protected String name;

    EmojiLangSymbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmojiLangSymbol{" +
                "name='" + name + '\'' +
                '}';
    }
}
