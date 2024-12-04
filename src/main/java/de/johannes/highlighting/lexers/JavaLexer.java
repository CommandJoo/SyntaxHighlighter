package de.johannes.highlighting.lexers;

import de.johannes.highlighting.colors.OneDark;
import de.johannes.processing.languagelexer.SimpleLexer;

import java.awt.*;
import java.util.List;

public class JavaLexer extends SimpleLexer {
    
    public JavaLexer() {
        registerMatcher(List.of("^([A-Z][a-z0-9]+)*[A-Z][a-z0-9]*$"), "CLASS");
        registerThemeVariable("CLASS", "ONE_DARK", Color.decode("#dbb671"));
    }
    
    @Override
    public List<String> keywords() {
        return List.of("package", "import", "extends",
                "public", "private", "protected",
                "static", "final", "abstract", "native",
                "void", "class", "boolean", "int", "char", "short", "byte", "float", "double",
                "return", "new"
        );
    }

    @Override
    public List<String> operators() {
        return List.of("+", "-",
                "*", "/",
                "=");
    }

    @Override
    public List<String> symbols() {
        return List.of("(", ")",
                "{", "}",
                "[", "]",
                ";",
                ".", ",");
    }

    @Override
    public List<String> values() {
        return List.of("true", "false", "null", "^(?:-(?:[0-9](?:\\d{0,2}(?:,\\d{3})+|\\d*))|(?:0|(?:[0-9](?:\\d{0,2}(?:,\\d{3})+|\\d*))))(?:.\\d+|)$");
    }

    @Override
    public List<String> strings() {
        return List.of("\"", "'");
    }

    @Override
    public List<String> comments() {
        return List.of("//");
    }

    @Override
    public String identifier() {
        return "(?:\\b[_a-zA-Z]|\\B\\$)[_$a-zA-Z0-9]*+";
    }
}
