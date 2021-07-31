package br.edu.ufabc;

import br.edu.ufabc.compiler.lexico.EmojiScanner;
import br.edu.ufabc.compiler.parser.EmojiParser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        EmojiScanner sc = new EmojiScanner("input.emo");
        EmojiParser ep = new EmojiParser(sc);
        LOGGER.setLevel(Level.ALL);
        try {
            ep.e();
            LOGGER.info("sucesso");
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}
