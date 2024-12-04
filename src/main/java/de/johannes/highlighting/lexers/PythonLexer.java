package de.johannes.highlighting.lexers;

import de.johannes.processing.languagelexer.SimpleLexer;

import java.util.List;

public class PythonLexer extends SimpleLexer {

    @Override
    public List<String> keywords() {
        return List.of("and", "del", "for", "is", "raise", "assert", "elif", "from", "lambda", "return", "break", "else", "global", "not", "try", "class", "except", "if", "or", "while", "continue", "exec", "import", "pass", "with", "def", "finally", "in", "print", "yield");
    }

    @Override
    public List<String> operators() {
        return List.of("+", "-", "*", "/", "%", "**", "//", "<<", ">>", "&", "|", "^", "~", "<", "<=", ">", ">=", "<>", "!=", "==");
    }

    @Override
    public List<String> symbols() {
        return List.of("(", ")", "[", "]", "{", "}", ",", ":", ".", "`", "=", ";", "+=", "-=", "*=", "/=", "//=", "%=", "&=", "|=", "^=", ">>=", "<<=", "**=");
    }

    @Override
    public List<String> values() {
        return List.of("true", "false", "^(?:-(?:[0-9](?:\\d{0,2}(?:,\\d{3})+|\\d*))|(?:0|(?:[0-9](?:\\d{0,2}(?:,\\d{3})+|\\d*))))(?:.\\d+|)$");
    }

    @Override
    public List<String> strings() {
        return List.of("\"\"\"", "\"", "'");
    }

    @Override
    public List<String> comments() {
        return List.of("#");
    }

    @Override
    public String identifier() {
        return "(?:\\b[_a-zA-Z]|\\B\\$)[_$a-zA-Z0-9]*+";
    }
}
