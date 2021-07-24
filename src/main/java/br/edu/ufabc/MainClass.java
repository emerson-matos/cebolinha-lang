package br.edu.ufabc;

import br.edu.ufabc.compiler.lexico.ContentScanner;
import br.edu.ufabc.compiler.lexico.Token;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        ContentScanner sc = new ContentScanner("input.isi");
        Token token;
        LOGGER.setLevel(Level.ALL);
        try {
            do {
                token = sc.nextToken();
                if (token != null) {
                    String value = token.toString();
                    LOGGER.info(value);
                }
            } while (token != null);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}
