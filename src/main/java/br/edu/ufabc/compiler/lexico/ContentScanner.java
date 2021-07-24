package br.edu.ufabc.compiler.lexico;

import br.edu.ufabc.compiler.lexico.enumeration.TK;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ContentScanner {
    private static final Logger LOGGER = Logger.getLogger(ContentScanner.class.getName());
    private static final List<Character> ALLOWED_OPERATORS = Arrays.asList('>', '<', '=', '!');

    private char[] content;
    private int pos;

    public ContentScanner(String filename) {
        try {
            String text = Files.readString(Paths.get(ClassLoader.getSystemResource(filename).toURI()));
            LOGGER.info("debug ---------");
            LOGGER.info(text);
            LOGGER.info("---------------");
            this.content = text.toCharArray();
            this.pos = 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Token nextToken() {
        char currentChar;
        StringBuilder textBuilder = new StringBuilder();
        if (isEOF()) {
            return null;
        }
        int state = 0;
        while (true) {
            currentChar = nextChar();
            switch (state) {
                case 0:
                    state = getStateFromInitialState(currentChar, textBuilder);
                    break;
                case 1:
                    state = getNextStateFromState1(currentChar, textBuilder);
                    break;
                case 2:
                    back();
                    return new Token(TK.IDENTIFIER, textBuilder.toString());
                case 3:
                    state = getNextStateFromState3(currentChar, textBuilder);
                    break;
                case 4:
                    back();
                    return new Token(TK.NUMBER, textBuilder.toString());
                case 5:
                    textBuilder.append(currentChar);
                    return new Token(TK.OPERATOR, textBuilder.toString());
                default:
                    throw new IllegalStateException("Unexpected value: " + state);
            }
        }
    }

    private int getNextStateFromState3(char currentChar, StringBuilder textBuilder) {
        int state;
        if (isDigit(currentChar)) {
            state = 3;
            textBuilder.append(currentChar);
        } else if (!isChar(currentChar))
            state = 4;
        else
            throw new IllegalArgumentException("Unexpected number symbol: " + currentChar);
        return state;
    }

    private int getNextStateFromState1(char currentChar, StringBuilder textBuilder) {
        int state;
        textBuilder.append(currentChar);
        if (isChar(currentChar) || isDigit(currentChar))
            state = 1;
        else if (isSpace(currentChar) || isOperator(currentChar))
            state = 2;
        else
            throw new IllegalArgumentException("Malformed identifier");
        return state;
    }

    private int getStateFromInitialState(char currentChar, StringBuilder textBuilder) {
        int state;
        if (isChar(currentChar)) {
            textBuilder.append(currentChar);
            state = 1;
        } else if (isDigit(currentChar)) {
            textBuilder.append(currentChar);
            state = 3;
        } else if (isSpace(currentChar))
            state = 0;
        else if (isOperator(currentChar))
            state = 5;
        else {
            throw new IllegalArgumentException("Unexpected symbol: " + currentChar + " at " + pos);
        }
        return state;
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    private boolean isChar(char c) {
        return Character.isAlphabetic(c);
    }

    private boolean isOperator(char c) {
        return ALLOWED_OPERATORS.contains(c);
    }

    private boolean isSpace(char c) {
        return Character.isWhitespace(c);
    }

    private char nextChar() {
        return content[pos++];
    }

    private boolean isEOF() {
        return pos == content.length;
    }

    private void back() {
        pos--;
    }
}
