package br.edu.ufabc.antlr;

import br.edu.ufabc.cebolinha.CebolinhaLangLexer;
import br.edu.ufabc.cebolinha.CebolinhaLangParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CebolinhaLangAnltr {
    private static final Logger LOGGER = Logger.getLogger(CebolinhaLangAnltr.class.getName());

    public static void main(String[] args) {
        LOGGER.setLevel(Level.ALL);
        try {
            String filename = "lang.cebolinha";
            CebolinhaLangLexer lexer;
            CebolinhaLangParser parser;
            lexer = new CebolinhaLangLexer(CharStreams.fromPath(Paths.get(ClassLoader.getSystemResource(filename).toURI())));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            parser = new CebolinhaLangParser(tokenStream);
            parser.prog();
            parser.showCommands(LOGGER);
            parser.generateCode();
            LOGGER.info("Compilado com sucesso melmão");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
