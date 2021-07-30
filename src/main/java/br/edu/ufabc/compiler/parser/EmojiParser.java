package br.edu.ufabc.compiler.parser;

import br.edu.ufabc.compiler.enumeration.TK;
import br.edu.ufabc.compiler.lexico.EmojiScanner;
import br.edu.ufabc.compiler.lexico.Token;

import java.util.logging.Logger;

public class EmojiParser {
    private static final Logger LOGGER = Logger.getLogger(EmojiParser.class.getName());
    private EmojiScanner scanner;
    private Token token;

    public EmojiParser(EmojiScanner scanner) {
        this.scanner = scanner;
    }

    public void e() {
        t();
        el();
    }

    public void t() {
        token = scanner.nextToken();
        log();
        if (token != null && token.getType() != TK.IDENTIFIER && token.getType() != TK.NUMBER) {
            throw new IllegalArgumentException("ID or Number expected!, but found " + token.toString());
        }
    }

    public void el() {
        token = scanner.nextToken();
        log();
        if (token != null) {
            op();
            t();
            el();
        }
    }

    public void op() {
        if (token.getType() != TK.OPERATOR) {
            throw new IllegalArgumentException("Operator expected!, but found " + token.toString());
        }
    }

    public void log(){
        if (token != null)
            LOGGER.info(token::toString);
    }
}
