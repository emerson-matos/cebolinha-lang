package br.edu.ufabc.compiler.lexico;

import br.edu.ufabc.compiler.enumeration.TK;

public class Token {
    private TK type;
    private String text;
    private int line;
    private int column;

    public Token(TK type, String text, int line, int column) {
        this.type = type;
        this.text = text;
        this.line = line;
        this.column = column - text.length();
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

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", text='" + text + '\'' +
                ", line=" + line +
                ", column=" + column +
                '}';
    }
}
