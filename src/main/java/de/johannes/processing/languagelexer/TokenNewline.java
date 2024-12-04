package de.johannes.processing.languagelexer;

public class TokenNewline extends Token{
    public TokenNewline(int amount) {
        super("\n".repeat(amount), "NEWLINE");
    }
}
