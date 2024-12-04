package de.johannes.processing.languagelexer;

public class TokenIndent extends Token{

    public TokenIndent(int amount) {
        super(" ".repeat(amount), "INDENTATION");
    }
}
