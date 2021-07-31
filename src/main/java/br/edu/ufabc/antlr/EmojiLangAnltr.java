package br.edu.ufabc.antlr;

import br.edu.ufabc.emojilang.EmojiLangLexer;
import br.edu.ufabc.emojilang.EmojiLangParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmojiLangAnltr {
    private static final Logger LOGGER = Logger.getLogger(EmojiLangAnltr.class.getName());

    public static void main(String[] args) {
        LOGGER.setLevel(Level.ALL);
        try {
            String filename = "lang.emo";
            EmojiLangLexer lexer;
            EmojiLangParser parser;
            lexer = new EmojiLangLexer(CharStreams.fromPath(Paths.get(ClassLoader.getSystemResource(filename).toURI())));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            parser = new EmojiLangParser(tokenStream);
            parser.prog();
            LOGGER.info("Compilado com sucesso mermão");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
