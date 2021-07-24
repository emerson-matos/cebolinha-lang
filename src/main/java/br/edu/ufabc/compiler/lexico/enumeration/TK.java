package br.edu.ufabc.compiler.lexico.enumeration;

public enum TK {
    IDENTIFIER(0),
    NUMBER(1),
    OPERATOR(2),
    PUNCTUATION(3),
    ASSIGN(4);


    private final int value;

    TK(int value) {
        this.value = value;
    }
}
