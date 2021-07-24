package br.edu.ufabc.compiler.lexico;

import br.edu.ufabc.compiler.lexico.enumeration.TK;

public class Token {
    private TK type;
    private String text;

    public Token(TK type, String text) {
        this.type = type;
        this.text = text;
    }

    public TK getType() {
        return type;
    }

    public void setType(TK type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("Token{type='%s', text=%s", type.name(), text);
    }
}
