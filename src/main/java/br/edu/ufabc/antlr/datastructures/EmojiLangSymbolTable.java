package br.edu.ufabc.antlr.datastructures;

import br.edu.ufabc.antlr.exceptions.EmojiLangSemanticException;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmojiLangSymbolTable {

    private static final Logger LOGGER = Logger.getLogger(EmojiLangSymbolTable.class.getName());
    private final Map<String, EmojiLangSymbol> table = new HashMap<>();

    public EmojiLangSymbol add(EmojiLangSymbol symbol) {
        if (exists(symbol.getName())) {
            throw new EmojiLangSemanticException("Symbol " + symbol + " already exists!");
        }
        LOGGER.log(Level.INFO, "Symbol added! {0}", symbol);
        return table.put(symbol.getName(), symbol);

    }

    public boolean exists(String symbolName) {
        return table.containsKey(symbolName);
    }

    public EmojiLangSymbol getSymbol(String symbolName) {
        return table.get(symbolName);
    }
}
