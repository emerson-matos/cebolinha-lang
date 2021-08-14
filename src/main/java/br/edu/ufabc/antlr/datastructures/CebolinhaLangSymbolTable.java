package br.edu.ufabc.antlr.datastructures;

import br.edu.ufabc.antlr.exceptions.CebolinhaLangSemanticException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CebolinhaLangSymbolTable {

    private static final Logger LOGGER = Logger.getLogger(CebolinhaLangSymbolTable.class.getName());
    private final Map<String, CebolinhaLangSymbol> table = new HashMap<>();

    public CebolinhaLangSymbol add(CebolinhaLangSymbol symbol) {
        if (exists(symbol.getName())) {
            throw new CebolinhaLangSemanticException("Symbol " + symbol + " already exists!");
        }
        LOGGER.log(Level.INFO, "Symbol added! {0}", symbol);
        return table.put(symbol.getName(), symbol);

    }

    public boolean exists(String symbolName) {
        return table.containsKey(symbolName);
    }

    public CebolinhaLangSymbol getSymbol(String symbolName) {
        return table.get(symbolName);
    }

    public CebolinhaLangSymbol updateUsage(String symbol) {
        CebolinhaLangSymbol variable = table.get(symbol);
        if (variable instanceof CebolinhaLangVariable) {
            ((CebolinhaLangVariable) variable).setUsed();
        }
        return table.replace(symbol, variable);
    }

    public Collection<CebolinhaLangSymbol> iterator() {
        return table.values();
    }
}
