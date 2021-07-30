package br.edu.ufabc.compiler.enumeration;

public enum TK {
    IDENTIFIER(0, "Identifier"),
    NUMBER(1, "Number"),
    OPERATOR(2, "Operator"),
    PUNCTUATION(3, "Punctuation"),
    ASSIGN(4, "Assignment");


    private final int value;
    private final String name;

    @Override
    public String toString() {
        return "TK{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }

    TK(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
