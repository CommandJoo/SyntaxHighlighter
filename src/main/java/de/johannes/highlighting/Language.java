package de.johannes.highlighting;

import de.johannes.highlighting.lexers.JavaLexer;
import de.johannes.highlighting.lexers.PythonLexer;
import de.johannes.processing.languagelexer.SimpleLexer;

public enum Language {

    JAVA(new JavaLexer()), PYTHON(new PythonLexer());

    private final SimpleLexer lexer;

    private Language(final SimpleLexer lexer) {
        this.lexer = lexer;
    }

    public SimpleLexer lexer() {
        return lexer;
    }
}
